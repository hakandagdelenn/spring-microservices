package com.fleetmanagement.fleetmanagementservice.annotation;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Hakan Dağdelen
 */
@Retention(RUNTIME)
@Target({METHOD, TYPE})
@ApiResponses(value = {
    @ApiResponse(code = 400, message = "Bad Request"),
    @ApiResponse(code = 401, message = "Unauthorized, Invalid username/password supplied"),
    @ApiResponse(code = 403, message = "Forbidden"),
    @ApiResponse(code = 404, message = "Not Found"),
    @ApiResponse(code = 406, message = "Not Acceptable"),
    @ApiResponse(code = 409, message = "Conflict"),
    @ApiResponse(code = 422, message = "Unprocessable Entity"),
    @ApiResponse(code = 424, message = "Failed Dependency")
})
public @interface DefaultApiResponses {

}