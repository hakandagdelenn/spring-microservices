package com.fleetmanagement.fleetmanagementservice.service;

import com.fleetmanagement.fleetmanagementservice.data.request.AssignShipmentToVehicleRequest;
import com.fleetmanagement.fleetmanagementservice.data.request.CreateVehicleRequest;
import com.fleetmanagement.fleetmanagementservice.data.request.Route;
import com.fleetmanagement.fleetmanagementservice.data.response.AssignShipmentToVehicleResponse;
import com.fleetmanagement.fleetmanagementservice.dto.CreatedVehicleDto;
import com.fleetmanagement.fleetmanagementservice.model.Bag;
import com.fleetmanagement.fleetmanagementservice.model.DeliveryPoint;
import com.fleetmanagement.fleetmanagementservice.model.Package;
import com.fleetmanagement.fleetmanagementservice.model.RouteHistory;
import com.fleetmanagement.fleetmanagementservice.model.Vehicle;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface VehicleService {

  CreatedVehicleDto create(CreateVehicleRequest createVehicleRequest);

  AssignShipmentToVehicleResponse assignShipmentToVehicle(AssignShipmentToVehicleRequest
                                                              assignShipmentToVehicleRequest);

  void assign(List<Route> routes, Vehicle vehicle);

  void setWrongBags(List<Bag> wrongDeliveryPointBags, Vehicle vehicle, DeliveryPoint deliveryPoint);

  void setWrongBagPackages(List<Bag> wrongDeliveryPointBags, Vehicle vehicle, DeliveryPoint deliveryPoint);

  void setWrongPackages(List<Package> wrongDeliveryPointPackages, Vehicle vehicle, DeliveryPoint deliveryPoint);

  void setBag(Vehicle vehicle, List<Bag> bags, DeliveryPoint deliveryPoint);

  void setPackage(Vehicle vehicle, List<Package> packages, DeliveryPoint deliveryPoint);

  void saveRouteHistory(Vehicle vehicle, DeliveryPoint deliveryPoint, Bag bag);

  void saveRouteHistory(Vehicle vehicle, DeliveryPoint deliveryPoint, Package aPackage);

  Vehicle getVehicle(String plate);

  DeliveryPoint getDeliveryPoint(Long id);

  Package getByBarcodePackage(String barcode);

  List<Package> getByBag(Bag bag);

  Bag getByBarcodeBag(String barcode);

  List<RouteHistory> getRouteHistories(Vehicle vehicle);
}
