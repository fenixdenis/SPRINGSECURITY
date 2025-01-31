package ru.kata.spring.boot_security.demo.controllers;

import ru.kata.spring.boot_security.demo.Service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.Service.UserService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.security.Principal;
import java.util.List;

@Controller
public class UsersController {

    private final RoleRepository roleRepository;
    private final RoleServiceImpl roleServiceImpl;
    private UserService userService;

    public UsersController(RoleServiceImpl roleServiceImpl, RoleRepository roleRepository) {
        this.roleServiceImpl = roleServiceImpl;
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String pageForadmin(ModelMap model){

        List<User> allUsers = userService.findAll();
        model.addAttribute("allUsers", allUsers);
        return "admin";
    }

        @GetMapping("/admin/showAllUsers")
        public String showAllUsers(ModelMap model) {

            List<User> allUsers = userService.findAll();
            model.addAttribute("allUsers", allUsers);
            return "users";
        }

        @GetMapping("/admin/addNewUser")
        public String addNewUser(ModelMap model){

            User user = new User();
            List<Role> allroles = roleRepository.findAll();
            model.addAttribute("userSave",user);
            model.addAttribute("allRoles", allroles);
            return "user-info";
        }

        @PostMapping("/admin/saveUser")
        public String saveUser(@ModelAttribute("userSave") User user ){
            
            userService.save(user);
            return "redirect:/admin";
        }

        @RequestMapping("/admin/updateUser")
        public String updateUser(@RequestParam("userId") int id,ModelMap model){

            User user = userService.getUser(id);
            List<Role> allroles = roleRepository.findAll();
            model.addAttribute("userSave",user);
            model.addAttribute("allRoles", allroles);
            return "user-info";
        }

        @RequestMapping("/admin/deleteUser")
        public String deleteUser(@RequestParam("userId") int id){

            userService.deleteById(id);
            return "redirect:/admin";
        }

        @GetMapping("/user")
        public String usersDetails(ModelMap model, Principal principal) {
            if (principal != null) {
                String username = principal.getName();
                User user = userService.findByUsername(username);
                model.addAttribute("user", user);
            }
            return "user";
        }


    @GetMapping("/login")
    public String login() {
        return "login"; // Возвращает шаблон login.html
    }
}










