package org.example.dao;

import org.example.model.Role;

public interface RoleDao {
    void addRole(Role role);
    Role getRoleByString(String name);
}
