package RotaTrackerAPI.RotaTrackerAPI.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "vehicles")
@Getter @Setter @NoArgsConstructor
public class Vehicle {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;
    private String plate;
    private boolean active = true;

    @OneToMany(mappedBy = "vehicle")
    private List<Route> routes;
}
