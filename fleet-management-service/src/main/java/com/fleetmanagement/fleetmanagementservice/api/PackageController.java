package com.fleetmanagement.fleetmanagementservice.api;

import com.fleetmanagement.fleetmanagementservice.annotation.DefaultApiResponses;
import com.fleetmanagement.fleetmanagementservice.data.request.CreatePackageRequest;
import com.fleetmanagement.fleetmanagementservice.data.response.RestResponseGenerator;
import com.fleetmanagement.fleetmanagementservice.service.PackageService;
import com.fleetmanagement.fleetmanagementservice.util.GsonUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Validated
@RequestMapping(value = "api/v1/package")
public class PackageController {

  private final PackageService packageService;

  private final RestResponseGenerator restResponseGenerator;

  public PackageController(PackageService packageService, RestResponseGenerator restResponseGenerator) {
    this.packageService = packageService;
    this.restResponseGenerator = restResponseGenerator;
  }

  @PostMapping(value = "create", produces = MediaType.APPLICATION_JSON_VALUE)
  @DefaultApiResponses
  @ApiOperation(value = "Create package")
  public ResponseEntity<Object> createDeliveryPoint(
      @RequestBody CreatePackageRequest createPackageRequest) {

    log.info("Create Delivery Point Request: {}", GsonUtil.toJson(createPackageRequest));

    return restResponseGenerator.success(packageService.create(createPackageRequest));
  }
}
