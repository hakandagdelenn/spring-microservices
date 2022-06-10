package com.fleetmanagement.fleetmanagementservice.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fleetmanagement.fleetmanagementservice.data.response.RestResponseGenerator;
import com.fleetmanagement.fleetmanagementservice.enums.ErrorCode;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author hakan.dagdelen
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  private final RestResponseGenerator restResponseGenerator;

  public GlobalExceptionHandler(RestResponseGenerator restResponseGenerator) {
    this.restResponseGenerator = restResponseGenerator;
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException e,
                                                                final HttpHeaders headers, final HttpStatus status,
                                                                final WebRequest request) {
    log.warn("handleHttpMessageNotReadable {}; ", request, e);
    return restResponseGenerator.badRequest(e.getMessage(), ErrorCode.METHOD_ARGUMENT_NOT_VALID_EXCEPTION);
  }

  @Override
  protected ResponseEntity<Object> handleBindException(final BindException e, final HttpHeaders headers,
                                                       final HttpStatus status, final WebRequest request) {
    log.warn("handleBindException {}; ", request, e);

    final List<String> errorMessageList = getErrors(e);
    errorMessageList.add(e.getMessage());
    return restResponseGenerator.badRequest(errorMessageList.toString(), ErrorCode.BIND_EXCEPTION);
  }

  @Override
  protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException e, final HttpHeaders headers,
                                                      final HttpStatus status, final WebRequest request) {
    log.warn("handleTypeMismatch {}; ", request, e);

    final var errorMessage =
        MessageFormat.format("{0} value for {1} should be of type {2}",
                             e.getValue(), e.getPropertyName(), e.getRequiredType());

    return restResponseGenerator.badRequest(errorMessage, ErrorCode.TYPE_MISMATCH_EXCEPTION);
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestPart(final MissingServletRequestPartException e,
                                                                   final HttpHeaders headers, final HttpStatus status,
                                                                   final WebRequest request) {
    log.error("handleMissingServletRequestPart {}; ", request, e);

    final var errorMessage = MessageFormat.format("{0} part is missing. {1}", e.getRequestPartName(), e.getMessage());
    return restResponseGenerator.badRequest(errorMessage, ErrorCode.MISSING_SERVLET_REQUEST_PART_EXCEPTION);
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestParameter(
      final MissingServletRequestParameterException e, final HttpHeaders headers, final HttpStatus status,
      final WebRequest request) {
    log.error("handleMissingServletRequestParameter {}; ", request, e);

    final var errorMessage =
        MessageFormat.format("{0} parameter is missing. {1}", e.getParameterName(), e.getMessage());
    return restResponseGenerator.badRequest(errorMessage, ErrorCode.MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION);
  }

  @Override
  protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException e,
                                                                 final HttpHeaders headers, final HttpStatus status,
                                                                 final WebRequest request) {
    log.error("handleNoHandlerFoundException {}; ", request, e);

    final var errorMessage =
        MessageFormat.format("No handler found for {0} {1}", e.getHttpMethod(), e.getRequestURL());
    return restResponseGenerator.error(HttpStatus.NOT_FOUND,
                                       ErrorCode.NO_HANDLER_FOUND_EXCEPTION.getName(),
                                       errorMessage,
                                       ErrorCode.NO_HANDLER_FOUND_EXCEPTION.getCode());
  }

  @Override
  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(final HttpRequestMethodNotSupportedException e,
                                                                       final HttpHeaders headers,
                                                                       final HttpStatus status,
                                                                       final WebRequest request) {
    log.warn("handleHttpRequestMethodNotSupported {}; ", request, e);

    final var errorMessageBuilder = new StringBuilder();
    errorMessageBuilder.append("method:").append(e.getMethod());
    errorMessageBuilder.append(" is not supported for this request. Supported methods are ");
    if (!CollectionUtils.isEmpty(e.getSupportedHttpMethods())) {
      e.getSupportedHttpMethods().forEach(t -> errorMessageBuilder.append(t + " "));
    }

    return restResponseGenerator.error(HttpStatus.NOT_FOUND,
                                       ErrorCode.HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION.getName(),
                                       errorMessageBuilder.toString(),
                                       ErrorCode.HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION.getCode());
  }

  @Override
  protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(final HttpMediaTypeNotSupportedException e,
                                                                   final HttpHeaders headers, final HttpStatus status,
                                                                   final WebRequest request) {
    log.warn("handleHttpMediaTypeNotSupported {}; ", request, e);

    final var errorMessageBuilder = new StringBuilder();
    errorMessageBuilder.append("media type:").append(e.getContentType());
    errorMessageBuilder.append(" is not supported. Supported media types are ");
    e.getSupportedMediaTypes().forEach(t -> errorMessageBuilder.append(t + " "));

    return restResponseGenerator.error(HttpStatus.NOT_FOUND,
                                       ErrorCode.HTTP_MEDIA_TYPE_NOT_SUPPORTED_EXCEPTION.getName(),
                                       errorMessageBuilder.toString(),
                                       ErrorCode.HTTP_MEDIA_TYPE_NOT_SUPPORTED_EXCEPTION.getCode());
  }

  @Override
  protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException e, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
    log.warn("handleMissingPathVariable {}; ", request, e);

    final var errorMessageBuilder = new StringBuilder();
    errorMessageBuilder.append("request does not contain parameter:")
                       .append(e.getParameter()).append(e.getMessage());

    return restResponseGenerator.badRequest(errorMessageBuilder.toString(), ErrorCode.MISSING_PATH_VARIABLE_EXCEPTION);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e, HttpHeaders headers,
                                                                HttpStatus status, WebRequest request) {
    log.warn("handleHttpMessageNotReadable {}; ", request, e);

    return restResponseGenerator.error(status, "HttpMessageNotReadable",
                                       e.getMessage(),
                                       ErrorCode.HTTP_MESSAGE_NOT_READABLE_EXCEPTION.getCode());
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<Object> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException e,
                                                                 final WebRequest request) {
    log.warn("handleMethodArgumentTypeMismatch {}; ", request, e);

    final var errorMessage = MessageFormat.format("{0} should be of type {1} {2}", e.getName(),
                                                  e.getRequiredType() != null ? e.getRequiredType().getName() : "",
                                                  e.getMessage());
    return restResponseGenerator.badRequest(errorMessage, ErrorCode.METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTION);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException e,
                                                          final WebRequest request) {
    log.warn("handleConstraintViolation {}; ", request, e);

    final List<String> errorMessageList = new ArrayList<>();
    for (final ConstraintViolation<?> violation : e.getConstraintViolations()) {
      var error =
          MessageFormat.format("{0} {1}:{2}", violation.getRootBeanClass().getName(), violation.getPropertyPath(),
                               violation.getMessage());
      errorMessageList.add(error);
    }

    return restResponseGenerator.badRequest(errorMessageList.toString(), ErrorCode.CONSTRAINT_VIOLATION_EXCEPTION);
  }

  @ExceptionHandler(TransactionSystemException.class)
  protected ResponseEntity<Object> handleTransactionException(TransactionSystemException e, final WebRequest request) {
    log.error("handleTransactionException {};", request, e);
    return handleAll(e, request);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e,
                                                                  final WebRequest request) {
    log.warn("handleIllegalArgumentException {}; ", request, e);
    return restResponseGenerator.badRequest(e.getMessage(), ErrorCode.ILLEGAL_ARGUMENT_EXCEPTION);
  }

  @ExceptionHandler(JsonMappingException.class)
  protected ResponseEntity<Object> handleJsonMappingException(JsonMappingException e, final WebRequest request) {
    log.warn("handleJsonMappingException {}; ", request, e);
    return handleAll(e, request);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleAll(final Exception e, final WebRequest request) {
    log.error("handleAll {}; ", request, e);

    final var errorMessage =
        MessageFormat.format("{0} Cause:{1}", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage());

    return restResponseGenerator.error(HttpStatus.INTERNAL_SERVER_ERROR,
                                       HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), errorMessage);
  }

  @ExceptionHandler(ConversionFailedException.class)
  public ResponseEntity<Object> handleConflict(RuntimeException e) {
    log.warn("handleConflict (ConversionFailedException) ", e);

    final var errorMessage =
        MessageFormat.format("{0} Cause:{1}", HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage());
    return restResponseGenerator.badRequest(errorMessage, ErrorCode.CONVERSION_FAILED_EXCEPTION);
  }

  @ExceptionHandler(JsonParseException.class)
  public ResponseEntity<Object> handleJsonParseException(JsonParseException e, final WebRequest request) {
    log.warn("handleJsonParseException {}; ", request, e);
    return restResponseGenerator.badRequest(e.getMessage(), ErrorCode.JSON_PARSE_EXCEPTION);
  }

  private List<String> getErrors(BindException bindException) {
    final List<String> errorList = new ArrayList<>();
    try {
      for (final FieldError error : bindException.getFieldErrors()) {
        errorList.add(error.getField() + ": " + error.getDefaultMessage());
      }
      for (final ObjectError error : bindException.getGlobalErrors()) {
        errorList.add(error.getObjectName() + ": " + error.getDefaultMessage());
      }
    } catch (Exception e) {
      log.error("Exception occurred at getErrors for bindException:{}", bindException, e);
    }
    return errorList;
  }
}
