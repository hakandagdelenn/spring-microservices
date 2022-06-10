package com.fleetmanagement.fleetmanagementservice.mapper;

import com.fleetmanagement.fleetmanagementservice.dto.CreatedPackageDto;
import com.fleetmanagement.fleetmanagementservice.model.Package;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface PackageMapper {

  @Mapping(target = "deliveryPointName", source = "deliveryPoint.name")
  @Mapping(target = "deliveryPointType", source = "deliveryPoint.type")
  CreatedPackageDto convertToCreatedPackageDto(Package packageEntity);
}
