package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String printUsers(ModelMap model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("userId") long id) {
        userService.removeUser(id);
        return "redirect:/users";
    }

    @PostMapping("/edit")
    public String openUserUpdatePage(@RequestParam("userId") long id, Model model) {
        User user = new User();
        user.setId(id);
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping("/update")
    public String editUser(User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping("/create")
    public String openUserCreatePage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "create";
    }

    @PostMapping("/create")
    public String addUser(User user) {
        userService.addUser(user);
        return "redirect:/users";
    }
}
