/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eos.pibe.services;

import com.eos.pibe.model.Agendamiento;
import com.eos.pibe.model.Comuna;
import com.eos.pibe.model.Entidad;
import com.eos.pibe.model.MovimientoSeries;
import com.eos.pibe.model.NumerosDeSerie;
import com.eos.pibe.model.Provincia;
import com.eos.pibe.model.Region;
import static com.oracle.jrockit.jfr.ContentType.Timestamp;
import java.io.OutputStream;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.stream.JsonGenerator;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 *
 * @author tomas
 */
@Stateless
public class ServiciosRest {

    @PersistenceContext(unitName = "pibe_db")
    private EntityManager em;

    public void obtenerListadoSeries(OutputStream os, boolean bool, String idEntidad) {
        List<NumerosDeSerie> series = null;
        Entidad entidad = em.find(Entidad.class, idEntidad);
        if (idEntidad.equals("") || idEntidad.equals("undefined")) {
            series = em.createNamedQuery("Series.findAll")
                    .setParameter("activado", bool)
                    .getResultList();
        } else {
            series = em.createNamedQuery("Series.findByEntidad")
                    .setParameter("valor", entidad)
                    .getResultList();
        }

        JsonGenerator gen = Json.createGenerator(os);
        gen.writeStartObject();
        gen.writeStartArray("series");
        for (NumerosDeSerie serie : series) {
            gen.writeStartObject();
            gen.write("id", serie.getId());
            gen.write("usos", serie.getUsos());
            gen.writeEnd();
        }
        gen.writeEnd();
        gen.writeEnd();
        gen.flush();
        gen.close();

    }

    public void ampliarSeries(OutputStream os, JsonObject json) {
        NumerosDeSerie serie = em.find(NumerosDeSerie.class, json.getString("idSerie"));
        serie.setUsos(serie.getUsos() + json.getJsonNumber("usos").intValue());
        em.merge(serie);
        registrarMovimientoSeries(serie.getEntidad(), serie, json.getJsonNumber("usos").intValue(), "Ampliacion");
    }

    public void listarEntidades(OutputStream os) {
        List<Entidad> entidades = em.createNamedQuery("Entidad.findAll").getResultList();
        JsonGenerator gen = Json.createGenerator(os);
        gen.writeStartObject();
        gen.writeStartArray("entidades");
        for (Entidad entidad : entidades) {
            gen.writeStartObject();
            gen.write("id", entidad.getId());
            gen.write("nombreEntidad", entidad.getNombreEntidad());
            gen.writeEnd();
        }
        gen.writeEnd();
        gen.writeEnd();
        gen.flush();
        gen.close();
    }

    public void listarComunas(OutputStream os) {

        //Collections.sort(comunas, (Comuna p1, Comuna p2) -> p1.getNombreCiudad().compareTo(p2.getNombreCiudad()));
        JsonGenerator gen = Json.createGenerator(os);
        List<Comuna> comunas = obtenerComunas(em);
        gen.writeStartObject();
        gen.writeStartArray("Comunas");
        for (Comuna comuna : comunas) {
            gen.writeStartObject();
            gen.write("display", comuna.getNombreCiudad());
            gen.write("value", comuna.getId());
            gen.write("provincia", comuna.getProvincia().getNombreProvincia());
            gen.write("region", comuna.getProvincia().getRegion().getNombreRegion());
            gen.writeEnd();
        }
        gen.writeEnd();
        gen.writeEnd();
        gen.flush();
        gen.close();
    }

    public void listarAgendamienetos(OutputStream os) {
        List<Agendamiento> agendamientos = em.createNamedQuery("Agendamiento.findAll").getResultList();
        System.out.println("Entro a service");
        String inicio = "";
        String fin = "";
        JsonGenerator gen = Json.createGenerator(os);
        gen.writeStartObject();
        gen.writeStartArray("events");

        for (Agendamiento agendamiento : agendamientos) {
            //fin = agendamiento.getFinFecha().toString();
            System.out.println(inicio);

            //Date date = agendamiento.getInicioFecha();
            /*Calendar calendar = Calendar.getInstance(); // creates a new calendar instance
            calendar.setTime(date);   // assigns calendar to given date 
            System.out.println(calendar.get(Calendar.HOUR_OF_DAY)); // gets hour in 24h format
            System.out.println(calendar.get(Calendar.HOUR));        // gets hour in 12h format
            System.out.println(calendar.get(Calendar.MINUTE));
            System.out.println(calendar.get(Calendar.MONTH));*/
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            //Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z").parse();
            /*java.util.Date utilDate = new java.util.Date();
             try{
                 utilDate = sdf.parse(agendamiento.getInicioFecha().toString());
             }catch(ParseException pe){
                 pe.printStackTrace();
             }*/
            gen.writeStartObject();
            gen.write("id", agendamiento.getId());
            //gen.write("title", agendamiento.getNombre());
            Timestamp timeStamp = agendamiento.getFechaInicio();
            gen.write("start", sdf.format(timeStamp));
            /*Timestamp tp = agendamiento.getTimestampField();
            int minutos = agendamiento.getTimestampField().getMinutes();
            tp.setMinutes( minutos + 45);*/
            gen.write("end", sdf.format(agendamiento.getFechaTermino()));
            //gen.write("end", sdf.format(agendamiento.getFinFecha()));
            switch (agendamiento.getPrioridad()) {
                case 1:
                    gen.write("className", "highPriority");
                    break;
                case 2:
                    gen.write("className", "lowPriority");
            }
            gen.write("stick", "true");
            gen.write("detalle", agendamiento.getDetalleagendamiento());
            gen.write("title", agendamiento.getDetalleagendamiento());
            gen.writeEnd();

        }
        gen.writeEnd();
        gen.writeEnd();
        gen.flush();
        gen.close();

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

    public void eliminarAgendamiento(Long id) {
        Agendamiento agendamiento = em.find(Agendamiento.class, id);
        try {

            if (agendamiento != null) {
                em.remove(agendamiento);
            }

        } catch (Exception e) {
            System.out.println("Error en la bd es: " + e.getMessage());
        }
    }

    public void registrarMovimientoSeries(Entidad entidad, NumerosDeSerie serie, int usos, String tipo) {

        try {

            MovimientoSeries movimientoSeries = new MovimientoSeries();
            movimientoSeries.setDetalleMovimiento("pruebas");
            movimientoSeries.setEntidad(entidad);
            movimientoSeries.setTipoMovimiento(tipo);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            movimientoSeries.setFechaMovimiento(timestamp);
            movimientoSeries.setNumeroDeSerie(serie);
            movimientoSeries.setUsos(usos);
            em.persist(movimientoSeries);

        } catch (PersistenceException e) {
            System.out.println("Error en el ingreso: " + e.getMessage());
        }
    }

    public void registrarAgendamiento(JsonObject json) throws ParseException {

        Entidad entidad = em.find(Entidad.class, json.getString("idEntidad"));
        try {
            System.out.println(json);
            Agendamiento agendamiento = new Agendamiento();
            //agendamiento.setId(Long.MIN_VALUE);
            agendamiento.setDetalleagendamiento(json.getString("detalleAgendamiento"));
            agendamiento.setEmailContacto(json.getString("emailAgendamiento"));
            agendamiento.setEntidad(entidad);
            agendamiento.setNombreContacto(json.getString("nombreContacto"));
            agendamiento.setPrioridad(1);
            agendamiento.setTelefonoContacto(json.getString("telefonoContacto"));

            SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");

            Date fecha = dateFormater.parse(json.getString("inicioHorario"));
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            timestamp.setYear(fecha.getYear());
            timestamp.setMonth(fecha.getMonth());
            timestamp.setDate(fecha.getDate());
            timestamp.setHours(fecha.getHours());
            timestamp.setMinutes(fecha.getMinutes());
            agendamiento.setFechaInicio(timestamp);

            Date fecha2 = dateFormater.parse(json.getString("finHorario"));
            Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
            timestamp2.setYear(fecha2.getYear());
            timestamp2.setMonth(fecha2.getMonth());
            timestamp2.setDate(fecha2.getDate());
            timestamp2.setHours(fecha2.getHours());
            timestamp2.setMinutes(fecha2.getMinutes());
            agendamiento.setFechaTermino(timestamp2);

            em.persist(agendamiento);

        } catch (PersistenceException e) {
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
