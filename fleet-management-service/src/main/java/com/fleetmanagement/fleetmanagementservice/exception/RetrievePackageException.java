package com.fleetmanagement.fleetmanagementservice.exception;

import java.io.Serializable;

/**
 * @author hakan.dagdelen
 */

public class RetrievePackageException extends RuntimeException implements Serializable {

  private static final long serialVersionUID = 8429143209326146172L;

  public RetrievePackageException(String message) {
    super(message);
  }

  public RetrievePackageException(String message, Throwable cause) {
    super(message, cause);
  }
}
