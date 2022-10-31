/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portfolio.portfolio.services;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Joel
 */
public interface FileService {
    public void saveFile(MultipartFile file,String id) throws Exception;
}
