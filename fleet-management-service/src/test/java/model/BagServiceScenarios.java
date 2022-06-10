package model;

import com.fleetmanagement.fleetmanagementservice.dto.CreatedBagDto;
import com.fleetmanagement.fleetmanagementservice.enums.BagStatus;
import com.fleetmanagement.fleetmanagementservice.enums.DeliveryPointType;
import com.fleetmanagement.fleetmanagementservice.model.Bag;
import com.fleetmanagement.fleetmanagementservice.model.DeliveryPoint;

public class BagServiceScenarios {

  public CreatedBagDto getCreateBagDto() {
    return CreatedBagDto.builder().id(1L)
                        .barcode("P7988000121")
                        .deliveryPointName("BESIKTAS")
                        .deliveryPointType("BRANCH")
                        .build();
  }

  public DeliveryPoint getDeliveryPoint() {
    return DeliveryPoint.builder().name("BESIKTAS")
                        .type(DeliveryPointType.BRANCH)
                        .build();
  }

  public Bag getBag() {
    return Bag.builder().barcode("P7988000121")
              .deliveryPoint(getDeliveryPoint())
              .status(BagStatus.CREATED)
              .build();
  }
}
