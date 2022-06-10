package com.fleetmanagement.fleetmanagementservice.enums;

/**
 * @author hakan.dagdelen
 */
public enum ErrorCode {

  //Global exception messages client server communication

  INTERNAL_SERVER_ERROR("0000", "Internal server error"),
  METHOD_ARGUMENT_NOT_VALID_EXCEPTION("0001", "MethodArgumentNotValidException"),
  BIND_EXCEPTION("0002", "BindException"),
  TYPE_MISMATCH_EXCEPTION("0003", "TypeMismatchException"),
  MISSING_SERVLET_REQUEST_PART_EXCEPTION("0004", "MissingServletRequestPartException"),
  MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION("0005", "MissingServletRequestParameterException"),
  NO_HANDLER_FOUND_EXCEPTION("0006", "NoHandlerFoundException"),
  HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION("0007", "HttpRequestMethodNotSupportedException"),
  HTTP_MEDIA_TYPE_NOT_SUPPORTED_EXCEPTION("0008", "HttpMediaTypeNotSupportedException"),
  MISSING_PATH_VARIABLE_EXCEPTION("0009", "MissingPathVariableException"),
  HTTP_MESSAGE_NOT_READABLE_EXCEPTION("0010", "HttpMessageNotReadableException"),
  METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTION("0011", "MethodArgumentTypeMismatchException"),
  CONSTRAINT_VIOLATION_EXCEPTION("0012", "ConstraintViolationException"),
  TRANSACTION_SYSTEM_EXCEPTION("0013", "TransactionSystemException"),
  ILLEGAL_ARGUMENT_EXCEPTION("0014", "Inappropriate argument"),
  JSON_MAPPING_EXCEPTION("0015", "JsonMappingException"),
  REPOSITORY_CONSTRAINT_VIOLATION_EXCEPTION("0016", "RepositoryConstraintViolationException"),
  CONVERSION_FAILED_EXCEPTION("0017", "ConversionFailedException"),
  JSON_PARSE_EXCEPTION("0018", "JsonParseException"),
  AUTHENTICATION_EXCEPTION("0019", "Invalid username/password"),
  ACCESS_DENIED_EXCEPTION("0020", "Permission denied to requested resource"),

  //Fleet management client server communication

  SAVE_VEHICLE_EXCEPTION("1001", "Failed to create new vehicle"),
  SAVE_DELIVERY_POINT_EXCEPTION("1002", "Failed to create new delivery point"),
  FLEET_MANAGEMENT_NOT_FOUND_EXCEPTION("1003", "Fleet Management not found"),
  RETRIEVE_DELIVERY_POINT_EXCEPTION("1004", "Failed to retrieve delivery point"),
  SAVE_BAG_EXCEPTION("1005", "Failed to create new bag"),
  SAVE_PACKAGE_EXCEPTION("1006", "Failed to create new package"),
  RETRIEVE_BAG_EXCEPTION("1007", "Failed to retrieve bag"),
  RETRIEVE_PACKAGE_EXCEPTION("1008", "Failed to retrieve package"),
  ASSIGN_PACKAGES_TO_BAG_EXCEPTION("1009", "Failed to assign packages to bag"),
  ASSIGN_SHIPMENT_TO_VEHICLE_EXCEPTION("1010", "Failed to assign shipment to vehicle");

  private static final String ERROR_PREFIX = "FM";

  private final String code;

  private final String name;

  ErrorCode(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public String getCode() {
    return String.format("%s-%s", ERROR_PREFIX, code);
  }

  public String getName() {
    return name;
  }
}
