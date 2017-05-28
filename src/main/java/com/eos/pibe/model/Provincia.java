/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eos.pibe.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author tomas
 */
@Entity
@Table(name = "provincia", schema = "public")
public class Provincia implements Serializable {
    
    @Id
    @Column(name = "provincia_id", updatable = false, nullable = false)
    private int id;
    
    @Column(length = 23, name= "provincia_nombre", nullable = false)
    private String nombreProvincia;
    
     @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name="region_id")
     private Region region;

    public Provincia() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreProvincia() {
        return nombreProvincia;
    }

    public void setNombreProvincia(String nombreProvincia) {
        this.nombreProvincia = nombreProvincia;
    }
     
     
 

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
     
     
    
    
    
}
