package com.fleetmanagement.fleetmanagementservice.data.response;

import com.fleetmanagement.fleetmanagementservice.enums.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @author hakan.dagdelen
 */

@Component
public class RestResponseGenerator {

  public <T> ResponseEntity<Object> success(T responseData) {

    return success(responseData, HttpStatus.OK);
  }

  public <T> ResponseEntity<Object> success(T responseData, HttpStatus httpStatus) {
    var restResponse = RestResponse.<T>builder()
                                   .status(httpStatus.value())
                                   .data(responseData)
                                   .build();
    return new ResponseEntity<>(restResponse, httpStatus);
  }

  public ResponseEntity<Object> error(HttpStatus httpStatus, String error, String errorMessage) {
    var restResponse = RestResponse.builder()
                                   .status(httpStatus.value())
                                   .error(error)
                                   .message(errorMessage)
                                   .build();
    return new ResponseEntity<>(restResponse, httpStatus);
  }

  public ResponseEntity<Object> error(HttpStatus httpStatus, String error, String errorMessage, String errorCode) {
    var restResponse = RestResponse.builder()
                                   .status(httpStatus.value())
                                   .error(error)
                                   .errorCode(errorCode)
                                   .message(errorMessage)
                                   .build();
    return new ResponseEntity<>(restResponse, httpStatus);
  }

  public ResponseEntity<Object> badRequest(String errorMessage, ErrorCode errorCode) {
    var restResponse = RestResponse.builder()
                                   .status(HttpStatus.BAD_REQUEST.value())
                                   .error(errorCode.getName())
                                   .errorCode(errorCode.getCode())
                                   .message(errorMessage)
                                   .build();
    return new ResponseEntity<>(restResponse, HttpStatus.BAD_REQUEST);
  }

  public ResponseEntity<Object> badRequest(ErrorCode errorCode) {
    var restResponse = RestResponse.builder()
                                   .status(HttpStatus.BAD_REQUEST.value())
                                   .error(errorCode.getName())
                                   .errorCode(errorCode.getCode())
                                   .message(errorCode.getName())
                                   .build();
    return new ResponseEntity<>(restResponse, HttpStatus.BAD_REQUEST);
  }
}
