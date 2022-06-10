package com.fleetmanagement.fleetmanagementservice.api;

import com.fleetmanagement.fleetmanagementservice.annotation.DefaultApiResponses;
import com.fleetmanagement.fleetmanagementservice.data.request.CreateDeliveryPointRequest;
import com.fleetmanagement.fleetmanagementservice.data.response.RestResponseGenerator;
import com.fleetmanagement.fleetmanagementservice.service.DeliveryPointService;
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
@RequestMapping(value = "api/v1/delivery-point")
public class DeliveryPointController {

  private final RestResponseGenerator restResponseGenerator;

  private final DeliveryPointService deliveryPointService;

  public DeliveryPointController(RestResponseGenerator restResponseGenerator,
                                 DeliveryPointService deliveryPointService) {
    this.restResponseGenerator = restResponseGenerator;
    this.deliveryPointService = deliveryPointService;
  }

  @PostMapping(value = "create", produces = MediaType.APPLICATION_JSON_VALUE)
  @DefaultApiResponses
  @ApiOperation(value = "Create Delivery Point")
  public ResponseEntity<Object> createDeliveryPoint(
      @RequestBody CreateDeliveryPointRequest createDeliveryPointRequest) {
    log.info("Create Delivery Point Request: {}", GsonUtil.toJson(createDeliveryPointRequest));

    return restResponseGenerator.success(deliveryPointService.create(createDeliveryPointRequest));
  }
}
