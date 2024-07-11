package com.example.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.db1.entity.User1;
import com.example.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/db1")
    public User1 createUserInDb(@RequestBody String name) {
        return userService.createUserInDb(name);
    }

 

    @GetMapping("/db1/{id}")
    public User1 getUserFromDb(@PathVariable Long id) {
        return userService.getUserFromDb(id);
    }
    
    

    @GetMapping("/db1/{name}/{age}")
    public List<User1> getUserFromDb2(@PathVariable String name, @PathVariable int age) {
        return userService.getUserFromDb2(name, age);
    }

    
}




