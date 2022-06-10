package com.fleetmanagement.gatewayserver.hystrix;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class HystrixFallbackController {

  @GetMapping("/fleet-management")
  public ResponseEntity<Object> fleetManagementFallback() {
    return ResponseEntity.badRequest().body("Fleet Management Service is not available.");
  }
}
