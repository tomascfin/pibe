/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eos.pibe.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Tomas
 */

@Entity
@Table(name = "historial_reclamos", schema="public")
public class HistorialReclamos implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_historial_reclamos", updatable = false, nullable = false)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reclamo", nullable = true)
    private Reclamo reclamo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area", nullable = true)
    private Areas area;
    
    @Column(name="fecha_historial")
    private java.sql.Timestamp fechaHistorial;

    public HistorialReclamos() {
    }

    public Timestamp getFechaHistorial() {
        return fechaHistorial;
    }

    public void setFechaHistorial(Timestamp fechaHistorial) {
        this.fechaHistorial = fechaHistorial;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Reclamo getReclamo() {
        return reclamo;
    }

    public void setReclamo(Reclamo reclamo) {
        this.reclamo = reclamo;
    }

    public Areas getArea() {
        return area;
    }

    public void setArea(Areas area) {
        this.area = area;
    }
    
    
   
    
    
     
}
