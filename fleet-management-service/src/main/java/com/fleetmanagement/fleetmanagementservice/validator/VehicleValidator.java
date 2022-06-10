package com.fleetmanagement.fleetmanagementservice.validator;

import com.fleetmanagement.fleetmanagementservice.model.Bag;
import com.fleetmanagement.fleetmanagementservice.model.DeliveryPoint;
import com.fleetmanagement.fleetmanagementservice.model.Package;
import java.util.List;

public interface VehicleValidator {

  List<Package> getWrongDeliveryPointPackages(DeliveryPoint deliveryPoint, List<Package> packages);

  List<Bag> getWrongDeliveryPointBags(DeliveryPoint deliveryPoint, List<Bag> bags);
}
