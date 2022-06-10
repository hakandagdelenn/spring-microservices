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
public class CreateDeliveryPointRequest implements Serializable {

  private static final long serialVersionUID = -86549667569654598L;

  @NotBlank(message = "name must not be blank")
  private String name;

  @NotBlank(message = "deliveryPointType must not be blank")
  private String deliveryPointType;
}
