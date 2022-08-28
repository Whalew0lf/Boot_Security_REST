package org.example.controller;

import org.example.model.Role;
import org.example.model.User;
import org.example.service.RoleService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public String printUsers(ModelMap model) {
        String username;
        String roles;

        Set<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        username = ((UserDetails) principal).getUsername();
        roles = ((User) principal).getRolesByString();
        model.put("currentUser", username);
        model.put("currentRoles", roles);
        return "users";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("userId") long id) {
        userService.removeUser(id);
        return "redirect:/admin/users";
    }

    @PostMapping("/edit")
    public String openUserUpdatePage(@RequestParam("userId") long id, Model model) {
        User user = new User(id);
        model.addAttribute("user", user);
        List<Role> roles= roleService.getAllRoles();
        model.addAttribute("roles",roles);
        return "edit";
    }

    @PostMapping("/update")
    public String editUser(User user) {
        System.out.println(user);
        userService.updateUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/create")
    public String openUserCreatePage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        List<Role> roles= roleService.getAllRoles();
        model.addAttribute("roles",roles);
        return "create";
    }

    @PostMapping("/create")
    public String addUser(User user) {
        userService.addUser(user);
        return "redirect:/admin/users";
    }
}
