/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.portfolio.services;

import com.portfolio.portfolio.models.Experience;

/**
 *
 * @author User
 */
public interface ExperienceService {
    public Experience saveExperience(Experience experience);
    public Experience getExperienceById(Long id);
    public void deleteExperienceById(Long id);

}
