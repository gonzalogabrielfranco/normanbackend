/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.portfolio.services;

import com.portfolio.portfolio.models.Experience;
import com.portfolio.portfolio.repositorys.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class ExperienceServiceImpl implements ExperienceService{
    @Autowired
    ExperienceRepository repository;

    @Override
    public Experience saveExperience(Experience experience) {
        return repository.save(experience); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Experience getExperienceById(Long id) {
       return repository.findById(id).get(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteExperienceById(Long id) {
        repository.deleteById(id);
    }
    
    
}
