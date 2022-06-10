package com.fleetmanagement.fleetmanagementservice.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreatedVehicleDto implements Serializable {

  private static final long serialVersionUID = -6220474735675369289L;

  private Long id;

  private String plate;
}
