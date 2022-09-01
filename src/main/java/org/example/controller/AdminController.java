package org.example.controller;

import org.example.model.Role;
import org.example.model.User;
import org.example.service.RoleService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView openAdminPage(@AuthenticationPrincipal UserDetails userDetails) {
        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("users", userService.getAllUsers());
        modelAndView.addObject("currentUser", userDetails.getUsername());
        modelAndView.addObject("currentRoles", ((User) userDetails).getRolesByString());
        modelAndView.addObject("editUser", new User());
        return modelAndView;
    }

    @PostMapping("/delete")
    public ModelAndView deleteUser(@RequestParam("userId") long id) {
        userService.removeUser(id);
        return new ModelAndView("redirect:/admin/users");
    }

    @PostMapping("/update")
    public ModelAndView editUser(User user) {
        userService.updateUser(user);
        return new ModelAndView("redirect:/admin/users");
    }

    @GetMapping("/create")
    public ModelAndView openUserCreatePage(@AuthenticationPrincipal UserDetails userDetails) {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("user", new User());
        modelAndView.addObject("currentUser", userDetails.getUsername());
        modelAndView.addObject("currentRoles", ((User) userDetails).getRolesByString());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView addUser(User user) {
        userService.addUser(user);
        return new ModelAndView("redirect:/admin/users");
    }
}
