package com.fleetmanagement.fleetmanagementservice.mapper;

import com.fleetmanagement.fleetmanagementservice.dto.CreatedBagDto;
import com.fleetmanagement.fleetmanagementservice.model.Bag;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface BagMapper {

  @Mapping(target = "deliveryPointName", source = "bag.deliveryPoint.name")
  @Mapping(target = "deliveryPointType", source = "bag.deliveryPoint.type")
  CreatedBagDto convertToCreatedBagDto(Bag bag);
}
