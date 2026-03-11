package RotaTrackerAPI.RotaTrackerAPI.controller;

import RotaTrackerAPI.RotaTrackerAPI.dtos.responses.DriverTotalDistanceResponseDTO;
import RotaTrackerAPI.RotaTrackerAPI.enums.RouteStatus;
import RotaTrackerAPI.RotaTrackerAPI.dtos.requests.RouteRequestDTO;
import RotaTrackerAPI.RotaTrackerAPI.dtos.responses.RouteResponseDTO;
import RotaTrackerAPI.RotaTrackerAPI.service.RouteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/routes")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;

    @PostMapping
    public ResponseEntity<RouteResponseDTO> create(@RequestBody @Valid RouteRequestDTO dto) {
        RouteResponseDTO responseDTO = routeService.createRoute(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDTO.id()).toUri();
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<RouteResponseDTO> updateStatus(
            @PathVariable Long id,
            @RequestParam RouteStatus newStatus) {

        RouteResponseDTO responseDTO = routeService.updateStatus(id, newStatus);
        return ResponseEntity.ok(responseDTO);
    }
    @GetMapping
    public ResponseEntity<Page<RouteResponseDTO>> list(@PageableDefault(
            size = 10, sort = {"id"}) Pageable pageable){
        var page = routeService.listAll(pageable);
        return ResponseEntity.ok(page);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        routeService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/drivers/{driverId}/total-distance")
    public ResponseEntity<Double> getDriverTotalDistance(@PathVariable Long driverId){
        Double totalDistance = routeService.getTotalDistanceByDriver(driverId);
        return ResponseEntity.ok(totalDistance);
    }


}
