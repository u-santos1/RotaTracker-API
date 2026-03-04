package RotaTrackerAPI.RotaTrackerAPI.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RouteRequestDTO(
        @NotBlank String origin,
        @NotBlank String destination,
        @NotNull Double distance,
        @NotNull Long driverId,
        @NotNull Long vehicleID

) {
}
