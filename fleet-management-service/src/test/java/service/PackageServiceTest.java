package service;

import com.fleetmanagement.fleetmanagementservice.data.request.CreatePackageRequest;
import com.fleetmanagement.fleetmanagementservice.mapper.PackageMapper;
import com.fleetmanagement.fleetmanagementservice.repository.PackageRepository;
import com.fleetmanagement.fleetmanagementservice.service.DeliveryPointService;
import com.fleetmanagement.fleetmanagementservice.service.impl.PackageServiceImpl;
import config.logger.TestLoggerExtension;
import config.mockito.MockitoExtension;
import model.PackageServiceScenarios;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

/**
 * @author Hakan Dağdelen
 */

@ExtendWith(MockitoExtension.class)
@ExtendWith(TestLoggerExtension.class)
@DisplayName("Package service unit test")
class PackageServiceTest {

  @InjectMocks
  PackageServiceImpl packageService;

  @Mock
  PackageRepository packageRepository;

  @Mock
  DeliveryPointService deliveryPointService;

  @Mock
  PackageMapper packageMapper;

  private PackageServiceScenarios packageServiceScenarios;

  @BeforeEach
  void setUp() {
    packageServiceScenarios = new PackageServiceScenarios();
  }

  @Test
  @DisplayName("check create package and check response data ")
  void whenCreatePackageThenReturnCreatePackageDto() {
    //given
    var createdPackageDto = packageServiceScenarios.getCreatedPackageDto();
    var deliveryPoint = packageServiceScenarios.getDeliveryPoint();
    var aPackage = packageServiceScenarios.getPackage();

    given(deliveryPointService.getDeliveryPoint(Mockito.any())).willReturn(deliveryPoint);
    given(packageRepository.save(aPackage)).willReturn(aPackage);
    given(packageMapper.convertToCreatedPackageDto(aPackage)).willReturn(createdPackageDto);

    //when
    var packageDto = packageService.create(CreatePackageRequest.builder()
                                                               .barcode("P7988000121")
                                                               .deliveryPointId(1L)
                                                               .volumetricWeight(5).build());

    //then
    assertThat(packageDto).isNotNull()
                          .isEqualTo(createdPackageDto);

    then(deliveryPointService).should().getDeliveryPoint(1L);
    then(packageRepository).should().save(aPackage);
    then(packageMapper).should().convertToCreatedPackageDto(aPackage);
  }
}
