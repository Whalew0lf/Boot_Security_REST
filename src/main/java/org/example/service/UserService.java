package org.example.service;

import org.example.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void addUser(User user);
    void removeUser(Long id);
    void updateUser(User user);
}

