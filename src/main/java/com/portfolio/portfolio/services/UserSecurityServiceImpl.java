/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.portfolio.services;

import com.portfolio.portfolio.models.Role;
import com.portfolio.portfolio.models.User;
import com.portfolio.portfolio.repositorys.UserRepository;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityServiceImpl implements UserSecurityService {
    @Autowired
    UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		User usuario = repository.findByName(name);
                System.out.println("cantidad de roles "+usuario.getRole().size());
                if(usuario == null) {
			throw new UsernameNotFoundException("Usuario o password inválidos");
		}
		return new org.springframework.security.core.userdetails.User(usuario.getName(),usuario.getPassword(), mapearAutoridadesRoles(usuario.getRole()));
	}

	private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Role> roles){
                System.out.println("user: " + roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()).toString());
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

    
}
