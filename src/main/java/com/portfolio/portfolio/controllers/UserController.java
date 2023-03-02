/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.portfolio.controllers;

import com.portfolio.portfolio.models.User;
import com.portfolio.portfolio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Joel
 */
@RestController
@CrossOrigin(origins="*")
public class UserController {
    @Autowired
    UserService service;
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @PostMapping("/auth/save/user")
    public ResponseEntity<Boolean> saveUser(@RequestBody User user){
        User n_user=service.getUserByName(user.getName());
        if(n_user==null){
            String pass=passwordEncoder.encode(user.getPassword());
            user.setPassword(pass);
            service.saveUser(user);
            return new ResponseEntity(true,HttpStatus.OK);
        }else{
            return new ResponseEntity(false,HttpStatus.OK);
        }
       
    }
}
