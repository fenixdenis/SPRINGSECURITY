package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.kata.spring.boot_security.demo.Service.UserService;
import ru.kata.spring.boot_security.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class UsersController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String homePage(){
        return "home";
    }

    @GetMapping("/authenticated")
    public String pageForAuthen(Principal principal){
        User user = userService.findByUsername(principal.getName());
        //Authentication a = SecurityContextHolder.getContext().getAuthentication();
        return "spring SECURITY" + user.getUsername() + " " + user.getEmail();
    }
    @GetMapping("/read")
    public String pageForRead(){

        return "read";}

    @GetMapping("/adminpage")
    public String pageForadmin(){

        return "admin";

}}





