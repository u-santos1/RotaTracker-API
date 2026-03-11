package RotaTrackerAPI.RotaTrackerAPI.dtos.responses;

public record DriverTotalDistanceResponseDTO(Long driverId,
                                             Double totalDistance,
                                             String unit) {
}
