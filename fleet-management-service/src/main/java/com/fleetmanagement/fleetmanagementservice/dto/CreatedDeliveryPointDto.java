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
public class CreatedDeliveryPointDto implements Serializable {

  private static final long serialVersionUID = -8180789039653844751L;

  private Long id;

  private String name;

  private String type;
}
