package com.fleetmanagement.fleetmanagementservice.validator;

import com.fleetmanagement.fleetmanagementservice.model.DeliveryPoint;
import com.fleetmanagement.fleetmanagementservice.model.Package;
import java.util.List;

public interface BagValidator {

  void markDeliveryPointBranchType(DeliveryPoint deliveryPoint);

  void markAllPackagesSkipped(List<Package> packageBarcodes, List<Package> skippedPackages);
}
