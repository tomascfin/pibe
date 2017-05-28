/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eos.pibe.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author tomas
 */
@Entity
@Table(name = "region", schema = "public")
public class Region implements Serializable {
    
    @Id
    @Column(name = "region_id", updatable = false, nullable = false)
    private int id;
    
    @Column(length = 50, name= "region_nombre", nullable = false)
    private String nombreRegion;
    
    @Column(length = 5, name= "ISO_3166_2_CL", nullable = false)
    private String isoNombre;
    
    

    public Region() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsoNombre() {
        return isoNombre;
    }

    public void setIsoNombre(String isoNombre) {
        this.isoNombre = isoNombre;
    }
    
   

    public String getNombreRegion() {
        return nombreRegion;
    }

    public void setNombreRegion(String nombreRegion) {
        this.nombreRegion = nombreRegion;
    }
   
}
