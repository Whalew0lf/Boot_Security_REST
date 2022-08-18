package org.example.service;

import org.example.model.Role;

import java.util.List;

public interface RoleService {
    void addRole(Role role);
    Role getRoleByString(String name);
    List<Role> getAllRoles();
}
