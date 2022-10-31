/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.portfolio.repositorys;

import com.portfolio.portfolio.models.Perfil;
import com.portfolio.portfolio.models.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joel
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    public User findByName(String name);
    public List<User> findByPerfil(Perfil perfil);
}
