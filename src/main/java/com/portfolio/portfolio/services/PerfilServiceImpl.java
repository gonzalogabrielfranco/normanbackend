/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.portfolio.services;

import com.portfolio.portfolio.models.Perfil;
import com.portfolio.portfolio.repositorys.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class PerfilServiceImpl implements PerfilService{
    @Autowired
    PerfilRepository repository;
    
    @Override
    public Perfil savePerfil(Perfil perfil) {
        return repository.save(perfil);
    }

    @Override
    public Perfil getPerfilById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public void deletePerfilById(Long id) {
        repository.deleteById(id); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
