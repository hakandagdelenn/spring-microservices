package service;

import com.fleetmanagement.fleetmanagementservice.data.request.CreateDeliveryPointRequest;
import com.fleetmanagement.fleetmanagementservice.mapper.DeliveryPointMapper;
import com.fleetmanagement.fleetmanagementservice.repository.DeliveryPointRepository;
import com.fleetmanagement.fleetmanagementservice.service.impl.DeliveryPointServiceImpl;
import config.logger.TestLoggerExtension;
import config.mockito.MockitoExtension;
import model.DeliveryPointServiceScenarios;
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
@DisplayName("Delivery point service unit test")
class DeliveryPointServiceTest {

  @InjectMocks
  DeliveryPointServiceImpl deliveryPointService;

  @Mock
  DeliveryPointRepository deliveryPointRepository;

  @Mock
  DeliveryPointMapper deliveryPointMapper;

  private DeliveryPointServiceScenarios deliveryPointServiceScenarios;

  @BeforeEach
  void setUp() {
    deliveryPointServiceScenarios = new DeliveryPointServiceScenarios();
  }

  @Test
  @DisplayName("check create delivery point and check response data ")
  void whenCreateDeliveryPointThenReturnCreateDeliveryPointDto() {
    //given
    var createdDeliveryPointDto = deliveryPointServiceScenarios.getDeliveryPointDto();
    var deliveryPoint = deliveryPointServiceScenarios.getDeliveryPoint();

    given(deliveryPointRepository.save(deliveryPoint)).willReturn(deliveryPoint);
    given(deliveryPointMapper.convertToCreatedDeliveryPointDto(deliveryPoint)).willReturn(createdDeliveryPointDto);

    //when
    var deliveryPointDto = deliveryPointService.create(CreateDeliveryPointRequest.builder()
                                                                                 .name("BESIKTAS")
                                                                                 .deliveryPointType("BRANCH").build());

    //then
    assertThat(deliveryPointDto).isNotNull()
                                .isEqualTo(createdDeliveryPointDto);

    assertThrows(IllegalArgumentException.class, () -> deliveryPointService.create(null));

    then(deliveryPointRepository).should().save(deliveryPoint);
    then(deliveryPointMapper).should().convertToCreatedDeliveryPointDto(deliveryPoint);
    ;
  }
}
