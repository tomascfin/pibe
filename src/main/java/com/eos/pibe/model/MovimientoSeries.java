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
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Tomas
 */
@Entity
@Table(name = "movimiento_series", schema = "public")
@NamedQuery(name = "MovimientoSeries.findByNumeroDeSerie", query = "SELECT m FROM MovimientoSeries m WHERE m.numeroDeSerie = :valor")
public class MovimientoSeries implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_movimiento_series", updatable = false, nullable = false)
    private Long id;
    
    @Column(name="detalle_movimiento")
    private String detalleMovimiento;
    
    @Column(name="fecha_movimiento")
    private java.sql.Timestamp fechaMovimiento;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_entidad", nullable = true)
    private Entidad entidad;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "numero_de_serie", nullable = true)
    private NumerosDeSerie numeroDeSerie;
    
    @Column(name="tipo_movimiento")
    private String tipoMovimiento;
    
    @Column(name="usos")
    private int usos;
    
    @Column(name="nombre_contacto", length=64)
    private String nombreContacto;
    
    @Column(name="telefono_contacto", length=64)
    private String telefonoContacto;
    
    @Column(name="email_contacto", length=64)
    private String emailContacto;

    public MovimientoSeries() {
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getEmailContacto() {
        return emailContacto;
    }

    public void setEmailContacto(String emailContacto) {
        this.emailContacto = emailContacto;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetalleMovimiento() {
        return detalleMovimiento;
    }

    public void setDetalleMovimiento(String detalleMovimiento) {
        this.detalleMovimiento = detalleMovimiento;
    }

    public Timestamp getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Timestamp fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public Entidad getEntidad() {
        return entidad;
    }

    public void setEntidad(Entidad entidad) {
        this.entidad = entidad;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public NumerosDeSerie getNumeroDeSerie() {
        return numeroDeSerie;
    }

    public void setNumeroDeSerie(NumerosDeSerie numeroDeSerie) {
        this.numeroDeSerie = numeroDeSerie;
    }

    public int getUsos() {
        return usos;
    }

    public void setUsos(int usos) {
        this.usos = usos;
    }
    
    
    
    

}
