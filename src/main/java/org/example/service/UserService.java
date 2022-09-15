package org.example.service;

import org.example.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {
    Set<User> getAllUsers();
    User addUser(User user);
    void removeUser(Long id);
    User updateUser(User user);
    User getUserById(Long id);
}

