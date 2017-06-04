/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eos.pibe.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Tomas
 */
@Entity
@Table(name = "reclamo", schema = "public")
public class Reclamo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_entidad")
    private Entidad entidad;
    @Column(length = 64, name = "nombre_contacto", nullable = false)
    private String nombreContacto;
    @Column(length = 32, name = "numero_contacto", nullable = false)
    private String numeroContacto;
    @Column(length = 32, name = "email_contacto", nullable = false)
    private String emailContacto;
    @Column(length = 32, name = "tipo_reclamo", nullable = false)
    private String tipoReclamo;
    @Column(length = 1024, name = "detalle_reclamo", nullable = false)
    private String detalleReclamo;
    @Column(length = 64, name = "ruta_archivo", nullable = false)
    private String rutaArchivo;
    @Column(name = "fecha_reclamo", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaReclamo;

    public Reclamo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Entidad getEntidad() {
        return entidad;
    }

    public void setEntidad(Entidad entidad) {
        this.entidad = entidad;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getNumeroContacto() {
        return numeroContacto;
    }

    public void setNumeroContacto(String numeroContacto) {
        this.numeroContacto = numeroContacto;
    }

    public String getEmailContacto() {
        return emailContacto;
    }

    public void setEmailContacto(String emailContacto) {
        this.emailContacto = emailContacto;
    }

    public String getTipoReclamo() {
        return tipoReclamo;
    }

    public void setTipoReclamo(String tipoReclamo) {
        this.tipoReclamo = tipoReclamo;
    }

    public String getDetalleReclamo() {
        return detalleReclamo;
    }

    public void setDetalleReclamo(String detalleReclamo) {
        this.detalleReclamo = detalleReclamo;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public Date getFechaReclamo() {
        return fechaReclamo;
    }

    public void setFechaReclamo(Date fechaReclamo) {
        this.fechaReclamo = fechaReclamo;
    }
    
    
}
