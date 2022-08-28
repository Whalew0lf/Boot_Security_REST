package org.example.dao;

import org.example.model.User;

import java.util.List;
import java.util.Set;

public interface UserDao {
    User getUserByName(String name);
    Set<User> getAllUsers();
    void addUser(User user);
    void removeUser(Long id);
    void updateUser(User user);
}
