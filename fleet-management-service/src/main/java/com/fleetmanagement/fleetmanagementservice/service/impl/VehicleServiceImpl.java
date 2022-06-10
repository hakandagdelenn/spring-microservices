package com.fleetmanagement.fleetmanagementservice.service.impl;

import com.fleetmanagement.fleetmanagementservice.data.request.AssignShipmentToVehicleRequest;
import com.fleetmanagement.fleetmanagementservice.data.request.CreateVehicleRequest;
import com.fleetmanagement.fleetmanagementservice.data.request.Route;
import com.fleetmanagement.fleetmanagementservice.data.response.AssignShipmentToVehicleResponse;
import com.fleetmanagement.fleetmanagementservice.dto.CreatedVehicleDto;
import com.fleetmanagement.fleetmanagementservice.enums.BagStatus;
import com.fleetmanagement.fleetmanagementservice.enums.PackageStatus;
import com.fleetmanagement.fleetmanagementservice.exception.AssignShipmentToVehicleException;
import com.fleetmanagement.fleetmanagementservice.exception.FleetManagementNotFoundException;
import com.fleetmanagement.fleetmanagementservice.exception.RetrieveBagException;
import com.fleetmanagement.fleetmanagementservice.exception.RetrieveDeliveryPointException;
import com.fleetmanagement.fleetmanagementservice.exception.RetrievePackageException;
import com.fleetmanagement.fleetmanagementservice.exception.SaveVehicleException;
import com.fleetmanagement.fleetmanagementservice.mapper.VehicleMapper;
import com.fleetmanagement.fleetmanagementservice.model.Bag;
import com.fleetmanagement.fleetmanagementservice.model.DeliveryPoint;
import com.fleetmanagement.fleetmanagementservice.model.Package;
import com.fleetmanagement.fleetmanagementservice.model.RouteHistory;
import com.fleetmanagement.fleetmanagementservice.model.Vehicle;
import com.fleetmanagement.fleetmanagementservice.repository.BagRepository;
import com.fleetmanagement.fleetmanagementservice.repository.DeliveryPointRepository;
import com.fleetmanagement.fleetmanagementservice.repository.PackageRepository;
import com.fleetmanagement.fleetmanagementservice.repository.RouteHistoryRepository;
import com.fleetmanagement.fleetmanagementservice.repository.VehicleRepository;
import com.fleetmanagement.fleetmanagementservice.service.VehicleService;
import com.fleetmanagement.fleetmanagementservice.util.GsonUtil;
import com.fleetmanagement.fleetmanagementservice.validator.VehicleValidator;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * @author hakan.dagdelen
 */

@Service
@Slf4j
public class VehicleServiceImpl implements VehicleService {

  private final VehicleRepository vehicleRepository;

  private final VehicleMapper vehicleMapper;

  private final DeliveryPointRepository deliveryPointRepository;

  private final PackageRepository packageRepository;

  private final BagRepository bagRepository;

  private final VehicleValidator vehicleValidator;

  private final RouteHistoryRepository routeHistoryRepository;

  public VehicleServiceImpl(VehicleRepository vehicleRepository,
                            VehicleMapper vehicleMapper,
                            DeliveryPointRepository deliveryPointRepository,
                            PackageRepository packageRepository,
                            BagRepository bagRepository,
                            VehicleValidator vehicleValidator,
                            RouteHistoryRepository routeHistoryRepository) {
    this.vehicleRepository = vehicleRepository;
    this.vehicleMapper = vehicleMapper;
    this.deliveryPointRepository = deliveryPointRepository;
    this.packageRepository = packageRepository;
    this.bagRepository = bagRepository;
    this.vehicleValidator = vehicleValidator;
    this.routeHistoryRepository = routeHistoryRepository;
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
  public CreatedVehicleDto create(CreateVehicleRequest createVehicleRequest) {
    Assert.notNull(createVehicleRequest, "CreateVehicleRequest cannot be null");
    try {
      var vehicle = Vehicle.builder()
                           .plate(createVehicleRequest.getPlate())
                           .build();

      vehicle = vehicleRepository.save(vehicle);

      return vehicleMapper.convertToCreateVehicleDto(vehicle);
    } catch (Exception e) {
      log.error("Error while creating vehicle! create vehicle request : {}", GsonUtil.toJson(createVehicleRequest), e);
      throw new SaveVehicleException(e.getMessage(), e);
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
  public AssignShipmentToVehicleResponse assignShipmentToVehicle(AssignShipmentToVehicleRequest
                                                                     assignShipmentToVehicleRequest) {
    Assert.notNull(assignShipmentToVehicleRequest, "AssignShipmentToVehicleRequest cannot be null");
    try {
      var vehicle = getVehicle(Optional.ofNullable(assignShipmentToVehicleRequest.getPlate()).orElse(""));
      var routes = assignShipmentToVehicleRequest.getRoute();
      assign(routes, vehicle);

      var routeHistories = getRouteHistories(vehicle);

      return vehicleMapper.convertToAssignShipmentToVehicleResponse(vehicle, routeHistories);
    } catch (Exception e) {
      log.error("Error while assigning shipment to vehicle! assign shipment to vehicle request : {}",
                GsonUtil.toJson(assignShipmentToVehicleRequest), e);
      throw new AssignShipmentToVehicleException(e.getMessage(), e);
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
  public void assign(List<Route> routes, Vehicle vehicle) {
    try {
      if (CollectionUtils.isNotEmpty(routes)) {
        routes.forEach(route -> {
          var deliveryPoint = getDeliveryPoint(Optional.of(route.getDeliveryPoint()).orElse(null));
          var deliveries = route.getDeliveries();

          var packages = deliveries.stream()
                                   .map(delivery -> getByBarcodePackage(delivery.getBarcode()))
                                   .filter(Objects::nonNull)
                                   .collect(Collectors.toList());

          var wrongDeliveryPointPackages = vehicleValidator.getWrongDeliveryPointPackages(deliveryPoint, packages);

          setWrongPackages(wrongDeliveryPointPackages, vehicle, deliveryPoint);

          setPackage(vehicle, packages, deliveryPoint);

          var bags = deliveries.stream()
                               .map(delivery -> getByBarcodeBag(delivery.getBarcode()))
                               .filter(Objects::nonNull)
                               .collect(Collectors.toList());

          var wrongDeliveryPointBags = vehicleValidator.getWrongDeliveryPointBags(deliveryPoint, bags);

          setWrongBags(wrongDeliveryPointBags, vehicle, deliveryPoint);

          setBag(vehicle, bags, deliveryPoint);
        });
      }
    } catch (Exception e) {
      log.error("Error while assigning shipment to vehicle! assign shipment to vehicle request : {}",
                GsonUtil.toJson(routes), e);
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
  public void setWrongBags(List<Bag> wrongDeliveryPointBags, Vehicle vehicle, DeliveryPoint deliveryPoint) {
    try {
      if (CollectionUtils.isNotEmpty(wrongDeliveryPointBags)) {
        setWrongBagPackages(wrongDeliveryPointBags, vehicle, deliveryPoint);
        wrongDeliveryPointBags.forEach(bag -> {
          bag.setStatus(BagStatus.UNLOADED);

          saveRouteHistory(vehicle, deliveryPoint, bag);

          bagRepository.save(bag);
        });
      }
    } catch (Exception e) {
      log.error("Error occurred while setting wrong bags! wrong bags : {}", wrongDeliveryPointBags, e);
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
  public void setWrongBagPackages(List<Bag> wrongDeliveryPointBags, Vehicle vehicle, DeliveryPoint deliveryPoint) {
    try {
      var packages = wrongDeliveryPointBags.stream()
                                           .map(this::getByBag)
                                           .flatMap(Collection::stream)
                                           .collect(Collectors.toList());
      if (CollectionUtils.isNotEmpty(packages)) {
        packages.forEach(package1 -> {
          package1.setStatus(PackageStatus.UNLOADED);

          saveRouteHistory(vehicle, deliveryPoint, package1);

          packageRepository.save(package1);
        });
      }
    } catch (Exception e) {
      log.error("Error occurred while setting wrong bag packages! wrong bag packages : {}", wrongDeliveryPointBags, e);
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
  public void setWrongPackages(List<Package> wrongDeliveryPointPackages, Vehicle vehicle, DeliveryPoint deliveryPoint) {
    try {
      if (CollectionUtils.isNotEmpty(wrongDeliveryPointPackages)) {
        wrongDeliveryPointPackages.forEach(aPackage -> {
          aPackage.setStatus(PackageStatus.LOADED);

          saveRouteHistory(vehicle, deliveryPoint, aPackage);

          packageRepository.save(aPackage);
        });
      }
    } catch (Exception e) {
      log.error("Error occurred while save wrong packages! packages : {}", wrongDeliveryPointPackages, e);
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
  public void setBag(Vehicle vehicle, List<Bag> bags, DeliveryPoint deliveryPoint) {
    try {

      var assignedBags = bags.stream()
                             .filter(bag -> bag.getDeliveryPoint().getName().equalsIgnoreCase(deliveryPoint.getName()))
                             .collect(Collectors.toList());

      if (CollectionUtils.isNotEmpty(assignedBags)) {
        assignedBags.forEach(bag -> {
          bag.setVehicle(vehicle);
          bag.setStatus(BagStatus.LOADED);

          saveRouteHistory(vehicle, deliveryPoint, bag);

          bagRepository.save(bag);
        });
      }
    } catch (Exception e) {
      log.error("Error occurred while saving bag! bag : {}", GsonUtil.toJson(bags), e);
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
  public void setPackage(Vehicle vehicle, List<Package> packages, DeliveryPoint deliveryPoint) {
    try {

      var assignedPackages = packages.stream()
                                     .filter(aPackage -> aPackage.getDeliveryPoint()
                                                                 .getName()
                                                                 .equalsIgnoreCase(deliveryPoint.getName()))
                                     .collect(Collectors.toList());

      if (CollectionUtils.isNotEmpty(assignedPackages)) {

        assignedPackages.forEach(aPackage -> {
          aPackage.setStatus(PackageStatus.LOADED);
          aPackage.setVehicle(vehicle);

          saveRouteHistory(vehicle, deliveryPoint, aPackage);

          packageRepository.save(aPackage);
        });
      }
    } catch (Exception e) {
      log.error("Error occurred while saving package! package : {}", GsonUtil.toJson(packages), e);
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
  public void saveRouteHistory(Vehicle vehicle, DeliveryPoint deliveryPoint, Bag bag) {
    try {
      var routeHistory = RouteHistory.builder().bagStatus(bag.getStatus())
                                     .vehicle(vehicle)
                                     .deliveryPoint(deliveryPoint)
                                     .barcode(bag.getBarcode())
                                     .build();

      routeHistoryRepository.save(routeHistory);
    } catch (Exception e) {
      log.error("Error occurred while saving route history! route history : {}", GsonUtil.toJson(bag), e);
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
  public void saveRouteHistory(Vehicle vehicle, DeliveryPoint deliveryPoint, Package aPackage) {
    try {
      var route = RouteHistory.builder().vehicle(vehicle)
                              .deliveryPoint(deliveryPoint)
                              .barcode(aPackage.getBarcode())
                              .packageStatus(aPackage.getStatus())
                              .build();

      routeHistoryRepository.save(route);
    } catch (Exception e) {
      log.error("Error occurred while saving route history! package : {}", GsonUtil.toJson(aPackage), e);
      throw e;
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
  public Vehicle getVehicle(String plate) {
    try {
      return vehicleRepository
          .findByPlate(plate)
          .orElseThrow(
              () -> new FleetManagementNotFoundException(String.format("Vehicle with plate %s not found!", plate)));
    } catch (Exception e) {
      log.error("Error while retrieving vehicle! plate : {}", plate, e);
      throw new RetrieveDeliveryPointException(e.getMessage(), e);
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
  public DeliveryPoint getDeliveryPoint(Long id) {
    try {
      return deliveryPointRepository
          .findById(id)
          .orElseThrow(
              () -> new FleetManagementNotFoundException(String.format("%s id delivery point not exists", id)));
    } catch (Exception e) {
      log.error("Error while getting delivery point! id : {}", id, e);
      throw new RetrieveDeliveryPointException(e.getMessage(), e);
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
  public Package getByBarcodePackage(String barcode) {
    try {
      return packageRepository.findByBarcode(barcode)
                              .orElse(null);
    } catch (Exception e) {
      log.error("Error while getting package by barcode! barcode : {}", barcode, e);
      throw new RetrievePackageException(e.getMessage(), e);
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
  public List<Package> getByBag(Bag bag) {
    try {
      return packageRepository.findByBag(bag);
    } catch (Exception e) {
      log.error("Error while getting package by bag! bag : {}", GsonUtil.toJson(bag), e);
      throw new RetrievePackageException(e.getMessage(), e);
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
  public Bag getByBarcodeBag(String barcode) {
    try {
      return bagRepository.findByBarcode(barcode)
                          .orElse(null);
    } catch (Exception e) {
      log.error("Error while getting bag by barcode! barcode : {}", barcode, e);
      throw new RetrieveBagException(e.getMessage(), e);
    }
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
  public List<RouteHistory> getRouteHistories(Vehicle vehicle) {
    try {
      return routeHistoryRepository.findByVehicle(vehicle);
    } catch (Exception e) {
      log.error("Error while getting route history by vehicle! vehicle : {}", GsonUtil.toJson(vehicle), e);
      return Collections.emptyList();
    }
  }
}
