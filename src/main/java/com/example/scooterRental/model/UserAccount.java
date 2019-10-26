package com.example.scooterRental.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String ownerEmail;
    private String ownerUsername;
    private Integer onwerAge;
    private BigDecimal balance;
    private LocalDateTime createdDate;
    @OneToOne(mappedBy = "userAccount")
    //one user can rent one scooter
    //mappedBy - for two-way relations
    // it means that Scooter is the "owner" of the relation and it points to field "userAccount" in Scooter class
    // so in Scooter class there will be more detailed configuraion of relation (joinTable)
    private Scooter scooter;


    public UserAccount() {
    }

    public UserAccount(Long id, String ownerEmail, String ownerUsername, Integer onwerAge, BigDecimal balance, LocalDateTime createdDate) {
        this.id = id;
        this.ownerEmail = ownerEmail;
        this.ownerUsername = ownerUsername;
        this.onwerAge = onwerAge;
        this.balance = balance;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public Integer getOnwerAge() {
        return onwerAge;
    }

    public void setOnwerAge(Integer onwerAge) {
        this.onwerAge = onwerAge;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Scooter getScooter() {
        return scooter;
    }

    public void setScooter(Scooter scooter) {
        this.scooter = scooter;
    }
}
