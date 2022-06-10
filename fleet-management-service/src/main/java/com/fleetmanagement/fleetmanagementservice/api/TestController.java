package com.fleetmanagement.fleetmanagementservice.api;

import com.fleetmanagement.fleetmanagementservice.annotation.DefaultApiResponses;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Validated
@RequestMapping(value = "api/v1/test")
public class TestController {

  @GetMapping(value = "/")
  @DefaultApiResponses
  public ResponseEntity<Object> getTestValues() {
    return ResponseEntity.ok(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
  }
}
