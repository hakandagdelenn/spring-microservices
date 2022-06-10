
package com.fleetmanagement.fleetmanagementservice.data.response;

import java.util.List;
import java.util.Map;
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
public class AssignShipmentToVehicleResponse {

  private String plate;

  private Map<Long, List<Route>> routes;
}