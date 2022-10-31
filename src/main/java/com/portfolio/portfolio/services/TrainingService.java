/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.portfolio.services;

import com.portfolio.portfolio.models.Training;

/**
 *
 * @author User
 */
public interface TrainingService {
    public Training saveTraining(Training training);
    public Training getTrainingById(Long id);
    public void deleteTrainingById(Long id);

}
