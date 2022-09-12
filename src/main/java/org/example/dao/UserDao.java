package org.example.dao;

import org.example.model.User;

import java.util.Set;

public interface UserDao {
    User getUserByEmail(String name);
    Set<User> getAllUsers();
    User addUser(User user);
    void removeUser(Long id);
    void updateUser(User user);
    User getUserById(Long id);
}
