package com.fleetmanagement.fleetmanagementservice.data.request;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
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
public class CreateBagRequest implements Serializable {

  private static final long serialVersionUID = -6139433162079811195L;

  @NotBlank(message = "barcode must not be blank")
  private String barcode;

  @NotNull(message = "deliveryPointId must not be null")
  private Long deliveryPointId;
}