package com.fleetmanagement.fleetmanagementservice.api;

import com.fleetmanagement.fleetmanagementservice.annotation.DefaultApiResponses;
import com.fleetmanagement.fleetmanagementservice.data.request.AssignPackagesToBagRequest;
import com.fleetmanagement.fleetmanagementservice.data.request.CreateBagRequest;
import com.fleetmanagement.fleetmanagementservice.data.response.RestResponseGenerator;
import com.fleetmanagement.fleetmanagementservice.service.BagService;
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
@RequestMapping(value = "api/v1/bag")
public class BagController {

  private final RestResponseGenerator restResponseGenerator;

  private final BagService bagService;

  public BagController(RestResponseGenerator restResponseGenerator, BagService bagService) {
    this.restResponseGenerator = restResponseGenerator;
    this.bagService = bagService;
  }

  @PostMapping(value = "create", produces = MediaType.APPLICATION_JSON_VALUE)
  @DefaultApiResponses
  @ApiOperation(value = "Create New Bag")
  public ResponseEntity<Object> createBag(@RequestBody CreateBagRequest createBagRequest) {
    log.info("Create Bag Request: {}", GsonUtil.toJson(createBagRequest));

    return restResponseGenerator.success(bagService.create(createBagRequest));
  }

  @PostMapping(value = "assign-packages-to-bag", produces = MediaType.APPLICATION_JSON_VALUE)
  @DefaultApiResponses
  @ApiOperation(value = "Assign Packages To Bag")
  public ResponseEntity<Object> assignPackagesToBag(
      @RequestBody AssignPackagesToBagRequest assignPackagesToBagRequest) {

    log.info("Assign Packages To Bag Request: {}", GsonUtil.toJson(assignPackagesToBagRequest));

    return restResponseGenerator.success(bagService.assignPackagesToBag(assignPackagesToBagRequest));
  }
}
