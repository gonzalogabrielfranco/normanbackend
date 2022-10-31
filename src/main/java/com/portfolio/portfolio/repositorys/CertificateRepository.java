/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.portfolio.repositorys;

import com.portfolio.portfolio.models.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Joel
 */
public interface CertificateRepository extends JpaRepository<Certificate, Long> {
   
}
