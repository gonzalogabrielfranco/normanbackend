/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.portfolio.controllers;

import com.portfolio.portfolio.models.Experience;
import com.portfolio.portfolio.models.Perfil;
import com.portfolio.portfolio.models.Training;
import com.portfolio.portfolio.services.PerfilService;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Joel
 */
@RestController
@CrossOrigin(origins="*")
public class ExperienceController {
    @Autowired
    PerfilService service;
    
    @PostMapping("/experience/save/{id}")
    @Secured("ADMIN")
    public ResponseEntity<List<Experience>>saveProfileExperience(@PathVariable Long id,@RequestBody Experience experience){
        Perfil perfil=service.getPerfilById(id);
        if(perfil!=null){
            try{
                if(experience.getImagen()!=null){
                    BufferedImage bufferedImageInput = ImageIO.read(new ByteArrayInputStream(experience.getImagen()));
        
                    BufferedImage bufferedImageOutput = new BufferedImage(256,256, bufferedImageInput.getType());
        
                    Graphics2D g2d = bufferedImageOutput.createGraphics();
                    g2d.drawImage(bufferedImageInput, 0, 0, 256, 256, null);
                    g2d.dispose();
        
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(bufferedImageOutput,"png", baos);
            
                    experience.setImagen(baos.toByteArray());
                }
                
                
                perfil.getExperience().add(experience);
                service.savePerfil(perfil);
                return new ResponseEntity(service.getPerfilById(id).getExperience(),HttpStatus.OK);
            }catch(IOException e){
                e.printStackTrace();
                return new ResponseEntity(false,HttpStatus.OK);
            }
        }else{
            return new ResponseEntity(false,HttpStatus.OK);
        }
       
    }

    @PostMapping("/experience/update/{id}")
    @Secured("ADMIN")
    public ResponseEntity<List<Experience>>updateProfileExperience(@PathVariable Long id,@RequestBody Experience experience){
        Perfil perfil=service.getPerfilById(id);
        if(perfil!=null){
            if(experience.getImagen()!=null){
            
                try{
                    BufferedImage bufferedImageInput = ImageIO.read(new ByteArrayInputStream(experience.getImagen()));
        
                    BufferedImage bufferedImageOutput = new BufferedImage(256,256, bufferedImageInput.getType());
        
                    Graphics2D g2d = bufferedImageOutput.createGraphics();
                    g2d.drawImage(bufferedImageInput, 0, 0, 256, 256, null);
                    g2d.dispose();
        
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(bufferedImageOutput,"png", baos);
            
                    experience.setImagen(baos.toByteArray());
                
                
                }catch(IOException e){
                    e.printStackTrace();
                
                }
            }
            for(int i=0;i<perfil.getExperience().size();i++){
                if(experience.getId()==perfil.getExperience().get(i).getId()){
                    if(experience.getImagen()!=null){
                        perfil.getExperience().get(i).setImagen(experience.getImagen());
                    }
                    
                    perfil.getExperience().get(i).setBusiness(experience.getBusiness());
                    perfil.getExperience().get(i).setDetails(experience.getDetails());
                    perfil.getExperience().get(i).setFinishDate(experience.getFinishDate());
                    perfil.getExperience().get(i).setJob(experience.getJob());
                    perfil.getExperience().get(i).setPosition(experience.getPosition());
                    perfil.getExperience().get(i).setStarDate(experience.getStarDate());
                }
            }
            service.savePerfil(perfil);
            return new ResponseEntity(perfil.getExperience(),HttpStatus.OK);
        }else{
            return new ResponseEntity(false,HttpStatus.OK);
        }
       
    }

    @DeleteMapping("/experience/delete/{id}/{ide}")
    @Secured("ADMIN")
    public ResponseEntity<List<Training>>deleteProfileExperience(@PathVariable Long id,@PathVariable Long ide){
        Perfil perfil=service.getPerfilById(id);
        
        if(perfil!=null){
            List<Experience> list=new ArrayList<>();
            for(Experience  experience: perfil.getExperience()){
                if(experience.getId()!=ide){
                    list.add(experience);
                }
            }
            perfil.setExperience(list);
            service.savePerfil(perfil);
            return new ResponseEntity(perfil.getExperience(),HttpStatus.OK);
        }else{
            return new ResponseEntity(false,HttpStatus.OK);
        }
    }

}
