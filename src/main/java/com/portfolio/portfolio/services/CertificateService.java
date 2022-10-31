/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.portfolio.services;

import com.portfolio.portfolio.models.Certificate;

/**
 *
 * @author Joel
 */
public interface CertificateService {
     public Certificate saveCertificate(Certificate certificate);
    public Certificate getCertificateById(Long id);
    public void deleteCertificateById(Long id);
}
