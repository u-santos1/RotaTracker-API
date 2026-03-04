package RotaTrackerAPI.RotaTrackerAPI.repository;

import RotaTrackerAPI.RotaTrackerAPI.domain.Driver;
import RotaTrackerAPI.RotaTrackerAPI.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
