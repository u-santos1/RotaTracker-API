package RotaTrackerAPI.RotaTrackerAPI.repository;

import RotaTrackerAPI.RotaTrackerAPI.domain.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
}
