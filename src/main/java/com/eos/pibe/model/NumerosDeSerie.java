package com.eos.pibe.model;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.Version;

@Entity
@Table(name = "numero_de_series", schema = "public")
@NamedQueries({@NamedQuery(name = "Series.findAll", query = "SELECT s FROM NumerosDeSerie s WHERE s.activado =:activado"),
@NamedQuery(name = "Series.findByEntidad", query = "SELECT s FROM NumerosDeSerie s WHERE s.entidad = :valor")})
public class NumerosDeSerie implements Serializable {

    @Id
    @Column(length = 64, name = "id", updatable = false, nullable = false)
    private String id;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_entidad", nullable = true)
     private Entidad entidad;

    @Column(name = "usos", nullable = true)
    private int usos;

    @Column(name = "activado", nullable = false)
    private boolean activado = false;

    @Column(name = "anulado_por_reinstalacion", nullable = false)
    private boolean anuladoPorReinstalacion = false;
    
    @Column(name = "fecha_ingreso", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaIngreso;

    public NumerosDeSerie() {
    }

    public Entidad getEntidad() {
        return entidad;
    }

    public void setEntidad(Entidad entidad) {
        this.entidad = entidad;
    }
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getUsos() {
        return usos;
    }

    public void setUsos(int usos) {
        this.usos = usos;
    }

    public boolean isActivado() {
        return activado;
    }

    public void setActivado(boolean activado) {
        this.activado = activado;
    }

    public boolean isAnuladoPorReinstalacion() {
        return anuladoPorReinstalacion;
    }

    public void setAnuladoPorReinstalacion(boolean anuladoPorReinstalacion) {
        this.anuladoPorReinstalacion = anuladoPorReinstalacion;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

}
