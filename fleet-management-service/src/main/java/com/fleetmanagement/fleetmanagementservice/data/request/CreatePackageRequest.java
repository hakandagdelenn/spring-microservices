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
public class CreatePackageRequest implements Serializable {

  private static final long serialVersionUID = 300197200786319272L;

  @NotBlank(message = "barcode must not be blank")
  private String barcode;

  @NotNull(message = "deliveryPointId must not be null")
  private Long deliveryPointId;

  @NotNull(message = "volumetricWeight must not be null")
  private Integer volumetricWeight;
}