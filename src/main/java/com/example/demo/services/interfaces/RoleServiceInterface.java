package com.example.demo.services.interfaces;

import com.example.demo.models.user.Role;

public interface RoleServiceInterface {
    Role saveRole(Role role);

    Role findRoleByName(String name);
}
