package com.fleetmanagement.fleetmanagementservice.exception;

import java.io.Serializable;

public class FleetManagementNotFoundException extends RuntimeException implements Serializable {

  private static final long serialVersionUID = 7683370795482076059L;

  public FleetManagementNotFoundException(String message) {
    super(message);
  }

  public FleetManagementNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
