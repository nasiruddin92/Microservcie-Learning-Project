package com.naba.tech.service.controllers;


import com.naba.tech.service.entities.User;
import com.naba.tech.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping
    ResponseEntity<User> createUser(@RequestBody User user){
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    ResponseEntity<User> getUserById(@PathVariable String userId){
        return new ResponseEntity<>(userService.getUserById(userId),HttpStatus.OK);
    }

    @GetMapping()
    ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }
}