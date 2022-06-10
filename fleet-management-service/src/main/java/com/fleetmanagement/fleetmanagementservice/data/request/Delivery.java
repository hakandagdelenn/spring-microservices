
package com.fleetmanagement.fleetmanagementservice.data.request;

import com.google.gson.annotations.Expose;
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
public class Delivery {

  @Expose
  @NotBlank(message = "barcode must not be blank")
  private String barcode;
}
