package com.fleetmanagement.fleetmanagementservice.service;

import com.fleetmanagement.fleetmanagementservice.data.request.CreateDeliveryPointRequest;
import com.fleetmanagement.fleetmanagementservice.dto.CreatedDeliveryPointDto;
import com.fleetmanagement.fleetmanagementservice.model.DeliveryPoint;

public interface DeliveryPointService {

  CreatedDeliveryPointDto create(CreateDeliveryPointRequest createDeliveryPointRequest);

  DeliveryPoint getDeliveryPoint(Long id);
}
