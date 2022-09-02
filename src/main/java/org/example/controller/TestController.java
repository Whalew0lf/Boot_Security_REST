package org.example.controller;

import org.example.model.User;
import org.example.service.RoleService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class TestController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public TestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping(path = "/get-all",
            method = {RequestMethod.POST})
    @ResponseBody
    public Set<User> getAllUser() {
        return userService.getAllUsers();
    }

    @RequestMapping(path = "/add",
            method = {RequestMethod.POST})
    public String addUser(@RequestBody User user) {
        userService.addUser(user);
        return "success";
    }

    @RequestMapping(path = "/delete",
            method = {RequestMethod.POST})
    public String deleteUser( Long id) {
        userService.removeUser(id);
        return "success";
    }

}
