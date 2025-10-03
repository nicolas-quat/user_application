package com.user_application.controller;

import com.user_application.entity.Utilisateur;
import com.user_application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public Utilisateur createUser(@RequestBody Utilisateur user){
        return userService.createUser(user);
    }


    @GetMapping("/{name}")
    public Utilisateur getUser(@PathVariable String name){
        return userService.findByName(name);
    }
}
