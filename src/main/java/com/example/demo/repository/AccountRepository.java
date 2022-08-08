package com.example.demo.repository;

import com.example.demo.models.users.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    public Account findByUsername(String username);
    public Account findAccountByNameAndSurname(String name, String Surname);
}

