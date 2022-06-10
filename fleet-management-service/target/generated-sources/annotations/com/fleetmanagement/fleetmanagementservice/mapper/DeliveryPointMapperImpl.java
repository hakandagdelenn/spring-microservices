package com.fleetmanagement.fleetmanagementservice.mapper;

import com.fleetmanagement.fleetmanagementservice.dto.CreatedDeliveryPointDto;
import com.fleetmanagement.fleetmanagementservice.dto.CreatedDeliveryPointDto.CreatedDeliveryPointDtoBuilder;
import com.fleetmanagement.fleetmanagementservice.model.DeliveryPoint;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-12T09:10:14+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class DeliveryPointMapperImpl implements DeliveryPointMapper {

    @Override
    public CreatedDeliveryPointDto convertToCreatedDeliveryPointDto(DeliveryPoint deliveryPoint) {
        if ( deliveryPoint == null ) {
            return null;
        }

        CreatedDeliveryPointDtoBuilder createdDeliveryPointDto = CreatedDeliveryPointDto.builder();

        createdDeliveryPointDto.id( deliveryPoint.getId() );
        createdDeliveryPointDto.name( deliveryPoint.getName() );
        if ( deliveryPoint.getType() != null ) {
            createdDeliveryPointDto.type( deliveryPoint.getType().name() );
        }

        return createdDeliveryPointDto.build();
    }
}
