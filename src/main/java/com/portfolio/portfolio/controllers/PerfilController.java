/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.portfolio.controllers;

import com.portfolio.portfolio.dtos.Mail;
import com.portfolio.portfolio.dtos.UserClientDto;
import com.portfolio.portfolio.models.Contact;
import com.portfolio.portfolio.models.Perfil;
import com.portfolio.portfolio.models.User;
import com.portfolio.portfolio.services.PerfilService;
import com.portfolio.portfolio.services.SendMailService;
import com.portfolio.portfolio.services.UserService;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Joel
 */
@RestController
@CrossOrigin(origins="*")
public class PerfilController {
    @Autowired
    PerfilService service;
    
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    UserService userservice;
    
    @GetMapping("/perfil/{name}")
    public ResponseEntity<Perfil> obtenerPerfil(@PathVariable String name ){
        User user=userservice.getUserByName(name);
        if(user!=null){
            return new ResponseEntity(user.getPerfil(),HttpStatus.OK);
        }else{
            return new ResponseEntity(false,HttpStatus.OK);
        }
    }
    
    @GetMapping("/perfil/edit/{id}")
    @Secured("ADMIN")
    public ResponseEntity<List<UserClientDto>> obtenerPerfilEdit(@PathVariable Long id){
        Perfil perfil=service.getPerfilById(id);
        List<User> users= userservice.getListUserByPerfil(perfil);
        System.out.println(users.size()+" "+perfil.getId());
        List<UserClientDto> userdto= new ArrayList<>();
        if(users.size()>0){
            for(User user:users){
                userdto.add(new UserClientDto(user.getId(),user.getName()));
            }
            return new ResponseEntity(userdto,HttpStatus.OK);
        }else{
            return new ResponseEntity("false",HttpStatus.OK);
        }
        
    }
   
    @PostMapping("/save")
    @Secured("ADMIN")
    public Perfil savePerfil(@RequestBody Perfil perfil){
        return service.savePerfil(perfil);
    }
    @GetMapping("/GetImage/{id}")
    public @ResponseBody Map<String, String> getImage(@PathVariable Long id) throws IOException { 
        
        Perfil perfil=service.getPerfilById(id);
        if(perfil!=null){
            String encodeImage = Base64.getEncoder().withoutPadding().encodeToString(perfil.getImagen());
            String encodeImage2 = Base64.getEncoder().withoutPadding().encodeToString(perfil.getFront_page());
            Map<String, String> jsonMap = new HashMap<>(); 
            jsonMap.put("content", encodeImage); 
            jsonMap.put("content2", encodeImage2); 
 
            return jsonMap; 
        }else{
            return null;
        }
        //File file = new ClassPathResource("/src/main/resources/Image/Captura.PNG").getFile(); 
        
    }
    
    @PostMapping("/SaveImageProf/{id}")
    @Secured("ADMIN")
    public ResponseEntity<Perfil> saveFileProfile(@RequestParam("file") MultipartFile file,@PathVariable Long id){
        Perfil perfil=service.getPerfilById(id);
        if(perfil!=null){
            if(file!=null){
                
                try{
                    BufferedImage bufferedImageInput = ImageIO.read(file.getInputStream());
                    BufferedImage bufferedImageOutput = new BufferedImage(256,256, bufferedImageInput.getType());
                    Graphics2D g2d = bufferedImageOutput.createGraphics();
                    g2d.drawImage(bufferedImageInput, 0, 0, 256, 256, null);
                    g2d.dispose();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(bufferedImageOutput,"png", baos);
                    perfil.setImagen(baos.toByteArray());

                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            service.savePerfil(perfil);
            return new ResponseEntity(perfil,HttpStatus.OK);
        }else{
            return new ResponseEntity("false",HttpStatus.OK);
        }
        
    }
  
    @PostMapping("/SaveImageFront/{id}")
    @Secured("ADMIN")
    public ResponseEntity<Perfil> saveFileFrontPage(@RequestParam("file") MultipartFile file,@PathVariable Long id)throws IOException{
        Perfil perfil=service.getPerfilById(id);
        if(perfil!=null){
            if(file!=null){
                BufferedImage bufferedImageInput = ImageIO.read(file.getInputStream());
                BufferedImage bufferedImageOutput = new BufferedImage(960,360, bufferedImageInput.getType());
                Graphics2D g2d = bufferedImageOutput.createGraphics();
                g2d.drawImage(bufferedImageInput, 0, 0, 960, 360, null);
                g2d.dispose();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                try{

                    ImageIO.write(bufferedImageOutput,"png", baos);
                    perfil.setFront_page(baos.toByteArray());

                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            
            service.savePerfil(perfil);
            return new ResponseEntity(perfil,HttpStatus.OK);
        }else{
            return new ResponseEntity("false",HttpStatus.OK);
        }
        
    }
  
    @PostMapping("/send/mail/{id}")
    public ResponseEntity<String> sendMail(@RequestBody Mail mail,@PathVariable Long id){
        Perfil perfil= service.getPerfilById(id);
        if(perfil!=null){
            String email="";
            for(Contact item:perfil.getContact()){
                if(item.getType().equals("email")){
                    email=item.getDetails();
                }
            }
            if(!email.equals("")){
                SendMailService sev= new SendMailService(email, mail, javaMailSender);
                sev.start();
                return new ResponseEntity("true",HttpStatus.OK);
            }else{
                return new ResponseEntity("false",HttpStatus.OK);
            }
            
        }else{
            return new ResponseEntity("false",HttpStatus.OK);
            }
    }
} 


