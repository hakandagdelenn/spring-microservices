package model;

import com.fleetmanagement.fleetmanagementservice.dto.CreatedVehicleDto;
import com.fleetmanagement.fleetmanagementservice.model.Vehicle;

public class VehicleServiceScenarios {

  public CreatedVehicleDto getCreatedVehicleDto() {
    return CreatedVehicleDto.builder().id(1L)
                            .plate("34 TL 34")
                            .build();
  }

  public Vehicle getVehicle() {
    return Vehicle.builder().plate("34 TL 34").build();
  }
}
