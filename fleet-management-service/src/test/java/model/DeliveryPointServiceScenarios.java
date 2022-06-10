package model;

import com.fleetmanagement.fleetmanagementservice.dto.CreatedDeliveryPointDto;
import com.fleetmanagement.fleetmanagementservice.enums.DeliveryPointType;
import com.fleetmanagement.fleetmanagementservice.model.DeliveryPoint;

public class DeliveryPointServiceScenarios {

  public CreatedDeliveryPointDto getDeliveryPointDto() {
    return CreatedDeliveryPointDto.builder().id(1L)
                                  .name("BESIKTAS")
                                  .type("BRANCH")
                                  .build();
  }

  public DeliveryPointType getDeliveryPointType() {
    return DeliveryPointType.BRANCH;
  }

  public DeliveryPoint getDeliveryPoint() {
    return DeliveryPoint.builder().name("BESIKTAS")
                        .type(getDeliveryPointType())
                        .build();
  }
}
