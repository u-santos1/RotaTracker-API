package RotaTrackerAPI.RotaTrackerAPI.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "drivers")
@Getter @Setter @NoArgsConstructor
public class Driver {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String cnh;
    private boolean active = true;

    @OneToMany(mappedBy = "driver")
    private List<Route> routes;
}
