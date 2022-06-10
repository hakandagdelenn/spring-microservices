
package com.fleetmanagement.fleetmanagementservice.data.response;

import java.util.List;
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
public class Route {

  private List<Delivery> deliveries;

  private Long deliveryPoint;
}
