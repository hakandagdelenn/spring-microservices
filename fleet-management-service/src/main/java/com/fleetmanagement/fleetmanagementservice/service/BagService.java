package com.fleetmanagement.fleetmanagementservice.service;

import com.fleetmanagement.fleetmanagementservice.data.request.AssignPackagesToBagRequest;
import com.fleetmanagement.fleetmanagementservice.data.request.CreateBagRequest;
import com.fleetmanagement.fleetmanagementservice.dto.CreatedBagDto;
import com.fleetmanagement.fleetmanagementservice.model.Bag;
import com.fleetmanagement.fleetmanagementservice.model.DeliveryPoint;
import com.fleetmanagement.fleetmanagementservice.model.Package;
import java.util.List;

public interface BagService {

  CreatedBagDto create(CreateBagRequest createBagRequest);

  DeliveryPoint getDeliveryPoint(Long id);

  Bag getByBarcodeBag(String barcode);

  Package getByBarcodePackage(String barcode);

  String assignPackagesToBag(AssignPackagesToBagRequest assignPackagesToBagRequest);

  void assignPackages(Bag bag, List<String> packageBarcodes);

  void setPackage(Bag bag, List<Package> assignedPackages);

  boolean isBagAndPackages(Bag bag, List<String> packageBarcodes);
}
