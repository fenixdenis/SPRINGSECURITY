package ru.kata.spring.boot_security.demo.controllers;



import ru.kata.spring.boot_security.demo.Service.RoleService;
import ru.kata.spring.boot_security.demo.Service.UserService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class UsersController {

    private final RoleRepository roleRepository;
    private final RoleService roleService;
    private UserService userService;

    public UsersController(RoleService roleService, RoleRepository roleRepository) {
        this.roleService = roleService;
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/adminpage")
    public String pageForadmin(ModelMap model){

        List<User> allUsers = userService.findAll();
        model.addAttribute("allUsers", allUsers);
        return "admin";
    }

        @GetMapping("/adminpage/showAllUsers")
        public String showAllUsers(ModelMap model) {

            List<User> allUsers = userService.findAll();
            model.addAttribute("allUsers", allUsers);
            return "users";
        }

        @GetMapping("/adminpage/addNewUser")
        public String addNewUser(ModelMap model){

            User user = new User();
            model.addAttribute("userSave", user);
            return "user-info";
        }

        @PostMapping("/adminpage/saveUser")
        public String saveUser(@ModelAttribute("userSave") User user ){
            
            userService.save(user);
            return "redirect:/adminpage";
        }

        @RequestMapping("/adminpage/updateUser")
        public String updateUser(@RequestParam("userId") int id,ModelMap model){

            User user = userService.getUser(id);
            List<Role> allroles = roleRepository.findAll();
            model.addAttribute("userSave",user);
            model.addAttribute("allRoles", allroles);
            return "user-info";
        }

        @RequestMapping("/adminpage/deleteUser")
        public String deleteUser(@RequestParam("userId") int id){

            userService.deleteById(id);
            return "redirect:/adminpage";
        }

        @GetMapping("/userdetails")
        public String usersDetails(ModelMap model, Principal principal) {
            if (principal != null) {
                String username = principal.getName();
                User user = userService.findByUsername(username);
                model.addAttribute("user", user);
            }
            return "userdetails";
        }
}










