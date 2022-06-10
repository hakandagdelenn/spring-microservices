package com.fleetmanagement.fleetmanagementservice.repository;

import com.fleetmanagement.fleetmanagementservice.model.DeliveryPoint;
import org.springframework.stereotype.Repository;

/**
 * @author hakan.dagdelen
 */

@Repository
public interface DeliveryPointRepository extends SoftDeleteJpaRepository<DeliveryPoint, Long> {

}
