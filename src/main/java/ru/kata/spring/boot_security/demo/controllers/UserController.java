package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.service.EntityUserDetailsService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final EntityUserDetailsService userDetailsService;
    @Autowired
    public UserController(UserService userService, EntityUserDetailsService userDetailsService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("")
    public String showUserInfo(Model model) {
        User currentUser = userDetailsService.getCurrentUser();
        List<User> userList = new ArrayList<>();
        userList.add(currentUser);

        model.addAttribute("user", currentUser);
        model.addAttribute("users", userList);
        return "user_pages/user_info";
    }


//todo: delete

//    @GetMapping("/id/{id}")
//    public String getUserInfo(@PathVariable("id") int id, Model model) {
//        User user = userService.getUserById(id);
//        model.addAttribute("users", user);
//        model.addAttribute("admin", true);
//        return "user_pages/user_info";
//    }
}
