/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eos.pibe.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Tomas
 */
@Entity
@Table(name = "agendamiento", schema = "public")
@NamedQueries({@NamedQuery(name = "Agendamiento.findAll", query = "SELECT a FROM Agendamiento a"),
@NamedQuery(name = "Agendamiento.findBy", query = "SELECT a FROM Agendamiento a WHERE a.id = :valor ")})
public class Agendamiento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    /*@Column(name = "inicio_fecha", insertable = false, updatable = true)
    @Temporal(TemporalType.DATE)
    private Date inicioFecha;

    @Column(name = "inicio_horario", insertable = false, updatable = true)
    @Temporal(TemporalType.TIME)
    private Date inicioHorario;

    @Basic(optional = false)
    @Column(name = "fecha_prueba", insertable = false, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPrueba;*/

    @Column(name = "prioridad", nullable = false, length = 128)
    private int prioridad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_entidad", nullable = true)
    private Entidad entidad;

    @Column(name = "detalle_agendamiento", length = 1024, nullable = true)
    private String detalleagendamiento;

    @Column(name = "telefono_contacto", length = 128, nullable = true)
    private String telefonoContacto;

    @Column(name = "nombre_contacto", length = 128, nullable = true)
    private String nombreContacto;

    @Column(name = "email_contacto", length = 128, nullable = true)
    private String emailContacto;

    @Column(name = "fecha_inicio")
    private java.sql.Timestamp fechaInicio;
    
    @Column(name = "fecha_termino")
    private java.sql.Timestamp fechaTermino;

    public Agendamiento() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public Entidad getEntidad() {
        return entidad;
    }

    public void setEntidad(Entidad entidad) {
        this.entidad = entidad;
    }

    public String getDetalleagendamiento() {
        return detalleagendamiento;
    }

    public void setDetalleagendamiento(String detalleagendamiento) {
        this.detalleagendamiento = detalleagendamiento;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getEmailContacto() {
        return emailContacto;
    }

    public void setEmailContacto(String emailContacto) {
        this.emailContacto = emailContacto;
    }

    public Timestamp getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Timestamp fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Timestamp getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Timestamp fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

   

    
}
