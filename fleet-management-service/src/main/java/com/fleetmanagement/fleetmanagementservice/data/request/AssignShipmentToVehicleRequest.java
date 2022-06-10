
package com.fleetmanagement.fleetmanagementservice.data.request;

import com.google.gson.annotations.Expose;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
public class AssignShipmentToVehicleRequest {

  @Expose
  @NotBlank(message = "plate must not be blank")
  private String plate;

  @Expose
  @NotEmpty(message = "route must not be empty")
  private List<Route> route;
}