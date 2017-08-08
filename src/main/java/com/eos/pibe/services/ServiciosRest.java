/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eos.pibe.services;

import com.eos.pibe.model.Agendamiento;
import com.eos.pibe.model.Areas;
import com.eos.pibe.model.Comuna;
import com.eos.pibe.model.Entidad;
import com.eos.pibe.model.MovimientoSeries;
import com.eos.pibe.model.NumerosDeSerie;
import com.eos.pibe.model.Provincia;
import com.eos.pibe.model.Reclamo;
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
import java.util.Properties;
import java.util.TimeZone;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.stream.JsonGenerator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
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
        registrarMovimientoSeries(serie.getEntidad(), serie, "Ampliacion", json);
    }

    public void contadorReclamos(OutputStream os) {
        Long abierto = em.createNamedQuery("Reclamo.countByTipoReclamo", Long.class).setParameter("tipo", 1).getSingleResult();
        Long enProgreso = em.createNamedQuery("Reclamo.countByTipoReclamo", Long.class).setParameter("tipo", 2).getSingleResult();
        Long finalizado = em.createNamedQuery("Reclamo.countByTipoReclamo", Long.class).setParameter("tipo", 3).getSingleResult();
        Long eliminado = em.createNamedQuery("Reclamo.countByTipoReclamo", Long.class).setParameter("tipo", 4).getSingleResult();

        JsonGenerator gen = Json.createGenerator(os);
        gen.writeStartObject();
        gen.write("abierto", abierto);
        gen.write("enProgreso", enProgreso);
        gen.write("finalizado", finalizado);
        gen.write("eliminado", eliminado);
        gen.writeEnd();
        gen.flush();
        gen.close();
    }

    public void modificarReclamo(OutputStream os, JsonObject json) {
        Reclamo reclamo = em.find(Reclamo.class, json.getJsonNumber("id").longValue());
        reclamo.setDetalleReclamo(json.getString("observacion"));
        System.out.println("entro a modificar en service");
        reclamo.setEstadoReclamo(2);
        em.merge(reclamo);
    }

    public void ingresarReclamo(OutputStream os, JsonObject json) throws ParseException {
        Reclamo reclamo = new Reclamo();
        Entidad entidad = em.find(Entidad.class, json.getString("idEntidad"));
        reclamo.setDetalleReclamo(json.getString("detalleReclamo"));
        reclamo.setEmailContacto(json.getString("emailContacto"));
        reclamo.setEntidad(entidad);

        //horas
        Timestamp fecha = new Timestamp(System.currentTimeMillis());//originalmente esta era la fecha que se asignaba
        TimeZone timeZone = TimeZone.getTimeZone("America/Santiago");
        Date fechaTest = new Date();
        System.out.println("Date: "+fechaTest.toString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
        sdf.setTimeZone(timeZone);
        
        TimeZone.setDefault(TimeZone.getTimeZone("GMT-4"));
        Date fechaTest2 = new Date();
        //Date fecha4 = dateFormater.parse(fechaTest.toString());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        timestamp.setYear(fechaTest2.getYear());
        timestamp.setMonth(fechaTest2.getMonth());
        timestamp.setDate(fechaTest2.getDate());
        timestamp.setHours(fechaTest2.getHours());
        timestamp.setMinutes(fechaTest2.getMinutes());
        System.out.println(timestamp);

        //fin horas
        reclamo.setFechaReclamo(timestamp);
        reclamo.setNombreContacto(json.getString("nombreContacto"));
        reclamo.setNumeroContacto(json.getString("numeroContacto"));
        reclamo.setPrioridad(json.getInt("prioridad"));

        switch (json.getInt("prioridad")) {
            case 1:
                reclamo.setColorHexidecimalValue("#ee7351");
                break;
            case 2:
                reclamo.setColorHexidecimalValue("#aa873b");
                break;
            default:
                reclamo.setColorHexidecimalValue("#d2bcc2");
                break;
        }
        reclamo.setRutaArchivo("");
        reclamo.setTipoReclamo(json.getString("tipoReclamo"));
        reclamo.setEstadoReclamo(1);
        em.persist(reclamo);
        em.flush();
        enviarMailReclamo(json.getString("emailContacto"), reclamo);
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

    public void enviarMailReclamo(String emailDestino, Reclamo reclamo) {
        System.out.println(reclamo.getEmailContacto() + reclamo.getDetalleReclamo() + reclamo.getNombreContacto() + " " + reclamo.getId());
        final String username = "tomas.i.rm25@gmail.com";
        final String password = "asusk40ab";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("tomas.i.rm25@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(reclamo.getEmailContacto()));
            message.setSubject("Relacmo Ticket " + reclamo.getId());
            message.setText("Estimado(a) " + reclamo.getNombreContacto() + ", los datos de su reclamo son: \n"
                    + "Detalle del reclamo: " + reclamo.getDetalleReclamo() + " \n"
                    + "Fecha de reclamo: " + reclamo.getFechaReclamo().toString());

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void listarDetalleSeries(OutputStream os) {
        List<NumerosDeSerie> series = em.createNamedQuery("Series.findAll").setParameter("activado", true).getResultList();
        JsonGenerator gen = Json.createGenerator(os);
        gen.writeStartObject();
        gen.writeStartArray("datos");
        for (NumerosDeSerie serie : series) {
            gen.writeStartObject();
            gen.write("numeroDeSerie", serie.getId());
            gen.write("usos", serie.getUsos());
            gen.write("establecimiento", serie.getEntidad().getNombreEntidad());
            //gen.write("link", "<button ng-click= \"ctlr.saludoPrueba()\">Modificar </button>");
            gen.writeEnd();
        }
        gen.writeEnd();
        gen.writeEnd();
        gen.flush();
        gen.close();
    }

    public void detalleSerie(OutputStream os, String id) {
        NumerosDeSerie serie = em.find(NumerosDeSerie.class, id);

        JsonGenerator gen = Json.createGenerator(os);
        gen.writeStartObject();
        gen.write("numeroDeSerie", serie.getId());
        gen.write("usos", serie.getUsos());
        gen.write("establecimiento", serie.getEntidad().getNombreEntidad());

        List<MovimientoSeries> movimientos = em.createNamedQuery("MovimientoSeries.findByNumeroDeSerie").setParameter("valor", serie).getResultList();
        gen.writeStartArray("movimientos");
        for (MovimientoSeries movimiento : movimientos) {
            gen.writeStartObject();
            gen.write("serie", movimiento.getNumeroDeSerie().getId());
            gen.write("tipoMovimiento", movimiento.getTipoMovimiento());
            gen.write("usosMovimiento", movimiento.getUsos());
            gen.write("fechaMovimiento", movimiento.getFechaMovimiento().toString());
            //gen.write("contacto", movimiento.get)
            gen.writeEnd();
        }
        gen.writeEnd();
        //gen.write("link", "<button ng-click= \"ctlr.saludoPrueba()\">Modificar </button>");

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
    
    public void listarAreas(OutputStream os){
        JsonGenerator gen = Json.createGenerator(os);
        List<Areas> areas = em.createNamedQuery("Areas.findAll", Areas.class).getResultList();
        gen.writeStartObject();
        gen.writeStartArray("areas");
        for(Areas area : areas){
            gen.writeStartObject();
            gen.write("nombreArea", area.getNombreArea());
            gen.write("correoArea", area.getCorreo_area());
            gen.writeEnd();
        }
        gen.writeEnd();
        gen.writeEnd();
        gen.flush();
        gen.close();
    }

    public void listarReclamos(OutputStream os) {

        //Collections.sort(comunas, (Comuna p1, Comuna p2) -> p1.getNombreCiudad().compareTo(p2.getNombreCiudad()));
        JsonGenerator gen = Json.createGenerator(os);
        List<Reclamo> reclamos = em.createNamedQuery("Reclamo.findAll", Reclamo.class).getResultList();
        gen.writeStartObject();
        gen.writeStartArray("reclamos");
        for (Reclamo reclamo : reclamos) {
            gen.writeStartObject();

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
            gen.write("telefonoContacto", agendamiento.getTelefonoContacto());
            gen.write("nombreContacto", agendamiento.getNombreContacto());
            gen.write("emailContacto", agendamiento.getEmailContacto());
            gen.write("nombreEstablecimiento", agendamiento.getEntidad().getNombreEntidad());
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

    public void registrarMovimientoSeries(Entidad entidad, NumerosDeSerie serie,  String tipo, JsonObject json) {

        try {
            
            MovimientoSeries movimientoSeries = new MovimientoSeries();
            movimientoSeries.setDetalleMovimiento("pruebas");
            movimientoSeries.setEntidad(entidad);
            movimientoSeries.setTipoMovimiento(tipo);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            movimientoSeries.setFechaMovimiento(timestamp);
            movimientoSeries.setNumeroDeSerie(serie);
            movimientoSeries.setUsos(json.getJsonNumber("usos").intValue());
            movimientoSeries.setNombreContacto("test");
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
