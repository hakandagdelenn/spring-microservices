package service;

import com.fleetmanagement.fleetmanagementservice.data.request.CreateBagRequest;
import com.fleetmanagement.fleetmanagementservice.mapper.BagMapper;
import com.fleetmanagement.fleetmanagementservice.repository.BagRepository;
import com.fleetmanagement.fleetmanagementservice.repository.PackageRepository;
import com.fleetmanagement.fleetmanagementservice.service.DeliveryPointService;
import com.fleetmanagement.fleetmanagementservice.service.impl.BagServiceImpl;
import com.fleetmanagement.fleetmanagementservice.validator.BagValidator;
import config.logger.TestLoggerExtension;
import config.mockito.MockitoExtension;
import model.BagServiceScenarios;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

/**
 * @author Hakan Dağdelen
 */

@ExtendWith(MockitoExtension.class)
@ExtendWith(TestLoggerExtension.class)
@DisplayName("Bag service unit test")
class BagServiceTest {

  @InjectMocks
  BagServiceImpl bagService;

  @Mock
  DeliveryPointService deliveryPointService;

  @Mock
  BagRepository bagRepository;

  @Mock
  BagMapper bagMapper;

  @Mock
  BagValidator bagValidator;

  @Mock
  PackageRepository packageRepository;

  private BagServiceScenarios bagServiceScenarios;

  @BeforeEach
  void setUp() {
    bagServiceScenarios = new BagServiceScenarios();
  }

  @Test
  @DisplayName("check create bag and check response data ")
  void whenCreateBagThenReturnCreateBagDto() {
    //given
    var createBagDto = bagServiceScenarios.getCreateBagDto();
    var deliveryPoint = bagServiceScenarios.getDeliveryPoint();
    var bag = bagServiceScenarios.getBag();

    given(deliveryPointService.getDeliveryPoint(Mockito.any())).willReturn(deliveryPoint);
    given(bagRepository.save(bag)).willReturn(bag);
    given(bagMapper.convertToCreatedBagDto(bag)).willReturn(createBagDto);

    //when
    var bagDto = bagService.create(CreateBagRequest.builder()
                                                   .barcode("P7988000121")
                                                   .deliveryPointId(1L)
                                                   .build());

    //then
    assertThat(bagDto).isNotNull()
                      .isEqualTo(createBagDto);

    assertThrows(IllegalArgumentException.class, () -> bagService.create(null));

    then(deliveryPointService).should().getDeliveryPoint(1L);
    then(bagRepository).should().save(bag);
    then(bagMapper).should().convertToCreatedBagDto(bag);
    ;
  }
}
