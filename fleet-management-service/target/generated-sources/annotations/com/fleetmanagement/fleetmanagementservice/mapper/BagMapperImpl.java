package com.fleetmanagement.fleetmanagementservice.mapper;

import com.fleetmanagement.fleetmanagementservice.dto.CreatedBagDto;
import com.fleetmanagement.fleetmanagementservice.dto.CreatedBagDto.CreatedBagDtoBuilder;
import com.fleetmanagement.fleetmanagementservice.enums.DeliveryPointType;
import com.fleetmanagement.fleetmanagementservice.model.Bag;
import com.fleetmanagement.fleetmanagementservice.model.DeliveryPoint;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-12T09:10:14+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class BagMapperImpl implements BagMapper {

    @Override
    public CreatedBagDto convertToCreatedBagDto(Bag bag) {
        if ( bag == null ) {
            return null;
        }

        CreatedBagDtoBuilder createdBagDto = CreatedBagDto.builder();

        createdBagDto.deliveryPointName( bagDeliveryPointName( bag ) );
        DeliveryPointType type = bagDeliveryPointType( bag );
        if ( type != null ) {
            createdBagDto.deliveryPointType( type.name() );
        }
        createdBagDto.id( bag.getId() );
        createdBagDto.barcode( bag.getBarcode() );

        return createdBagDto.build();
    }

    private String bagDeliveryPointName(Bag bag) {
        if ( bag == null ) {
            return null;
        }
        DeliveryPoint deliveryPoint = bag.getDeliveryPoint();
        if ( deliveryPoint == null ) {
            return null;
        }
        String name = deliveryPoint.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private DeliveryPointType bagDeliveryPointType(Bag bag) {
        if ( bag == null ) {
            return null;
        }
        DeliveryPoint deliveryPoint = bag.getDeliveryPoint();
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
