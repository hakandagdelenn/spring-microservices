package com.fleetmanagement.fleetmanagementservice.exception.client;

import com.fleetmanagement.fleetmanagementservice.config.MessageSourceConfig;
import com.fleetmanagement.fleetmanagementservice.data.response.RestResponseGenerator;
import com.fleetmanagement.fleetmanagementservice.enums.ErrorCode;
import com.fleetmanagement.fleetmanagementservice.exception.AssignPackagesToBagException;
import com.fleetmanagement.fleetmanagementservice.exception.AssignShipmentToVehicleException;
import com.fleetmanagement.fleetmanagementservice.exception.FleetManagementNotFoundException;
import com.fleetmanagement.fleetmanagementservice.exception.RetrieveBagException;
import com.fleetmanagement.fleetmanagementservice.exception.RetrieveDeliveryPointException;
import com.fleetmanagement.fleetmanagementservice.exception.RetrievePackageException;
import com.fleetmanagement.fleetmanagementservice.exception.SaveBagException;
import com.fleetmanagement.fleetmanagementservice.exception.SaveDeliveryPointException;
import com.fleetmanagement.fleetmanagementservice.exception.SavePackageException;
import com.fleetmanagement.fleetmanagementservice.exception.SaveVehicleException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import static com.fleetmanagement.fleetmanagementservice.util.MessageConstants.ASSIGN_BAG_TO_VEHICLE_ERROR_MESSAGE;
import static com.fleetmanagement.fleetmanagementservice.util.MessageConstants.ASSIGN_PACKAGE_TO_BAG_ERROR_MESSAGE;
import static com.fleetmanagement.fleetmanagementservice.util.MessageConstants.FLEET_MANAGEMENT_NOT_FOUND_ERROR_MESSAGE;
import static com.fleetmanagement.fleetmanagementservice.util.MessageConstants.RETRIEVE_BAG_ERROR_MESSAGE;
import static com.fleetmanagement.fleetmanagementservice.util.MessageConstants.RETRIEVE_DELIVERY_POINT_ERROR_MESSAGE;
import static com.fleetmanagement.fleetmanagementservice.util.MessageConstants.RETRIEVE_PACKAGE_ERROR_MESSAGE;
import static com.fleetmanagement.fleetmanagementservice.util.MessageConstants.SAVE_BAG_ERROR_MESSAGE;
import static com.fleetmanagement.fleetmanagementservice.util.MessageConstants.SAVE_DELIVERY_POINT_ERROR_MESSAGE;
import static com.fleetmanagement.fleetmanagementservice.util.MessageConstants.SAVE_PACKAGE_ERROR_MESSAGE;
import static com.fleetmanagement.fleetmanagementservice.util.MessageConstants.SAVE_VEHICLE_ERROR_MESSAGE;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ClientExceptionHandler {

  private final RestResponseGenerator restResponseGenerator;

  public ClientExceptionHandler(RestResponseGenerator restResponseGenerator) {
    this.restResponseGenerator = restResponseGenerator;
  }

  @ExceptionHandler(value = {SaveVehicleException.class})
  public ResponseEntity<Object> handleSaveVehicleException(final SaveVehicleException e,
                                                           final WebRequest request) {

    return restResponseGenerator.error(HttpStatus.BAD_REQUEST, e.getMessage(),
                                       MessageSourceConfig.getMessage(SAVE_VEHICLE_ERROR_MESSAGE),
                                       ErrorCode.SAVE_VEHICLE_EXCEPTION.getCode());
  }

  @ExceptionHandler(value = {SaveDeliveryPointException.class})
  public ResponseEntity<Object> handleSaveDeliveryPointException(final SaveDeliveryPointException e,
                                                                 final WebRequest request) {

    return restResponseGenerator.error(HttpStatus.BAD_REQUEST, e.getMessage(),
                                       MessageSourceConfig.getMessage(SAVE_DELIVERY_POINT_ERROR_MESSAGE),
                                       ErrorCode.SAVE_DELIVERY_POINT_EXCEPTION.getCode());
  }

  @ExceptionHandler(value = {FleetManagementNotFoundException.class})
  public ResponseEntity<Object> handleFleetManagementNotFoundException(final FleetManagementNotFoundException e,
                                                                       final WebRequest request) {

    return restResponseGenerator.error(HttpStatus.BAD_REQUEST, e.getMessage(),
                                       MessageSourceConfig.getMessage(FLEET_MANAGEMENT_NOT_FOUND_ERROR_MESSAGE),
                                       ErrorCode.FLEET_MANAGEMENT_NOT_FOUND_EXCEPTION.getCode());
  }

  @ExceptionHandler(value = {RetrieveDeliveryPointException.class})
  public ResponseEntity<Object> handleRetrieveDeliveryPointException(final RetrieveDeliveryPointException e,
                                                                     final WebRequest request) {

    return restResponseGenerator.error(HttpStatus.BAD_REQUEST, e.getMessage(),
                                       MessageSourceConfig.getMessage(RETRIEVE_DELIVERY_POINT_ERROR_MESSAGE),
                                       ErrorCode.RETRIEVE_DELIVERY_POINT_EXCEPTION.getCode());
  }

  @ExceptionHandler(value = {SaveBagException.class})
  public ResponseEntity<Object> handleSaveBagException(final SaveBagException e,
                                                       final WebRequest request) {

    return restResponseGenerator.error(HttpStatus.BAD_REQUEST, e.getMessage(),
                                       MessageSourceConfig.getMessage(SAVE_BAG_ERROR_MESSAGE),
                                       ErrorCode.SAVE_BAG_EXCEPTION.getCode());
  }

  @ExceptionHandler(value = {SavePackageException.class})
  public ResponseEntity<Object> handleSavePackageException(final SavePackageException e,
                                                           final WebRequest request) {

    return restResponseGenerator.error(HttpStatus.BAD_REQUEST, e.getMessage(),
                                       MessageSourceConfig.getMessage(SAVE_PACKAGE_ERROR_MESSAGE),
                                       ErrorCode.SAVE_PACKAGE_EXCEPTION.getCode());
  }

  @ExceptionHandler(value = {RetrieveBagException.class})
  public ResponseEntity<Object> handleRetrieveBagException(final SavePackageException e,
                                                           final WebRequest request) {

    return restResponseGenerator.error(HttpStatus.BAD_REQUEST, e.getMessage(),
                                       MessageSourceConfig.getMessage(RETRIEVE_BAG_ERROR_MESSAGE),
                                       ErrorCode.RETRIEVE_BAG_EXCEPTION.getCode());
  }

  @ExceptionHandler(value = {RetrievePackageException.class})
  public ResponseEntity<Object> handleRetrievePackageException(final SavePackageException e,
                                                               final WebRequest request) {

    return restResponseGenerator.error(HttpStatus.BAD_REQUEST, e.getMessage(),
                                       MessageSourceConfig.getMessage(RETRIEVE_PACKAGE_ERROR_MESSAGE),
                                       ErrorCode.RETRIEVE_PACKAGE_EXCEPTION.getCode());
  }

  @ExceptionHandler(value = {AssignPackagesToBagException.class})
  public ResponseEntity<Object> handleAssignPackagesToBagException(final SavePackageException e,
                                                                   final WebRequest request) {

    return restResponseGenerator.error(HttpStatus.BAD_REQUEST, e.getMessage(),
                                       MessageSourceConfig.getMessage(ASSIGN_PACKAGE_TO_BAG_ERROR_MESSAGE),
                                       ErrorCode.ASSIGN_PACKAGES_TO_BAG_EXCEPTION.getCode());
  }

  @ExceptionHandler(value = {AssignShipmentToVehicleException.class})
  public ResponseEntity<Object> handleAssignShipmentToVehicleException(final AssignShipmentToVehicleException e,
                                                                       final WebRequest request) {

    return restResponseGenerator.error(HttpStatus.BAD_REQUEST, e.getMessage(),
                                       MessageSourceConfig.getMessage(ASSIGN_BAG_TO_VEHICLE_ERROR_MESSAGE),
                                       ErrorCode.ASSIGN_SHIPMENT_TO_VEHICLE_EXCEPTION.getCode());
  }
}
