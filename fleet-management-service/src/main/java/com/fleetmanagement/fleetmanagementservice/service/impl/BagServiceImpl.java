package com.fleetmanagement.fleetmanagementservice.service.impl;

import com.fleetmanagement.fleetmanagementservice.config.MessageSourceConfig;
import com.fleetmanagement.fleetmanagementservice.data.request.AssignPackagesToBagRequest;
import com.fleetmanagement.fleetmanagementservice.data.request.CreateBagRequest;
import com.fleetmanagement.fleetmanagementservice.dto.CreatedBagDto;
import com.fleetmanagement.fleetmanagementservice.enums.BagStatus;
import com.fleetmanagement.fleetmanagementservice.enums.PackageStatus;
import com.fleetmanagement.fleetmanagementservice.exception.AssignPackagesToBagException;
import com.fleetmanagement.fleetmanagementservice.exception.FleetManagementNotFoundException;
import com.fleetmanagement.fleetmanagementservice.exception.RetrieveBagException;
import com.fleetmanagement.fleetmanagementservice.exception.RetrievePackageException;
import com.fleetmanagement.fleetmanagementservice.exception.SaveBagException;
import com.fleetmanagement.fleetmanagementservice.mapper.BagMapper;
import com.fleetmanagement.fleetmanagementservice.model.Bag;
import com.fleetmanagement.fleetmanagementservice.model.DeliveryPoint;
import com.fleetmanagement.fleetmanagementservice.model.Package;
import com.fleetmanagement.fleetmanagementservice.repository.BagRepository;
import com.fleetmanagement.fleetmanagementservice.repository.PackageRepository;
import com.fleetmanagement.fleetmanagementservice.service.BagService;
import com.fleetmanagement.fleetmanagementservice.service.DeliveryPointService;
import com.fleetmanagement.fleetmanagementservice.util.GsonUtil;
import com.fleetmanagement.fleetmanagementservice.validator.BagValidator;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import static com.fleetmanagement.fleetmanagementservice.util.MessageConstants.ASSIGN_PACKAGE_TO_BAG_SUCCESS_MESSAGE;

/**
 * @author hakan.dagdelen
 */

@Service
@Slf4j
public class BagServiceImpl implements BagService {

  private final DeliveryPointService deliveryPointService;

  private final BagRepository bagRepository;

  private final BagMapper bagMapper;

  private final BagValidator bagValidator;

  private final PackageRepository packageRepository;

  public BagServiceImpl(DeliveryPointService deliveryPointService, BagRepository bagRepository,
                        BagMapper bagMapper,
                        BagValidator bagValidator,
                        PackageRepository packageRepository) {
    this.deliveryPointService = deliveryPointService;
    this.bagRepository = bagRepository;
    this.bagMapper = bagMapper;
    this.bagValidator = bagValidator;
    this.packageRepository = packageRepository;
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
  public CreatedBagDto create(CreateBagRequest createBagRequest) {
    Assert.notNull(createBagRequest, "Create bag request cannot be null");
    try {
      var bag = Bag.builder().barcode(createBagRequest.getBarcode())
                   .status(BagStatus.CREATED)
                   .deliveryPoint(getDeliveryPoint(createBagRequest.getDeliveryPointId()))
                   .build();

      bag = bagRepository.save(bag);

      return bagMapper.convertToCreatedBagDto(bag);
    } catch (Exception e) {
      log.error("Error while creating bag! create bag request : {}", GsonUtil.toJson(createBagRequest), e);
      throw new SaveBagException(e.getMessage(), e);
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
  public String assignPackagesToBag(AssignPackagesToBagRequest assignPackagesToBagRequest) {
    Assert.notNull(assignPackagesToBagRequest, "Assign packages to bag request cannot be null");
    try {
      var bag = getByBarcodeBag(assignPackagesToBagRequest.getBagBarcode());
      var packageBarcodes = assignPackagesToBagRequest.getPackageBarcodes();

      assignPackages(bag, packageBarcodes);

      log.info("Bag with barcode {} has been assigned packages", bag.getBarcode());
      return MessageSourceConfig.getMessage(ASSIGN_PACKAGE_TO_BAG_SUCCESS_MESSAGE);
    } catch (Exception e) {
      log.error("Error while assigning packages to bag! assign packages to bag request : {}",
                GsonUtil.toJson(assignPackagesToBagRequest), e);
      throw new AssignPackagesToBagException(e.getMessage(), e);
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
  public void assignPackages(Bag bag, List<String> packageBarcodes) {
    try {
      if (isBagAndPackages(bag, packageBarcodes)) {
        var packages = packageBarcodes.stream().map(this::getByBarcodePackage)
                                      .filter(Objects::nonNull)
                                      .collect(Collectors.toList());

        var assignedPackages = packages.stream()
                                       .filter(aPackage -> aPackage.getDeliveryPoint()
                                                                   .getName()
                                                                   .equalsIgnoreCase(bag.getDeliveryPoint().getName()))
                                       .collect(Collectors.toList());

        var skippedPackages = packages.stream()
                                      .filter(aPackage -> !aPackage.getDeliveryPoint()
                                                                   .getName()
                                                                   .equalsIgnoreCase(bag.getDeliveryPoint().getName()))
                                      .collect(Collectors.toList());

        bagValidator.markAllPackagesSkipped(packages, skippedPackages);

        setPackage(bag, assignedPackages);
      }
    } catch (Exception e) {
      log.error("Error while getting skipped packages! bag : {}", GsonUtil.toJson(bag), e);
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
  public void setPackage(Bag bag, List<Package> assignedPackages) {
    try {
      if (CollectionUtils.isNotEmpty(assignedPackages)) {
        assignedPackages.forEach(aPackage -> {
          aPackage.setBag(bag);
          aPackage.setStatus(PackageStatus.LOADED_INTO_BAG);
          packageRepository.save(aPackage);
        });
      }
    } catch (Exception e) {
      log.error("Error while setting package! bag : {}", GsonUtil.toJson(bag), e);
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
  public boolean isBagAndPackages(Bag bag, List<String> packageBarcodes) {
    return Objects.nonNull(bag) && CollectionUtils.isNotEmpty(packageBarcodes);
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
  public Bag getByBarcodeBag(String barcode) {
    try {
      return bagRepository.findByBarcode(barcode)
                          .orElseThrow(
                              () -> new FleetManagementNotFoundException("Bag not found! Barcode : " + barcode));
    } catch (Exception e) {
      log.error("Error while getting bag by barcode! barcode : {}", barcode, e);
      throw new RetrieveBagException(e.getMessage(), e);
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
  public DeliveryPoint getDeliveryPoint(Long id) {
    var deliveryPoint = deliveryPointService.getDeliveryPoint(id);
    bagValidator.markDeliveryPointBranchType(deliveryPoint);
    return deliveryPoint;
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
  public Package getByBarcodePackage(String barcode) {
    try {
      return packageRepository
          .findByBarcode(barcode)
          .orElseThrow(() -> new FleetManagementNotFoundException("Package not found with barcode : " + barcode));
    } catch (Exception e) {
      log.error("Error while getting package by barcode! barcode : {}", barcode, e);
      throw new RetrievePackageException(e.getMessage(), e);
    }
  }
}
