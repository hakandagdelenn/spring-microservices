package com.fleetmanagement.fleetmanagementservice.exception;

import java.io.Serializable;

/**
 * @author hakan.dagdelen
 */

public class SaveBagException extends RuntimeException implements Serializable {

  private static final long serialVersionUID = 8429143209326146172L;

  public SaveBagException(String message) {
    super(message);
  }

  public SaveBagException(String message, Throwable cause) {
    super(message, cause);
  }
}
