package org.example.service;

import org.example.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {
    Set<User> getAllUsers();
    void addUser(User user);
    void removeUser(Long id);
    void updateUser(User user);
}

