package com.example.scooterRental.repository;

import com.example.scooterRental.model.UserAccount;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {
    List<UserAccount> findByOwnerEmail(String ownerEmail);
}
