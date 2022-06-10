package com.fleetmanagement.fleetmanagementservice.data.request;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hakan.dagdelen
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateVehicleRequest implements Serializable {

  private static final long serialVersionUID = -772606881367407142L;

  @NotBlank(message = "plate must not be blank")
  private String plate;
}
