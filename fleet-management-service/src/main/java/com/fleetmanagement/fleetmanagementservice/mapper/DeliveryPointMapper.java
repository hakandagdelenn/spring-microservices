package com.fleetmanagement.fleetmanagementservice.mapper;

import com.fleetmanagement.fleetmanagementservice.dto.CreatedDeliveryPointDto;
import com.fleetmanagement.fleetmanagementservice.model.DeliveryPoint;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface DeliveryPointMapper {

  CreatedDeliveryPointDto convertToCreatedDeliveryPointDto(DeliveryPoint deliveryPoint);
}
