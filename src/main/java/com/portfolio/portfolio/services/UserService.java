/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.portfolio.services;

import com.portfolio.portfolio.models.Perfil;
import com.portfolio.portfolio.models.User;
import java.util.List;

/**
 *
 * @author Joel
 */
public interface UserService {
    public User saveUser(User user);
    public User getUserById(Long id);
    public User getUserByName(String name);
    public void deleteUserById(Long id);
    public List<User> getListUserByPerfil(Perfil perfil);
}
