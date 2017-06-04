/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eos.pibe.model;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Tomas
 */
public class DetalleReclamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reclamo")
    private Reclamo reclamo;
    @Column(length = 32, name = "estado_reclamo", nullable = false)
    private String estadoReclamo;
    @Column(length = 1024, name = "observacion_reclamo", nullable = false)
    private String observacionReclamo;
    @Column(length = 16, name = "id_usuario", nullable = false)
    private String idUsuario;

    public DetalleReclamo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Reclamo getReclamo() {
        return reclamo;
    }

    public void setReclamo(Reclamo reclamo) {
        this.reclamo = reclamo;
    }

    public String getEstadoReclamo() {
        return estadoReclamo;
    }

    public void setEstadoReclamo(String estadoReclamo) {
        this.estadoReclamo = estadoReclamo;
    }

    public String getObservacionReclamo() {
        return observacionReclamo;
    }

    public void setObservacionReclamo(String observacionReclamo) {
        this.observacionReclamo = observacionReclamo;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    

}
