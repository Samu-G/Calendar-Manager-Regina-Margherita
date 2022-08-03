package com.example.demo.repository;

import com.example.demo.models.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findById(int id);

    public Role findByName(String name);
}