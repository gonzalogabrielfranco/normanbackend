/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.portfolio.services;

import com.portfolio.portfolio.models.Training;
import com.portfolio.portfolio.repositorys.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class TrainingServiceImpl implements TrainingService{

    @Autowired
    TrainingRepository repository;
    @Override
    public Training saveTraining(Training training) {
        return repository.save(training);
    }

    @Override
    public Training getTrainingById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public void deleteTrainingById(Long id) {
        repository.deleteById(id);
    }
    
}
