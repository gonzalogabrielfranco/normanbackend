/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.portfolio.services;

import com.portfolio.portfolio.models.Certificate;
import com.portfolio.portfolio.repositorys.CertificateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joel
 */
@Service
public class CertificateServiceImpl implements CertificateService{

    @Autowired
    CertificateRepository repository;
    @Override
    public Certificate saveCertificate(Certificate certificate) {
        return repository.save(certificate);
    }

    @Override
    public Certificate getCertificateById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public void deleteCertificateById(Long id) {
        repository.deleteById(id);
    }
    
}
