package com.fleetmanagement.fleetmanagementservice.exception;

import java.io.Serializable;

public class SaveVehicleException extends RuntimeException implements Serializable {

  private static final long serialVersionUID = 4662207122455646708L;

  public SaveVehicleException(String message, Throwable cause) {
    super(message, cause);
  }
}
