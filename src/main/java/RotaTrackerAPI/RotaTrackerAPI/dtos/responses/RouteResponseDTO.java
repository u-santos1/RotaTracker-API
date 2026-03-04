package RotaTrackerAPI.RotaTrackerAPI.dtos.responses;

import RotaTrackerAPI.RotaTrackerAPI.domain.Route;
import RotaTrackerAPI.RotaTrackerAPI.enums.RouteStatus;

public record RouteResponseDTO(
        Long id,
        String origin,
        String destination,
        RouteStatus status,
        String driverName,
        String vehicleModel
) {
    public RouteResponseDTO(Route route) {
        this(route.getId(), route.getOrigin(), route.getDestination(),
                route.getStatus(), route.getDriver().getName(), route.getVehicle().getModel());
    }
}
