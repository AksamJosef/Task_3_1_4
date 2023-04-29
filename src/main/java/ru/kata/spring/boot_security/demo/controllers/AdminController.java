package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.service.EntityUserDetailsService;
import ru.kata.spring.boot_security.demo.service.RolesService;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.validators.UserValidator;

import javax.validation.Valid;
import java.util.Set;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final UserValidator userValidator;
    private final EntityUserDetailsService userDetailsService;
    private final RolesService rolesService;

    @Autowired
    public AdminController(UserService userService, UserValidator userValidator, EntityUserDetailsService userDetailsService, RolesService rolesService) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.userDetailsService = userDetailsService;
        this.rolesService = rolesService;
    }

    @GetMapping("")
    public String getAdminPage(Model model) {
        model.addAttribute("users", userService.getUserList());
        model.addAttribute("user", userDetailsService.getCurrentUser());
        model.addAttribute("roles", rolesService.getAllRoles());
        model.addAttribute("newUser", new User());
        return "admin_pages/admin";
    }


    @PostMapping("/new")
    public String create(@ModelAttribute("newUser") User newUser) {

        userService.addAsUser(newUser);
        return "redirect:/admin";
    }


    @PatchMapping("/{id}")
    public String update(@ModelAttribute ("user") User updated,
                         @PathVariable("id") int id) {
        userService.update(id, updated);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        userService.delete(id);
        return "redirect:/admin";
    }



}
