/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eos.pibe.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Tomas
 */
@Entity
@Table(name = "areas", schema = "public")
@NamedQueries({@NamedQuery(name = "Areas.findAll", query = "SELECT a FROM Areas a")})
public class Areas implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
        
    @Column(length = 32, name = "nombre_area", nullable = false)
    private String nombreArea;
   
    @Column(length = 32, name = "correo_area", nullable = false)
    private String correo_area;

    public Areas() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    public String getCorreo_area() {
        return correo_area;
    }

    public void setCorreo_area(String correo_area) {
        this.correo_area = correo_area;
    }
    
    
    
    
}
