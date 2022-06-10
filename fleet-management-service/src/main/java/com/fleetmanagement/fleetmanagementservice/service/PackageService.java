package com.fleetmanagement.fleetmanagementservice.service;

import com.fleetmanagement.fleetmanagementservice.data.request.CreatePackageRequest;
import com.fleetmanagement.fleetmanagementservice.dto.CreatedPackageDto;
import com.fleetmanagement.fleetmanagementservice.model.DeliveryPoint;

public interface PackageService {

  CreatedPackageDto create(CreatePackageRequest createVehicleRequest);

  DeliveryPoint getDeliveryPoint(Long id);
}
