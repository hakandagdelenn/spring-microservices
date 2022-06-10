package com.fleetmanagement.fleetmanagementservice.service.impl;

import com.fleetmanagement.fleetmanagementservice.data.request.CreateDeliveryPointRequest;
import com.fleetmanagement.fleetmanagementservice.dto.CreatedDeliveryPointDto;
import com.fleetmanagement.fleetmanagementservice.enums.DeliveryPointType;
import com.fleetmanagement.fleetmanagementservice.exception.FleetManagementNotFoundException;
import com.fleetmanagement.fleetmanagementservice.exception.RetrieveDeliveryPointException;
import com.fleetmanagement.fleetmanagementservice.exception.SaveDeliveryPointException;
import com.fleetmanagement.fleetmanagementservice.mapper.DeliveryPointMapper;
import com.fleetmanagement.fleetmanagementservice.model.DeliveryPoint;
import com.fleetmanagement.fleetmanagementservice.repository.DeliveryPointRepository;
import com.fleetmanagement.fleetmanagementservice.service.DeliveryPointService;
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
public class DeliveryPointServiceImpl implements DeliveryPointService {

  private final DeliveryPointRepository deliveryPointRepository;

  private final DeliveryPointMapper deliveryPointMapper;

  public DeliveryPointServiceImpl(DeliveryPointRepository deliveryPointRepository,
                                  DeliveryPointMapper deliveryPointMapper) {
    this.deliveryPointRepository = deliveryPointRepository;
    this.deliveryPointMapper = deliveryPointMapper;
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
  public CreatedDeliveryPointDto create(CreateDeliveryPointRequest createDeliveryPointRequest) {
    Assert.notNull(createDeliveryPointRequest, "CreateDeliveryPointRequest cannot be null");
    try {
      var deliveryPoint =
          DeliveryPoint.builder()
                       .name(createDeliveryPointRequest.getName())
                       .type(DeliveryPointType.getDeliveryPoints(createDeliveryPointRequest.getDeliveryPointType()))
                       .build();
      deliveryPoint = deliveryPointRepository.save(deliveryPoint);
      return deliveryPointMapper.convertToCreatedDeliveryPointDto(deliveryPoint);
    } catch (Exception e) {
      log.error("Error while creating vehicle! create vehicle request : {}",
                GsonUtil.toJson(createDeliveryPointRequest), e);
      throw new SaveDeliveryPointException(e.getMessage(), e);
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
}
