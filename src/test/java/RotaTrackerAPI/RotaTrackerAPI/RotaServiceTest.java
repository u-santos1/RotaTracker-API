package RotaTrackerAPI.RotaTrackerAPI;

import RotaTrackerAPI.RotaTrackerAPI.domain.Driver;
import RotaTrackerAPI.RotaTrackerAPI.repository.DriverRepository;
import RotaTrackerAPI.RotaTrackerAPI.repository.RouteRepository;
import RotaTrackerAPI.RotaTrackerAPI.service.RouteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RotaServiceTest {

    @Mock
    private RouteRepository routeRepository;

    @Mock
    private DriverRepository driverRepository;

    @InjectMocks
    private RouteService routeService;

    @Test
    @DisplayName("Deve retornar a soma das distancias das rotas ativas de um motorista")
    void deveRetornarSomaDasDistancias() {
        // Arrange
        Long idMotorista = 1L;
        Double distanciaEsperada = 150.5;

        Driver motoristaFalso = Mockito.mock(Driver.class);
        when(driverRepository.findById(idMotorista)).thenReturn(Optional.of(motoristaFalso));
        when(routeRepository.sumDistanceByDriverId(idMotorista)).thenReturn(distanciaEsperada);

        // Act
        Double distanciaRetornada = routeService.getTotalDistanceByDriver(idMotorista);

        // Assert
        assertEquals(distanciaEsperada, distanciaRetornada, "A distancia calculada esta incorreta");
        Mockito.verify(routeRepository, Mockito.times(1)).sumDistanceByDriverId(idMotorista);
        Mockito.verify(driverRepository, Mockito.times(1)).findById(idMotorista);
    }
}