package org.example.controller;

import org.example.model.User;
import org.example.service.RoleService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/users")
public class RestController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public RestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping(path = "/all",
            method = {RequestMethod.GET})
    public Set<User> getAllUser() {
        return userService.getAllUsers();
    }

    @RequestMapping(path = "",
            method = {RequestMethod.POST})
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @RequestMapping(path = "/{id}",
            method = {RequestMethod.DELETE})
    public String deleteUser(@PathVariable Long id) {
        userService.removeUser(id);
        return null;
    }

    @RequestMapping(path = "/current",
            method = {RequestMethod.GET})
    public UserDetails getCurrentUser(@AuthenticationPrincipal UserDetails user) {
        return user;
    }

    @RequestMapping(path = "",
            method = {RequestMethod.PUT})
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @RequestMapping (path = "/{id}"
            , method = {RequestMethod.GET})
    public User getUserById(@PathVariable(name = "id") Long id) {
        return userService.getUserById(id);
    }
}
