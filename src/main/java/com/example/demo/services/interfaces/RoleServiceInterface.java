package com.example.demo.services.interfaces;

import com.example.demo.models.users.Role;

public interface RoleServiceInterface {
    Role saveRole(Role role);

    Role findRoleByName(String name);
}
