package RotaTrackerAPI.RotaTrackerAPI.service;

import RotaTrackerAPI.RotaTrackerAPI.domain.Driver;
import RotaTrackerAPI.RotaTrackerAPI.domain.Route;
import RotaTrackerAPI.RotaTrackerAPI.enums.RouteStatus;
import RotaTrackerAPI.RotaTrackerAPI.domain.Vehicle;
import RotaTrackerAPI.RotaTrackerAPI.dtos.requests.RouteRequestDTO;
import RotaTrackerAPI.RotaTrackerAPI.dtos.responses.RouteResponseDTO;
import RotaTrackerAPI.RotaTrackerAPI.infra.exception.RecursoNaoEncontradoException;
import RotaTrackerAPI.RotaTrackerAPI.infra.exception.RegraNegocioException;
import RotaTrackerAPI.RotaTrackerAPI.repository.DriverRepository;
import RotaTrackerAPI.RotaTrackerAPI.repository.RouteRepository;
import RotaTrackerAPI.RotaTrackerAPI.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final DriverRepository driverRepository;
    private final RouteRepository routeRepository;
    private final VehicleRepository vehicleRepository;

    @Transactional
    public RouteResponseDTO createRoute(RouteRequestDTO dto){
        Driver driver = driverRepository.findById(dto.driverId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Motorista nao foi encontrado"));
        Vehicle vehicle = vehicleRepository.findById(dto.vehicleID())
                .orElseThrow(()-> new RecursoNaoEncontradoException("Veiculo nao encontrado"));

        Route route = new Route(
                null, // ID gerado pelo banco
                dto.origin(),
                dto.destination(),
                dto.distance(),
                RouteStatus.PENDENTE, // Toda rota nasce pendente
                driver, // 👈 Passamos o Objeto Driver inteiro
                vehicle,// 👈 Passamos o Objeto Vehicle inteiro
                true
        );

        routeRepository.save(route);
        return new RouteResponseDTO(
                route.getId(),
                route.getOrigin(),
                route.getDestination(),
                route.getStatus(),
                driver.getName(),
                vehicle.getModel()
        );
    }
    @Transactional
    public RouteResponseDTO updateStatus(Long routeId, RouteStatus newStatus){
        Route route = routeRepository.findById(routeId)
                .orElseThrow(()-> new RecursoNaoEncontradoException("Rota nao encontrada"));

        if (route.getStatus() == RouteStatus.CONCLUIDO){
            throw new RegraNegocioException("Nao e possivel alterar uma rota ja concluida");
        }
        route.setStatus(newStatus);

        return new RouteResponseDTO(route);
    }

    public Page<RouteResponseDTO> listAll(Pageable pageable){
        return routeRepository.findAllRoutesComplete(pageable)
                .map(route -> new RouteResponseDTO(
                        route.getId(),
                        route.getOrigin(),
                        route.getDestination(),
                        route.getStatus(),
                        route.getDriver().getName(),
                        route.getVehicle().getModel()
                ));
    }
    @Transactional
    public void delete(Long id){
        Route route = routeRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Nao foi encotrado rota nesse id"));
        route.setActive(false);
    }
    public Double getTotalDistanceByDriver(Long driverId){
        driverRepository.findById(driverId)
                .orElseThrow(() -> new RecursoNaoEncontradoException(" Motorista nao encontrado"));
        Double totalDistance = routeRepository.sumDistanceByDriverId(driverId);

        return totalDistance != null ? totalDistance : 0.0;
    }
}
