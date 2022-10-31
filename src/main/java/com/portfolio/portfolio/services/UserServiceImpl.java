/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.portfolio.services;

import com.portfolio.portfolio.models.Experience;
import com.portfolio.portfolio.models.Perfil;
import com.portfolio.portfolio.models.User;
import com.portfolio.portfolio.repositorys.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joel
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository repository;
    @Override
    public User saveUser(User user) {
        return repository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public void deleteUserById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<User> getListUserByPerfil(Perfil perfil) {
        User user=new User();
        user.setPerfil(perfil);
        Example<User> example=Example.of(user);
        return repository.findAll(example);
//        List<User> list=repository.findAll();
//	List<User> list2=new ArrayList<>();
//	for(User item:list) {
//            if(item.getPerfil().getId()==perfil.getId() ) {
//		list2.add(item);
//            }
//        }
//        return list2;
    }

    @Override
    public User getUserByName(String name) {
        return repository.findByName(name);
    }
    

}
