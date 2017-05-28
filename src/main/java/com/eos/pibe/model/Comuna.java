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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author tomas
 */
@Entity
@Table(name = "comuna", schema = "public")
public class Comuna implements Serializable {
    
      @Id
    @Column(name = "comuna_id", updatable = false, nullable = false)
    private int id;
    
    @Column(length = 20, name= "comuna_nombre", nullable = false)
    private String nombreCiudad;
    
     @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="provincia_id")
     private Provincia provincia;

    public Comuna() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }
     
     
    
}
