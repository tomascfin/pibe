/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eos.pibe.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Tomas
 */
@Entity
@Table(name = "agendamiento", schema = "public")
@NamedQuery(name = "Agendamiento.findAll", query = "SELECT a FROM Agendamiento a")
public class Agendamiento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 128)
    private String nombre;

    @Basic(optional = false)
    @Column(name = "inicio_fecha", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date inicioFecha;

    @Basic(optional = false)
    @Column(name = "fin_fecha", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date finFecha;

    public Agendamiento() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getInicioFecha() {
        return inicioFecha;
    }

    public void setInicioFecha(Date inicioFecha) {
        this.inicioFecha = inicioFecha;
    }

    public Date getFinFecha() {
        return finFecha;
    }

    public void setFinFecha(Date finFecha) {
        this.finFecha = finFecha;
    }
    
    

}
