/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.portfolio.models;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author User
 */
@Entity
@Table(name = "perfil")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String description;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Training> training;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Experience> experience;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Contact> contact;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Certificate> certificate;
    private byte[] imagen;
    private byte[] front_page;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Skill> skill;

    public byte[] getFront_page() {
        return front_page;
    }

    public void setFront_page(byte[] front_page) {
        this.front_page = front_page;
    }

    public List<Skill> getSkill() {
        return skill;
    }

    public void setSkill(List<Skill> skill) {
        this.skill = skill;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public Perfil(Long id, String name, String surname, String description, List<Training> training, List<Experience> experience, List<Contact> contact, List<Certificate> certificate, byte[] imagen, byte[] front_page, List<Skill> skill) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.description = description;
        this.training = training;
        this.experience = experience;
        this.contact = contact;
        this.certificate = certificate;
        this.imagen = imagen;
        this.front_page = front_page;
        this.skill = skill;
    }

    
    
    
   
    public Perfil() {
        
    }

    public List<Certificate> getCertificate() {
        return certificate;
    }

    public void setCertificate(List<Certificate> certificate) {
        this.certificate = certificate;
    }

    public List<Contact> getContact() {
        return contact;
    }

    public void setContact(List<Contact> contact) {
        this.contact = contact;
    }
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Training> getTraining() {
        return training;
    }

    public void setTraining(List<Training> training) {
        this.training = training;
    }

    

    public List<Experience> getExperience() {
        return experience;
    }

    public void setExperience(List<Experience> experience) {
        this.experience = experience;
    }
    
    
}
