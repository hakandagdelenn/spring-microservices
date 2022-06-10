package com.fleetmanagement.fleetmanagementservice.api;

import com.fleetmanagement.fleetmanagementservice.annotation.DefaultApiResponses;
import com.fleetmanagement.fleetmanagementservice.data.request.AssignShipmentToVehicleRequest;
import com.fleetmanagement.fleetmanagementservice.data.request.CreateVehicleRequest;
import com.fleetmanagement.fleetmanagementservice.data.response.RestResponseGenerator;
import com.fleetmanagement.fleetmanagementservice.service.VehicleService;
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
@RequestMapping(value = "api/v1/vehicle")
public class VehicleController {

  private final RestResponseGenerator restResponseGenerator;

  private final VehicleService vehicleService;

  public VehicleController(RestResponseGenerator restResponseGenerator,
                           VehicleService vehicleService) {
    this.restResponseGenerator = restResponseGenerator;
    this.vehicleService = vehicleService;
  }

  @PostMapping(value = "create", produces = MediaType.APPLICATION_JSON_VALUE)
  @DefaultApiResponses
  @ApiOperation(value = "Create Vehicle")
  public ResponseEntity<Object> createVehicle(@RequestBody CreateVehicleRequest createVehicleRequest) {
    log.info("Create Vehicle Request: {}", GsonUtil.toJson(createVehicleRequest));

    return restResponseGenerator.success(vehicleService.create(createVehicleRequest));
  }

  @PostMapping(value = "assign-shipment-to-vehicle", produces = MediaType.APPLICATION_JSON_VALUE)
  @DefaultApiResponses
  @ApiOperation(value = "Assign Shipment to Vehicle")
  public ResponseEntity<Object> assignShipmentToVehicle(
      @RequestBody AssignShipmentToVehicleRequest assignShipmentToVehicleRequest) {
    log.info("Assign Shipment To Vehicle Request: {}", GsonUtil.toJson(assignShipmentToVehicleRequest));

    return restResponseGenerator.success(vehicleService.assignShipmentToVehicle(assignShipmentToVehicleRequest));
  }
}
