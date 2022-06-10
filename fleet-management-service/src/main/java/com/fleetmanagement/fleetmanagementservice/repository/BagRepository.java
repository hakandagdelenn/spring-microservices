package com.fleetmanagement.fleetmanagementservice.repository;

import com.fleetmanagement.fleetmanagementservice.model.Bag;
import java.util.Optional;
import org.springframework.stereotype.Repository;

/**
 * @author hakan.dagdelen
 */

@Repository
public interface BagRepository extends SoftDeleteJpaRepository<Bag, Long> {

  Optional<Bag> findByBarcode(String barcode);
}
