/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.portfolio.controllers;

import com.portfolio.portfolio.dtos.UserLogin;
import com.portfolio.portfolio.models.Jwt;
import com.portfolio.portfolio.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Joel
 */
@RestController
@CrossOrigin(origins="*")
public class SecurityController {
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    JwtProvider jwtProvider;
    
    @PostMapping("/auth/login")
    public ResponseEntity<Jwt> login(@RequestBody UserLogin loginUsuario){
        
        System.out.println("Aqui 0 " +loginUsuario.getName()+"---"+loginUsuario.getPasword());
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getName(), loginUsuario.getPasword()));
        System.out.println("Aqui 1" + authentication.getDetails());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("Aqui 2");
        String jwt = jwtProvider.generateToken(loginUsuario.getName());
        System.out.println("Aqui 3");
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        System.out.println("userDetails: "+userDetails.toString());
        Jwt jwtDto = new Jwt(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }    
        
   
}
