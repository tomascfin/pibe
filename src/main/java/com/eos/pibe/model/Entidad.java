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
import javax.persistence.Table;

/**
 *
 * @author tomas
 */
@Entity
@Table(name = "entidad", schema = "public")
public class Entidad implements Serializable {
    
        @Id
	@Column(length = 32, name = "id_entidad", updatable = false, nullable = false)
	private String id;
        
        @Column(length = 64, name = "nombre_entidad", nullable = false)
        private String nombreEntidad;
        
        @Column(length = 64, name = "direccion_entidad", nullable = false)
        private String direccionEntidad;
        
         /*@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_contacto")
        private Contacto contacto;*/
        
        @Column(length = 64, name = "nombre_contacto", nullable = false)
        private String nombreContacto;
        
        @Column(length = 64, name = "telefono_contacto", nullable = false)
        private String telefonoContacto;
        
        @Column(length = 64, name = "email_contacto", nullable = false)
        private String emailContacto;
        
         @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="comuna_id")
        private Comuna comuna;

    public Entidad() {
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
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreEntidad() {
        return nombreEntidad;
    }

    public void setNombreEntidad(String nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
    }

    public String getDireccionEntidad() {
        return direccionEntidad;
    }

    public void setDireccionEntidad(String direccionEntidad) {
        this.direccionEntidad = direccionEntidad;
    }

    /*public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }*/

    public Comuna getComuna() {
        return comuna;
    }

    public void setComuna(Comuna comuna) {
        this.comuna = comuna;
    }
         
         
        
        
    
}
