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
public class CreatedBagDto implements Serializable {

  private static final long serialVersionUID = -81207907908769098L;

  private Long id;

  private String barcode;

  private String deliveryPointName;

  private String deliveryPointType;
}
