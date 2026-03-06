package RotaTrackerAPI.RotaTrackerAPI.repository;


import RotaTrackerAPI.RotaTrackerAPI.domain.Route;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RouteRepository extends JpaRepository<Route, Long> {
    @Query(
            value = "SELECT r FROM Route r JOIN FETCH r.driver JOIN FETCH r.vehicle",
            countQuery = "SELECT count(r) FROM Route r"
    )
    Page<Route> findAllRoutesComplete(Pageable pageable);
    @Query(
            "SELECT SUM(r.distance) FROM Route r WHERE r.driver.id = :driverId AND r.active = true"
    )
    Double sumDistanceByDriverId(@Param("driverId") Long driverId);

}
