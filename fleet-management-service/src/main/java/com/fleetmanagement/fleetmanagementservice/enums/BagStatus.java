package com.fleetmanagement.fleetmanagementservice.enums;

import java.util.EnumSet;

/**
 * @author hakan.dagdelen
 */

public enum BagStatus {

  CREATED(1),
  LOADED(3),
  UNLOADED(4);

  private final int value;

  BagStatus(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  public static BagStatus getBagStatus(int value) {
    return EnumSet.allOf(BagStatus.class)
                  .stream().filter(bagStatus -> bagStatus.getValue() == value)
                  .findFirst()
                  .orElse(null);
  }

  public static BagStatus getBagStatus(String name) {
    return EnumSet.allOf(BagStatus.class)
                  .stream().filter(bagStatus -> bagStatus.name().equals(name))
                  .findFirst()
                  .orElse(null);
  }
}
