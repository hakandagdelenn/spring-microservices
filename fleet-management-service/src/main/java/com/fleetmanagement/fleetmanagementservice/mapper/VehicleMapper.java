package com.fleetmanagement.fleetmanagementservice.mapper;

import com.fleetmanagement.fleetmanagementservice.data.response.AssignShipmentToVehicleResponse;
import com.fleetmanagement.fleetmanagementservice.data.response.Delivery;
import com.fleetmanagement.fleetmanagementservice.data.response.Route;
import com.fleetmanagement.fleetmanagementservice.dto.CreatedVehicleDto;
import com.fleetmanagement.fleetmanagementservice.enums.BagStatus;
import com.fleetmanagement.fleetmanagementservice.enums.PackageStatus;
import com.fleetmanagement.fleetmanagementservice.model.RouteHistory;
import com.fleetmanagement.fleetmanagementservice.model.Vehicle;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * @author hakan.dagdelen
 */

@Mapper(componentModel = "spring", collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface VehicleMapper {

  CreatedVehicleDto convertToCreateVehicleDto(Vehicle vehicle);

  @Mapping(target = "plate", source = "vehicle.plate")
  @Mapping(target = "routes", source = "routeHistoryList", qualifiedByName = "convertToRouteList")
  AssignShipmentToVehicleResponse convertToAssignShipmentToVehicleResponse(Vehicle vehicle,
                                                                           List<RouteHistory> routeHistoryList);

  @Named("convertToRouteList")
  default Map<Long, List<Route>> convertToRouteList(List<RouteHistory> routeHistoryList) {

    return routeHistoryList.stream()
                           .map(routeHistory -> new Route(getDeliveries(routeHistory),
                                                          routeHistory.getDeliveryPoint().getId()))
                           .collect(Collectors.toList())
                           .stream().collect(Collectors.groupingBy(Route::getDeliveryPoint));
  }

  static List<Delivery> getDeliveries(RouteHistory routeHistory) {
    return Collections.singletonList(Delivery.builder()
                                             .barcode(routeHistory.getBarcode())
                                             .state(convertToInteger(routeHistory.getBagStatus(),
                                                                     routeHistory.getPackageStatus()))
                                             .build());
  }

  static Integer convertToInteger(BagStatus bagStatus, PackageStatus packageStatus) {
    if (Objects.nonNull(bagStatus)) {
      return bagStatus.getValue();
    } else if (Objects.nonNull(packageStatus)) {
      return packageStatus.getValue();
    }
    return null;
  }
}
