package com.fleetmanagement.fleetmanagementservice.enums;

import java.util.EnumSet;

/**
 * @author hakan.dagdelen
 */

public enum PackageStatus {

  CREATED(1),
  LOADED_INTO_BAG(2),
  LOADED(3),
  UNLOADED(4);

  private final int value;

  PackageStatus(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  public static PackageStatus getPackageStatus(int value) {
    return EnumSet.allOf(PackageStatus.class)
                  .stream().filter(packageStatus -> packageStatus.getValue() == value)
                  .findFirst()
                  .orElse(null);
  }

  public static PackageStatus getPackageStatus(String name) {
    return EnumSet.allOf(PackageStatus.class)
                  .stream().filter(packageStatus -> packageStatus.name().equals(name))
                  .findFirst()
                  .orElse(null);
  }
}
