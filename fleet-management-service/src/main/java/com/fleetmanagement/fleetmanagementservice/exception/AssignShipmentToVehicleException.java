package com.fleetmanagement.fleetmanagementservice.exception;

import java.io.Serializable;

/**
 * @author hakan.dagdelen
 */

public class AssignShipmentToVehicleException extends RuntimeException implements Serializable {

  private static final long serialVersionUID = 8429143209326146172L;

  public AssignShipmentToVehicleException(String message) {
    super(message);
  }

  public AssignShipmentToVehicleException(String message, Throwable cause) {
    super(message, cause);
  }
}
