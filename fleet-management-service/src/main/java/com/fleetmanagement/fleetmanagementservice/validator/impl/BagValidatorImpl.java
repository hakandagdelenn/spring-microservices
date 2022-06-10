package com.fleetmanagement.fleetmanagementservice.validator.impl;

import com.fleetmanagement.fleetmanagementservice.enums.DeliveryPointType;
import com.fleetmanagement.fleetmanagementservice.exception.AssignPackagesToBagException;
import com.fleetmanagement.fleetmanagementservice.exception.SaveBagException;
import com.fleetmanagement.fleetmanagementservice.model.DeliveryPoint;
import com.fleetmanagement.fleetmanagementservice.model.Package;
import com.fleetmanagement.fleetmanagementservice.util.GsonUtil;
import com.fleetmanagement.fleetmanagementservice.validator.BagValidator;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BagValidatorImpl implements BagValidator {

  @Override
  public void markDeliveryPointBranchType(DeliveryPoint deliveryPoint) {
    if (DeliveryPointType.BRANCH.equals(deliveryPoint.getType())) {
      throw new SaveBagException("Branch delivery point cannot be used for bag!");
    }
  }

  @Override
  public void markAllPackagesSkipped(List<Package> packageBarcodes, List<Package> skippedPackages) {
    if (packageBarcodes.stream().anyMatch(skippedPackages::contains)) {
      log.error("Error while assigning packages to bag! skipped packages : {}", GsonUtil.toJson(skippedPackages));
      throw new AssignPackagesToBagException("All packages are skipped!");
    }
  }
}
