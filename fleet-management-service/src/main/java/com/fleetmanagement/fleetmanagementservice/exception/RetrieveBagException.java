package com.fleetmanagement.fleetmanagementservice.exception;

import java.io.Serializable;

/**
 * @author hakan.dagdelen
 */

public class RetrieveBagException extends RuntimeException implements Serializable {

  private static final long serialVersionUID = 8429143209326146172L;

  public RetrieveBagException(String message) {
    super(message);
  }

  public RetrieveBagException(String message, Throwable cause) {
    super(message, cause);
  }
}
