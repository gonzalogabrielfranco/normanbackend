/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.portfolio.services;

import com.portfolio.portfolio.models.Perfil;

/**
 *
 * @author User
 */
public interface PerfilService {
    public Perfil savePerfil(Perfil perfil);
    public Perfil getPerfilById(Long id);
    public void deletePerfilById(Long id);
}
