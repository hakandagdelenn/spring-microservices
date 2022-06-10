package com.fleetmanagement.fleetmanagementservice.exception;

import java.io.Serializable;

/**
 * @author hakan.dagdelen
 */

public class AssignPackagesToBagException extends RuntimeException implements Serializable {

  private static final long serialVersionUID = 8429143209326146172L;

  public AssignPackagesToBagException(String message) {
    super(message);
  }

  public AssignPackagesToBagException(String message, Throwable cause) {
    super(message, cause);
  }
}
