package com.fleetmanagement.fleetmanagementservice.repository;

import com.fleetmanagement.fleetmanagementservice.model.Vehicle;
import java.util.Optional;
import org.springframework.stereotype.Repository;

/**
 * @author hakan.dagdelen
 */

@Repository
public interface VehicleRepository extends SoftDeleteJpaRepository<Vehicle, Long> {

  Optional<Vehicle> findByPlate(String plate);
}
