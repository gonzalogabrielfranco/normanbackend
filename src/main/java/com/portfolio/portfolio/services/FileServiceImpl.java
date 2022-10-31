/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.portfolio.services;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Joel
 */
@Service
public class FileServiceImpl implements FileService{

    @Override
    public void saveFile(MultipartFile file,String id) {
        System.out.println(Paths.get(file.getOriginalFilename()));
        File fi=new File("src\\main\\resources\\Image\\"+id+".png");
        try {
            fi.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
        Files.copy(file.getInputStream(),Paths.get("src\\main\\resources\\Image\\"+id+".png"));
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}
