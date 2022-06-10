package service;

import com.fleetmanagement.fleetmanagementservice.data.request.CreateVehicleRequest;
import com.fleetmanagement.fleetmanagementservice.mapper.VehicleMapper;
import com.fleetmanagement.fleetmanagementservice.repository.BagRepository;
import com.fleetmanagement.fleetmanagementservice.repository.DeliveryPointRepository;
import com.fleetmanagement.fleetmanagementservice.repository.PackageRepository;
import com.fleetmanagement.fleetmanagementservice.repository.RouteHistoryRepository;
import com.fleetmanagement.fleetmanagementservice.repository.VehicleRepository;
import com.fleetmanagement.fleetmanagementservice.service.impl.VehicleServiceImpl;
import com.fleetmanagement.fleetmanagementservice.validator.VehicleValidator;
import config.logger.TestLoggerExtension;
import config.mockito.MockitoExtension;
import model.VehicleServiceScenarios;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

/**
 * @author Hakan Dağdelen
 */

@ExtendWith(MockitoExtension.class)
@ExtendWith(TestLoggerExtension.class)
@DisplayName("Vehicle service unit test")
class VehicleServiceTest {

  @InjectMocks
  VehicleServiceImpl vehicleService;

  @Mock
  VehicleRepository vehicleRepository;

  @Mock
  VehicleMapper vehicleMapper;

  @Mock
  DeliveryPointRepository deliveryPointRepository;

  @Mock
  PackageRepository packageRepository;

  @Mock
  BagRepository bagRepository;

  @Mock
  VehicleValidator vehicleValidator;

  @Mock
  RouteHistoryRepository routeHistoryRepository;

  private VehicleServiceScenarios vehicleServiceScenarios;

  @BeforeEach
  void setUp() {
    vehicleServiceScenarios = new VehicleServiceScenarios();
  }

  @Test
  @DisplayName("check create vehicle and check response data")
  void whenCreateVehicleThenReturnCreateVehicleDto() {
    //given
    var createdVehicleDto = vehicleServiceScenarios.getCreatedVehicleDto();
    var vehicle = vehicleServiceScenarios.getVehicle();

    given(vehicleRepository.save(vehicle)).willReturn(vehicle);
    given(vehicleMapper.convertToCreateVehicleDto(vehicle)).willReturn(createdVehicleDto);

    //when
    var vehicleDto = vehicleService.create(CreateVehicleRequest.builder()
                                                               .plate("34 TL 34")
                                                               .build());

    //then
    assertThat(vehicleDto).isNotNull()
                          .matches(createdVehicleDto1 ->
                                       createdVehicleDto1.getPlate().equalsIgnoreCase(createdVehicleDto.getPlate()));

    assertThrows(IllegalArgumentException.class, () -> vehicleService.create(null));

    then(vehicleRepository).should().save(vehicle);
    then(vehicleMapper).should().convertToCreateVehicleDto(vehicle);

    ;
  }
}
