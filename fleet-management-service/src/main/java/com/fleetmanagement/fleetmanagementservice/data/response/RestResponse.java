package com.fleetmanagement.fleetmanagementservice.data.response;

/**
 * • status – contains the HTTP response status code as an integer.
 * response values from 500-599 and 400-499 means fail or error and for everything
 * else (e.g. 1XX, 2XX and 3XX responses) means success.
 * • error - only used for 500-599 and 400-499 response statuses, exception name or empty
 * • message – only used for 500-599 and 400-499 response statuses to contain the error message. For
 * internationalization (i18n) purposes, this could contain a message number or code, either alone
 * or contained within delimiters.
 * • data – that contains the success response body. In the case of “error” or “fail” statuses, this would be empty
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author hakan.dagdelen
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestResponse<T> {

  private int status;

  private String error;

  private String errorCode;

  private String message;

  private T data;
}

