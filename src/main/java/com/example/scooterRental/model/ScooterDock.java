package com.example.scooterRental.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class ScooterDock {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String dockName;
    private Integer availablePlace;

    @OneToMany(mappedBy = "scooterDock", cascade = CascadeType.ALL)
    //one dock can have many scooters
    //mappedBy - for two-way relations
    // it means that Scooter is the "owner" of the relation and it points to field "scooterDock" in Scooter class
    // so in Scooter class there will be more detailed configuraion of relation (joinTable)
    private Set<Scooter> scooters;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDockName() {
        return dockName;
    }

    public void setDockName(String dockName) {
        this.dockName = dockName;
    }

    public Integer getAvailablePlace() {
        return availablePlace;
    }

    public void setAvailablePlace(Integer availablePlace) {
        this.availablePlace = availablePlace;
    }

    public Set<Scooter> getScooters() {
        return scooters;
    }

    public void setScooters(Set<Scooter> scooters) {
        this.scooters = scooters;
    }
}
