package model;

import com.fleetmanagement.fleetmanagementservice.dto.CreatedPackageDto;
import com.fleetmanagement.fleetmanagementservice.enums.DeliveryPointType;
import com.fleetmanagement.fleetmanagementservice.enums.PackageStatus;
import com.fleetmanagement.fleetmanagementservice.model.DeliveryPoint;
import com.fleetmanagement.fleetmanagementservice.model.Package;

public class PackageServiceScenarios {

  public CreatedPackageDto getCreatedPackageDto() {
    return CreatedPackageDto.builder().id(1L)
                            .barcode("P7988000121")
                            .deliveryPointName("BESIKTAS")
                            .deliveryPointType("BRANCH")
                            .volumetricWeight(20)
                            .build();
  }

  public DeliveryPoint getDeliveryPoint() {
    return DeliveryPoint.builder().name("BESIKTAS")
                        .type(DeliveryPointType.BRANCH)
                        .build();
  }

  public Package getPackage() {
    return Package.builder().barcode("P7988000121")
                  .deliveryPoint(getDeliveryPoint())
                  .volumetricWeight(5)
                  .status(PackageStatus.CREATED)
                  .build();
  }
}
