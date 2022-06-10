package com.fleetmanagement.fleetmanagementservice.repository;

import com.fleetmanagement.fleetmanagementservice.dto.PackageDto;
import com.fleetmanagement.fleetmanagementservice.model.Bag;
import com.fleetmanagement.fleetmanagementservice.model.Package;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author hakan.dagdelen
 */

@Repository
public interface PackageRepository extends SoftDeleteJpaRepository<Package, Long> {

  @Query(
      "SELECT new com.fleetmanagement.fleetmanagementservice.dto.PackageDto"
      + "(p.vehicle.plate,p.barcode,p.deliveryPoint.id) FROM Package p "
      + "WHERE p.vehicle.plate=:plate AND p.bag.id is null")
  List<PackageDto> findAllUsingVehicleIdAndBagIsNull(String plate);

  Optional<Package> findByBarcode(String barcode);

  List<Package> findByBag(Bag bag);
}
