package com.example.scooterRental.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Scooter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String modelName;
    private Integer maxSpeed;
    private BigDecimal rentalPrice;


    @JsonIgnore //to avoid nested JSONs
    @ManyToOne(cascade = CascadeType.ALL)
    //many scooters can be in one dock
    @JoinColumn(name = "scooter_dock_id") //creates additional column
    //@JoinColumn contains info what's the name of column with FK to ScooterDock
    private ScooterDock scooterDock;

    @JsonIgnore //to avoid nested JSONs
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn( //creates additional column
            name = "user_account_id",
            referencedColumnName = "id")
    //TODO check later if referencedColumnName not required -
    // it should work without it, spring finds the PK of the table by itself
    private UserAccount userAccount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Integer getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Integer maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public BigDecimal getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(BigDecimal rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public ScooterDock getScooterDock() {
        return scooterDock;
    }

    public void setScooterDock(ScooterDock scooterDock) {
        this.scooterDock = scooterDock;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
}
