/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eos.pibe.services;

import com.eos.pibe.model.Comuna;
import com.eos.pibe.model.Entidad;
import com.eos.pibe.model.NumerosDeSerie;
import com.eos.pibe.model.Provincia;
import com.eos.pibe.model.Region;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.stream.JsonGenerator;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author tomas
 */
@Stateless
public class ServiciosRest {

    @PersistenceContext(unitName = "pibe_db")
    private EntityManager em;

    public void obtenerListadoSeries(OutputStream ou) {

    }

    public void listarComunas(OutputStream os) {

        List<Region> regiones = obtenerRegiones(em);
        //Collections.sort(comunas, (Comuna p1, Comuna p2) -> p1.getNombreCiudad().compareTo(p2.getNombreCiudad()));
        JsonGenerator gen = Json.createGenerator(os);

        List<Region> regiones2 = obtenerRegiones(em);
        List<Provincia> provincias2 = obtenerProvincias(em);
        List<Comuna> comunas2 = obtenerComunas(em);
        gen.writeStartObject();
        gen.writeStartArray("Comunas");
        for (Comuna comuna : comunas2) {
             gen.writeStartObject();
            gen.write("display", comuna.getNombreCiudad());
            gen.write("value" , comuna.getId());
            gen.write("provincia", comuna.getProvincia().getNombreProvincia());
            gen.write("region", comuna.getProvincia().getRegion().getNombreRegion());
            
           /* gen.writeStartArray("");
            gen.writeStartObject();
            gen.write("nombre", comuna.getNombreCiudad());
            gen.write("id", comuna.getId());*/

/*            Query query = em.createQuery("SELECT p FROM Provincia p WHERE p.comuna = :comuna");
            query.setParameter("comuna", comuna.getId());
            Provincia provincia2 = (Provincia) query.getSingleResult();
            System.out.println(provincia2.getNombreProvincia());*/
            gen.writeEnd();
        }
        gen.writeEnd();
         gen.writeEnd();
        gen.flush();
        gen.close();
        /*gen.writeStartObject();
        gen.writeStartArray("Regiones");
        for (Region region : regiones) {
            List<Provincia> provincias = obtenerProvinciaPorRegion(em, region);
            gen.writeStartObject();
            gen.writeStartArray(region.getNombreRegion());
            for (Provincia provincia : provincias) {
                gen.writeStartObject();
                gen.writeStartArray(provincia.getNombreProvincia());
                List<Comuna> comunas = obtenerCiudadPorProvincia(em, provincia);
                for (Comuna comuna : comunas) {
                    gen.writeStartObject();
                    gen.write("display", comuna.getNombreCiudad());
                    gen.write("value", comuna.getId());
                    gen.writeEnd();
                }
                gen.writeEnd();
                gen.writeEnd();
            }
            gen.writeEnd();
            gen.writeEnd();
        }
        gen.writeEnd();
        gen.writeEnd();
        gen.flush();
        gen.close();
*/
    }

    public void registrarEntidad(JsonObject json) {
        Entity entity = null;
        Entidad entidad = new Entidad();
        try {

            Comuna comuna = em.find(Comuna.class, json.getJsonNumber("comunaId").intValue());
            entidad.setComuna(comuna);
            entidad.setEmailContacto(json.getString("email"));
            entidad.setNombreEntidad(json.getString("nombreEntidad"));
            entidad.setDireccionEntidad(json.getString("direccion"));
            entidad.setNombreContacto(json.getString("nombreContacto"));
            entidad.setTelefonoContacto(json.getString("telefonoContacto"));
            entidad.setId(json.getString("idEntidad"));
            em.persist(entidad);

        } catch (Exception e) {
            System.out.println("Error en la bd es: " + e.getMessage());
        }
        //entidad.setNombreContacto(json.getString("nombre contacto"));
    }
    
    public void registrarSerie(NumerosDeSerie serie) {
       
        try {

            
            em.persist(serie);

        } catch (Exception e) {
            System.out.println("Error en la bd es: " + e.getMessage());
        }
        //entidad.setNombreContacto(json.getString("nombre contacto"));
    }

    /*public static List<Producto> obtenerProductoPorCategoria(EntityManager em, Categoria categoria) {
        TypedQuery<Producto> query = em.createQuery(
                "SELECT p FROM Producto p WHERE p.categoria = :categoria", Producto.class);
        return query.setParameter("categoria", categoria).getResultList();
    }*/
    public static List<Comuna> obtenerCiudadPorProvincia(EntityManager em, Provincia provincia) {
        TypedQuery<Comuna> query = em.createQuery(
                "SELECT c FROM Comuna c WHERE c.provincia = :provincia", Comuna.class);
        return query.setParameter("provincia", provincia).getResultList();
    }

    public static List<Provincia> obtenerProvinciaPorRegion(EntityManager em, Region region) {
        TypedQuery<Provincia> query = em.createQuery(
                "SELECT c FROM Provincia c WHERE c.region = :region", Provincia.class);
        return query.setParameter("region", region).getResultList();
    }

    public static List<Comuna> obtenerComunas(EntityManager em) {
        TypedQuery<Comuna> query = em.createQuery(
                "SELECT c FROM Comuna c", Comuna.class);
        return query.getResultList();
    }

    public static List<Region> obtenerRegiones(EntityManager em) {
        TypedQuery<Region> query = em.createQuery(
                "SELECT r FROM Region r", Region.class);
        return query.getResultList();
    }

    public static List<Provincia> obtenerProvincias(EntityManager em) {
        TypedQuery<Provincia> query = em.createQuery(
                "SELECT r FROM Provincia r", Provincia.class);
        return query.getResultList();
    }
}
