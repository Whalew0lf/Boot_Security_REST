package org.example.dao;

import org.example.model.Role;

import java.util.List;

public interface RoleDao {
    void addRole(Role role);
    Role getRoleByString(String name);
    List<Role> getAllRoles();
}
