/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.portfolio.services;

import com.portfolio.portfolio.dtos.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joel
 */

public class SendMailService extends Thread{

    String to;
    Mail mail;
    private JavaMailSender javaMailSender;

    public SendMailService(String to, Mail mail, JavaMailSender javaMailSender) {
        this.to = to;
        this.mail=mail;
        this.javaMailSender = javaMailSender;
    }
    
    

    public void sendMail() {

        System.out.println("creando mail");
        SimpleMailMessage mail = new SimpleMailMessage();
        System.out.println("from");
        mail.setFrom("gen.code.login@gmail.com");
        System.out.println("to");
        mail.setTo(to);
        System.out.println("mail");
        mail.setSubject(this.mail.getSubject());
        System.out.println("creand");
        mail.setText(this.mail.getMessage()+"\n\n"+"From: "+this.mail.getEmail());
        javaMailSender.send(mail);
        
        System.out.println("creando mail2");
        SimpleMailMessage mail2 = new SimpleMailMessage();
        System.out.println("from2");
        mail2.setFrom("gen.code.login@gmail.com");
        System.out.println("to2");
        mail2.setTo(this.mail.getEmail());
        System.out.println("mail2");
        mail2.setSubject(this.mail.getSubject());
        System.out.println("creand2");
        mail2.setText("You sent an email with the following message:\n"+this.mail.getMessage());
        javaMailSender.send(mail2);
    }
    @Override
    public void run(){
        sendMail();
    }
}
