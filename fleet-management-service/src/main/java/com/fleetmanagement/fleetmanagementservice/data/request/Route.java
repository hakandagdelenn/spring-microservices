
package com.fleetmanagement.fleetmanagementservice.data.request;

import com.google.gson.annotations.Expose;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

  @Expose
  @NotEmpty(message = "deliveries must not be empty")
  private List<Delivery> deliveries;

  @Expose
  @NotNull(message = "delivery point must not be null")
  private Long deliveryPoint;
}
