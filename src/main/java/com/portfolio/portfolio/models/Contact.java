/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.portfolio.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Joel
 */
@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String details;
    private String type;
    private String source;
    private String source_card;
    public Contact(Long id, String details, String type) {
        this.id = id;
        this.details = details;
        this.type = type;
    }

    public Contact(Long id, String details, String type, String source, String source_card) {
        this.id = id;
        this.details = details;
        this.type = type;
        this.source = source;
        this.source_card = source_card;
    }

    public Contact(Long id, String details, String type, String source) {
        this.id = id;
        this.details = details;
        this.type = type;
        this.source = source;
    }

    public String getSource_card() {
        return source_card;
    }

    public void setSource_card(String source_card) {
        this.source_card = source_card;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
    
    public Contact() {
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
