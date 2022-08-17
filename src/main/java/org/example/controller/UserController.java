package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @GetMapping("/admin/users")
    public String printUsers(ModelMap model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @PostMapping("/admin/delete")
    public String deleteUser(@RequestParam("userId") long id) {
        userService.removeUser(id);
        return "redirect:/admin/users";
    }

    @PostMapping("/admin/edit")
    public String openUserUpdatePage(@RequestParam("userId") long id, Model model) {
        User user = new User();
        user.setId(id);
        model.addAttribute("user", user);
        model.addAttribute("test", new Boolean(true));
        return "edit";
    }

    @PostMapping("/admin/update")
    public String editUser(User user) {
        userService.updateUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/create")
    public String openUserCreatePage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "create";
    }

    @PostMapping("/admin/create")
    public String addUser(User user) {
        userService.addUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/user")
    public String showUserPage(ModelMap modelMap) {
        String username;
        Object principal = SecurityContextHolder. getContext(). getAuthentication(). getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal). getUsername();
        } else {
            username = principal. toString();
        }
        User currentUser = (User) userService.loadUserByUsername(username);
        modelMap.put("user", currentUser);
        return "user";
    }
}
