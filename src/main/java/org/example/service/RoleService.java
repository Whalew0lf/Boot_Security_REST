package org.example.service;

import org.example.model.Role;

public interface RoleService {
    void addRole(Role role);
    Role getRoleByString(String name);
}
