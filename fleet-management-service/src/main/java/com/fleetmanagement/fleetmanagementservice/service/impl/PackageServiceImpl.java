package com.fleetmanagement.fleetmanagementservice.service.impl;

import com.fleetmanagement.fleetmanagementservice.data.request.CreatePackageRequest;
import com.fleetmanagement.fleetmanagementservice.dto.CreatedPackageDto;
import com.fleetmanagement.fleetmanagementservice.enums.PackageStatus;
import com.fleetmanagement.fleetmanagementservice.exception.SavePackageException;
import com.fleetmanagement.fleetmanagementservice.mapper.PackageMapper;
import com.fleetmanagement.fleetmanagementservice.model.DeliveryPoint;
import com.fleetmanagement.fleetmanagementservice.model.Package;
import com.fleetmanagement.fleetmanagementservice.repository.PackageRepository;
import com.fleetmanagement.fleetmanagementservice.service.DeliveryPointService;
import com.fleetmanagement.fleetmanagementservice.service.PackageService;
import com.fleetmanagement.fleetmanagementservice.util.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * @author hakan.dagdelen
 */

@Service
@Slf4j
public class PackageServiceImpl implements PackageService {

  private final PackageRepository packageRepository;

  private final DeliveryPointService deliveryPointService;

  private final PackageMapper packageMapper;

  public PackageServiceImpl(PackageRepository packageRepository, DeliveryPointService deliveryPointService,
                            PackageMapper packageMapper) {
    this.packageRepository = packageRepository;
    this.deliveryPointService = deliveryPointService;
    this.packageMapper = packageMapper;
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
  public CreatedPackageDto create(CreatePackageRequest createPackageRequest) {
    Assert.notNull(createPackageRequest, "CreatePackageRequest cannot be null");
    try {

      var aPackage = Package.builder().barcode(createPackageRequest.getBarcode())
                            .volumetricWeight(createPackageRequest.getVolumetricWeight())
                            .status(PackageStatus.CREATED)
                            .deliveryPoint(getDeliveryPoint(createPackageRequest.getDeliveryPointId())).build();

      aPackage = packageRepository.save(aPackage);
      return packageMapper.convertToCreatedPackageDto(aPackage);
    } catch (Exception e) {
      log.error("Error while creating package! create package request : {}", GsonUtil.toJson(createPackageRequest), e);
      throw new SavePackageException(e.getMessage(), e);
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
  public DeliveryPoint getDeliveryPoint(Long id) {
    return deliveryPointService.getDeliveryPoint(id);
  }
}