package com.fleetmanagement.fleetmanagementservice.exception;

import java.io.Serializable;

/**
 * @author hakan.dagdelen
 */

public class RetrieveDeliveryPointException extends RuntimeException implements Serializable {

  private static final long serialVersionUID = 8429143209326146172L;

  public RetrieveDeliveryPointException(String message) {
    super(message);
  }

  public RetrieveDeliveryPointException(String message, Throwable cause) {
    super(message, cause);
  }
}
