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


    //todo: delete

    @GetMapping("/new")
    public String addNewUser(@ModelAttribute("user") User user) {
        return "admin_pages/new";
    }


    @PostMapping("/new")
    public String create(@ModelAttribute("newUser") @Valid User newUser,
                         BindingResult bindingResult) {


        userValidator.validate(newUser, bindingResult);

//        if (bindingResult.hasErrors()) return "admin_pages/admin";
//        System.out.println(roles);
//        newUser.setRoles(roles);

        userService.addAsUser(newUser);

        System.out.println(newUser.getRoles());

        return "redirect:/admin";
    }


//    @GetMapping("/{id}")
//    public String editUser(Model model, @PathVariable("id") int id) {
//        model.addAttribute("selectedUser", userService.getUserById(id));
//        return "redirect:/admin";
//    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute ("user") @Valid User updated,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {

//        userValidator.validate(user, bindingResult);
//        if (bindingResult.hasErrors()) return "admin_pages/edit";
        System.out.println(updated.getRoles());
        userService.update(id, updated);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        userService.delete(id);
        return "redirect:/admin";
    }



}
