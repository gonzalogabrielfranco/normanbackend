package com.portfolio.portfolio.controllers;

import com.portfolio.portfolio.models.Perfil;
import com.portfolio.portfolio.models.Training;
import com.portfolio.portfolio.services.PerfilService;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Joel
 */
@RestController
@CrossOrigin(origins="*")
public class TrainingController {
    @Autowired
    PerfilService service;
    
    @PostMapping("/training/save/{id}")
    @Secured("ADMIN")
    public ResponseEntity<List<Training>>saveProfileTraining(@PathVariable Long id,@RequestBody Training training){
        Perfil perfil=service.getPerfilById(id);
        if(perfil!=null){
            try{
                if(training.getImagen()!=null){
                     BufferedImage bufferedImageInput = ImageIO.read(new ByteArrayInputStream(training.getImagen()));
        
                    BufferedImage bufferedImageOutput = new BufferedImage(256,256, bufferedImageInput.getType());
        
                    Graphics2D g2d = bufferedImageOutput.createGraphics();
                    g2d.drawImage(bufferedImageInput, 0, 0, 256, 256, null);
                    g2d.dispose();
        
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(bufferedImageOutput,"png", baos);
            
                    training.setImagen(baos.toByteArray());
                }
                perfil.getTraining().add(training);
                service.savePerfil(perfil);
                return new ResponseEntity(service.getPerfilById(id).getTraining(),HttpStatus.OK);
            }catch(IOException e){
                e.printStackTrace();
                return new ResponseEntity(false,HttpStatus.OK);
            }
        }else{
            return new ResponseEntity(false,HttpStatus.OK);
        }
       
    }

    @PostMapping("/training/update/{id}")
    @Secured("ADMIN")
    public ResponseEntity<List<Training>>updateProfileTraining(@PathVariable Long id,@RequestBody Training training){
        Perfil perfil=service.getPerfilById(id);
        if(perfil!=null){
            if(training.getImagen()!=null){
            
                try{
                    BufferedImage bufferedImageInput = ImageIO.read(new ByteArrayInputStream(training.getImagen()));
        
                    BufferedImage bufferedImageOutput = new BufferedImage(256,256, bufferedImageInput.getType());
        
                    Graphics2D g2d = bufferedImageOutput.createGraphics();
                    g2d.drawImage(bufferedImageInput, 0, 0, 256, 256, null);
                    g2d.dispose();
        
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(bufferedImageOutput,"png", baos);
            
                    training.setImagen(baos.toByteArray());
                
                
                }catch(IOException e){
                    e.printStackTrace();
                
                }
            }
            for(int i=0;i<perfil.getTraining().size();i++){
                if(training.getId()==perfil.getTraining().get(i).getId()){
                    if(training.getImagen()!=null){
                        perfil.getTraining().get(i).setImagen(training.getImagen());
                    }
                    perfil.getTraining().get(i).setAddress(training.getAddress());
                    perfil.getTraining().get(i).setDetails(training.getDetails());
                    perfil.getTraining().get(i).setFinishDate(training.getFinishDate());
                    perfil.getTraining().get(i).setInstitute(training.getInstitute());
                    perfil.getTraining().get(i).setStarDate(training.getStarDate());
                }
            }
            service.savePerfil(perfil);
            return new ResponseEntity(perfil.getTraining(),HttpStatus.OK);
        }else{
            return new ResponseEntity(false,HttpStatus.OK);
        }
       
    }

    @DeleteMapping("/training/delete/{id}/{idt}")
    @Secured("ADMIN")
    public ResponseEntity<List<Training>>deleteProfileTraining(@PathVariable Long id,@PathVariable Long idt){
        Perfil perfil=service.getPerfilById(id);
        
        if(perfil!=null){
            List<Training> list=new ArrayList<>();
            for(Training training: perfil.getTraining()){
                if(training.getId()!=idt){
                    list.add(training);
                }
            }
            perfil.setTraining(list);
            service.savePerfil(perfil);
            return new ResponseEntity(perfil.getTraining(),HttpStatus.OK);
        }else{
            return new ResponseEntity(false,HttpStatus.OK);
        }
    }
}
