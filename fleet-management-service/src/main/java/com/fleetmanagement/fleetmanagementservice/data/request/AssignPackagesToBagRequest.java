package com.fleetmanagement.fleetmanagementservice.data.request;

import java.io.Serializable;
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
public class AssignPackagesToBagRequest implements Serializable {

  private static final long serialVersionUID = 300197200786319272L;

  @NotEmpty(message = "barcode list must not be blank")
  private List<String> packageBarcodes;

  @NotBlank(message = "deliveryPointId must not be blank")
  private String bagBarcode;
}