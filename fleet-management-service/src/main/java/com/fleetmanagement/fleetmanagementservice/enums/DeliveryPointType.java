package com.fleetmanagement.fleetmanagementservice.enums;

import com.fleetmanagement.fleetmanagementservice.exception.FleetManagementNotFoundException;
import java.util.EnumSet;

/**
 * @author hakan.dagdelen
 */

public enum DeliveryPointType {

  BRANCH(1),
  DISTRIBUTION_CENTER(2),
  TRANSFER_CENTER(3);

  private final int value;

  public int getValue() {
    return value;
  }

  DeliveryPointType(int value) {
    this.value = value;
  }

  public static DeliveryPointType getDeliveryPoints(int value) {
    return EnumSet.allOf(DeliveryPointType.class)
                  .stream().filter(deliveryPointType -> deliveryPointType.getValue() == value)
                  .findFirst()
                  .orElse(null);
  }

  public static DeliveryPointType getDeliveryPoints(String value) {
    return EnumSet.allOf(DeliveryPointType.class)
                  .stream().filter(deliveryPointType -> deliveryPointType.name().equals(value))
                  .findFirst()
                  .orElseThrow(
                      () -> new FleetManagementNotFoundException("No DeliveryPoint found for value: " + value));
  }
}
