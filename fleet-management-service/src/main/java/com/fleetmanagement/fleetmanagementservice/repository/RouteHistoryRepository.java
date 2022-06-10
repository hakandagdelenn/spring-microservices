package com.fleetmanagement.fleetmanagementservice.repository;

import com.fleetmanagement.fleetmanagementservice.model.RouteHistory;
import com.fleetmanagement.fleetmanagementservice.model.Vehicle;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * @author hakan.dagdelen
 */

@Repository
public interface RouteHistoryRepository extends SoftDeleteJpaRepository<RouteHistory, Long> {

  List<RouteHistory> findByVehicle(Vehicle vehicle);
}
