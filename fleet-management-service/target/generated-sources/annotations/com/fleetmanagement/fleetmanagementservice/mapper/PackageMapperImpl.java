package com.fleetmanagement.fleetmanagementservice.mapper;

import com.fleetmanagement.fleetmanagementservice.dto.CreatedPackageDto;
import com.fleetmanagement.fleetmanagementservice.dto.CreatedPackageDto.CreatedPackageDtoBuilder;
import com.fleetmanagement.fleetmanagementservice.enums.DeliveryPointType;
import com.fleetmanagement.fleetmanagementservice.model.DeliveryPoint;
import com.fleetmanagement.fleetmanagementservice.model.Package;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-12T09:10:14+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class PackageMapperImpl implements PackageMapper {

    @Override
    public CreatedPackageDto convertToCreatedPackageDto(Package packageEntity) {
        if ( packageEntity == null ) {
            return null;
        }

        CreatedPackageDtoBuilder createdPackageDto = CreatedPackageDto.builder();

        createdPackageDto.deliveryPointName( packageEntityDeliveryPointName( packageEntity ) );
        DeliveryPointType type = packageEntityDeliveryPointType( packageEntity );
        if ( type != null ) {
            createdPackageDto.deliveryPointType( type.name() );
        }
        createdPackageDto.id( packageEntity.getId() );
        createdPackageDto.barcode( packageEntity.getBarcode() );
        createdPackageDto.volumetricWeight( packageEntity.getVolumetricWeight() );

        return createdPackageDto.build();
    }

    private String packageEntityDeliveryPointName(Package package1) {
        if ( package1 == null ) {
            return null;
        }
        DeliveryPoint deliveryPoint = package1.getDeliveryPoint();
        if ( deliveryPoint == null ) {
            return null;
        }
        String name = deliveryPoint.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private DeliveryPointType packageEntityDeliveryPointType(Package package1) {
        if ( package1 == null ) {
            return null;
        }
        DeliveryPoint deliveryPoint = package1.getDeliveryPoint();
        if ( deliveryPoint == null ) {
            return null;
        }
        DeliveryPointType type = deliveryPoint.getType();
        if ( type == null ) {
            return null;
        }
        return type;
    }
}
