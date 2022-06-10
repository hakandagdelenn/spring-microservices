package com.fleetmanagement.fleetmanagementservice.mapper;

import com.fleetmanagement.fleetmanagementservice.data.response.AssignShipmentToVehicleResponse;
import com.fleetmanagement.fleetmanagementservice.data.response.AssignShipmentToVehicleResponse.AssignShipmentToVehicleResponseBuilder;
import com.fleetmanagement.fleetmanagementservice.dto.CreatedVehicleDto;
import com.fleetmanagement.fleetmanagementservice.dto.CreatedVehicleDto.CreatedVehicleDtoBuilder;
import com.fleetmanagement.fleetmanagementservice.model.RouteHistory;
import com.fleetmanagement.fleetmanagementservice.model.Vehicle;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-12T09:10:14+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class VehicleMapperImpl implements VehicleMapper {

    @Override
    public CreatedVehicleDto convertToCreateVehicleDto(Vehicle vehicle) {
        if ( vehicle == null ) {
            return null;
        }

        CreatedVehicleDtoBuilder createdVehicleDto = CreatedVehicleDto.builder();

        createdVehicleDto.id( vehicle.getId() );
        createdVehicleDto.plate( vehicle.getPlate() );

        return createdVehicleDto.build();
    }

    @Override
    public AssignShipmentToVehicleResponse convertToAssignShipmentToVehicleResponse(Vehicle vehicle, List<RouteHistory> routeHistoryList) {
        if ( vehicle == null && routeHistoryList == null ) {
            return null;
        }

        AssignShipmentToVehicleResponseBuilder assignShipmentToVehicleResponse = AssignShipmentToVehicleResponse.builder();

        if ( vehicle != null ) {
            assignShipmentToVehicleResponse.plate( vehicle.getPlate() );
        }
        if ( routeHistoryList != null ) {
            assignShipmentToVehicleResponse.routes( convertToRouteList( routeHistoryList ) );
        }

        return assignShipmentToVehicleResponse.build();
    }
}
