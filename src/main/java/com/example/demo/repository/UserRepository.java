package com.example.demo.repository;

import com.example.demo.models.user.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Account, Long> {
    public Account findByUsername(String username);

    public Account findByNameAndSurname(String name, String Surname);
}

