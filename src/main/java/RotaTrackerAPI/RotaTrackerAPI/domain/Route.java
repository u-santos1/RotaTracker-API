package RotaTrackerAPI.RotaTrackerAPI.domain;

import RotaTrackerAPI.RotaTrackerAPI.enums.RouteStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "routes")
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor

@SQLRestriction("active = true") // (Se der erro nesta linha, troque por @Where(clause = "active = true"))
public class Route {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String origin;
    private String destination;
    private Double distance;

    @Enumerated(EnumType.STRING)
    private RouteStatus status = RouteStatus.PENDENTE;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
    private Boolean active;
}
