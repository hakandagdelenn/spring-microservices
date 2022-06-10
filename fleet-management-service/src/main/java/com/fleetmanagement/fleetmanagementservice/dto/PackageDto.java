package com.fleetmanagement.fleetmanagementservice.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PackageDto implements Serializable {

  private static final long serialVersionUID = -178733736411622242L;

  private String plate;

  private String barcode;

  private Long deliveryPointId;
}
