package com.fleetmanagement.fleetmanagementservice.validator.impl;

import com.fleetmanagement.fleetmanagementservice.model.Bag;
import com.fleetmanagement.fleetmanagementservice.model.DeliveryPoint;
import com.fleetmanagement.fleetmanagementservice.model.Package;
import com.fleetmanagement.fleetmanagementservice.validator.VehicleValidator;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class VehicleValidatorImpl implements VehicleValidator {

  @Override
  public List<Package> getWrongDeliveryPointPackages(DeliveryPoint deliveryPoint, List<Package> packages) {
    var wrongDeliveryPointPackages =
        packages.stream()
                .filter(aPackage -> !aPackage.getDeliveryPoint().getName().equalsIgnoreCase(deliveryPoint.getName()))
                .collect(Collectors.toList());

    if (CollectionUtils.isNotEmpty(wrongDeliveryPointPackages)) {
      log.warn("Error while assigning shipment to vehicle! wrong delivery point packages : {}",
               wrongDeliveryPointPackages);
      return wrongDeliveryPointPackages;
    }
    return Collections.emptyList();
  }

  @Override
  public List<Bag> getWrongDeliveryPointBags(DeliveryPoint deliveryPoint, List<Bag> bags) {
    var wrongDeliveryPointBags =
        bags.stream()
            .filter(aBag -> !aBag.getDeliveryPoint().getName().equalsIgnoreCase(deliveryPoint.getName()))
            .collect(Collectors.toList());

    if (CollectionUtils.isNotEmpty(wrongDeliveryPointBags)) {
      log.warn("Error while assigning shipment to vehicle! wrong delivery point bags : {}", wrongDeliveryPointBags);
      return wrongDeliveryPointBags;
    }
    return Collections.emptyList();
  }
}
