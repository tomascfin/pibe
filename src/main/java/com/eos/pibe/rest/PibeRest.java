/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eos.pibe.rest;

import com.eos.pibe.model.Agendamiento;
import com.eos.pibe.model.Entidad;
import com.eos.pibe.model.MovimientoSeries;
import com.eos.pibe.model.NumerosDeSerie;
import com.eos.pibe.services.ServiciosRest;
//import com.sun.jersey.core.header.FormDataContentDisposition;
//import com.sun.jersey.multipart.FormDataParam;
//import com.sun.jersey.multipart.FormDataParam;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.StreamingOutput;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;
//import org.glassfish.jersey.media.multipart.FormDataParam;
//import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author tomas
 */
@Path("pibe")
@Stateless
public class PibeRest {

    @Inject
    ServiciosRest serviciosRest;

    @PersistenceContext(unitName = "pibe_db")
    private EntityManager em;

    @GET
    @Path("obtener_listado_series")
    @Produces(MediaType.APPLICATION_JSON)
    public Response prueba(@DefaultValue("true")@QueryParam("activado") final boolean bool,
            @DefaultValue("")@QueryParam("valor") final String idEntidad) {
        StreamingOutput so = new StreamingOutput() {
            @Override
            public void write(OutputStream outputStream) throws IOException, WebApplicationException {
                try {
                    System.out.println("bollean :"+bool);
                    serviciosRest.obtenerListadoSeries(outputStream, bool, idEntidad);
                } catch (Exception e) {
                    System.out.println("Error en listar series: " + e.getMessage());
                }
            }
        };
        return Response.ok(so).build();
    }
    
    @GET
    @Path("listar_entidades")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarEntidades() {
        StreamingOutput so = new StreamingOutput() {
            @Override
            public void write(OutputStream outputStream) throws IOException, WebApplicationException {
                try {
                    serviciosRest.listarEntidades(outputStream);
                } catch (Exception e) {
                    System.out.println("Error en listar entidades: " + e.getMessage());
                }
            }
        };
        return Response.ok(so).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("ampliar_series")
    public Response ampliarSeries(final InputStream requestBody) {
        JsonReader cRead = Json.createReader(requestBody);
        final JsonObject numeroDeSerie = cRead.readObject();
        cRead.close();
        
        StreamingOutput so = new StreamingOutput() {
            @Override
            public void write(OutputStream outputStream) throws IOException, WebApplicationException {
                try {
                    serviciosRest.ampliarSeries(outputStream, numeroDeSerie);
                } catch (Exception e) {
                    System.out.println("Error en listar entidades: " + e.getMessage());
                }
            }
        };
        return Response.ok(so).build();
    }
    

    @GET
    @Path("listar_comunas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarComunas() {
        StreamingOutput so = new StreamingOutput() {
            @Override
            public void write(OutputStream outputStream) throws IOException, WebApplicationException {
                try {
                    serviciosRest.listarComunas(outputStream);
                } catch (Exception e) {
                    System.out.println("Error en listar comunas: " + e.getMessage());
                }
            }
        };
        return Response.ok(so).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    //@Produces(MediaType.APPLICATION_JSON)
    @Path("registrar_entidad")
    public Response registroEntidad(final InputStream requestBody) {
        JsonReader cRead = Json.createReader(requestBody);
        JsonObject entidadIngresada = cRead.readObject();
        cRead.close();
        Entidad entidad = em.find(Entidad.class, entidadIngresada.getString("idEntidad"));

        if (entidad != null) {
            return Response.status(Response.Status.CONFLICT).entity("Id ya existe: " + entidadIngresada.getString("idEntidad")).build();
        }
        try {
            serviciosRest.registrarEntidad(entidadIngresada);
        } catch (Exception e) {
            System.out.println("Error de ws: " + e.getMessage());
            Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status(Response.Status.OK).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    //@Produces(MediaType.APPLICATION_JSON)
    @Path("registrar_agendamiento")
    public Response registroAgendamiento(final InputStream requestBody) {
        JsonReader cRead = Json.createReader(requestBody);
        JsonObject agendamientoJson = cRead.readObject();
        cRead.close();
      
        try {
            serviciosRest.registrarAgendamiento(agendamientoJson);
        } catch (Exception e) {
            System.out.println("Error de ws: " + e.getMessage());
            Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status(Response.Status.OK).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    //@Produces(MediaType.APPLICATION_JSON)
    @Path("activacion_pibe")
    public Response activacionPibe(final InputStream requestBody) {
        JsonReader cRead = Json.createReader(requestBody);
        JsonObject entidadIngresada = cRead.readObject();
        cRead.close();
        
        Entidad entidad = em.find(Entidad.class, entidadIngresada.getString("idEntidad"));
        NumerosDeSerie serie = em.find(NumerosDeSerie.class, entidadIngresada.getString("idSerie"));
        System.out.println("usos que habia "+ serie.getUsos());
         System.out.println("el nombre de la entidad es: "+entidad.getNombreEntidad());
        /*if (entidad != null) {
            return Response.status(Response.Status.CONFLICT).entity("Id ya existe: " + entidadIngresada.getString("idEntidad")).build();
        }*/
        try {
            Date fecha = new Date();
            //CriteriaBuilder cb = em.getCriteriaBuilder();
        //CriteriaUpdate<NumerosDeSerie> updateCriteria = cb.createCriteriaUpdate(NumerosDeSerie.class);
            int usos = serie.getUsos() + entidadIngresada.getInt("usos");
            System.out.println("usos: "+usos);
            serie.setActivado(true);
            serie.setEntidad(entidad);
            //serie.setFechaIngreso(new Date(entidadIngresada.getString("fechaActivacion")));
            serie.setFechaIngreso(fecha);
            serie.setUsos(usos);
            
            serviciosRest.registrarMovimientoSeries(entidad, serie, usos, entidadIngresada.getString("tipoActivacion"));
            //em.merge(serie);
            //serviciosRest.registrarEntidad(entidadIngresada);
        } catch (Exception e) {
            System.out.println("Error de ws: " + e.getMessage());
            Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    //@Produces(MediaType.APPLICATION_JSON)
    @Path("verficar_id_entidad")
    public Response verificarIdEntidad(@QueryParam("id_entidad") final String id) {

        try {
            id.replace("-", "");
            System.out.println(id);
            Entidad entidad = em.find(Entidad.class, id);
            if (entidad != null) {
                System.out.println("id ya existe");
                return Response.status(Response.Status.CONFLICT).entity("Id ya existe: " + id).build();
            } else {
                System.out.println("id disponible");
                return Response.status(Response.Status.OK).entity("Id disponible: " + id).build();
            }

        } catch (Exception e) {
            System.out.println("Error en la consulta: " + e.getMessage());
            Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status(Response.Status.OK).build();
    }

    public void escanear(String file) {
        System.out.println("Entro a escanear");
        try (Scanner scanner = new Scanner(new File(file))) {

            //scanner.useDelimiter(",");
            String valor = "";
            while (scanner.hasNext()) {

                valor = scanner.next();
                NumerosDeSerie serie = new NumerosDeSerie();
                serie.setActivado(false);
                serie.setAnuladoPorReinstalacion(false);
                System.out.println("Valor--> "+valor);
                serie.setId(valor);
                serie.setEntidad(null);
                serie.setUsos(0);
                Date fecha = new Date();
                serie.setFechaIngreso(fecha);
                serviciosRest.registrarSerie(serie);
            }
            scanner.close();

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
    }

    public String escanear2(String file) {
        String valor = "";
        try (Scanner scanner = new Scanner(new File(file))) {
            scanner.useDelimiter(",");
            while (scanner.hasNext()) {
                System.out.print(scanner.hasNextLine() + "|");
                valor = scanner.next();
            }
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        return valor;
    }

    @GET
    @Path("eliminar_agendamiento")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarAgendamiento(@QueryParam("id") final Long id) {
        StreamingOutput so = new StreamingOutput() {
            @Override
            public void write(OutputStream outputStream) throws IOException, WebApplicationException {

                
               serviciosRest.eliminarAgendamiento(id);
            }
        };
        return Response.ok(so).build();
    }
    
    
    
    @GET
    @Path("registrar_serie")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarSerie() {
        StreamingOutput so = new StreamingOutput() {
            @Override
            public void write(OutputStream outputStream) throws IOException, WebApplicationException {

                String valor = "";
                String filename = "c://upload/prueba csv.csv";
                valor = escanear2(filename);
                NumerosDeSerie serie = new NumerosDeSerie();
                serie.setActivado(false);
                serie.setAnuladoPorReinstalacion(false);
                serie.setId(valor);
                serie.setEntidad(null);
                serie.setUsos(0);
                Date fecha = new Date();
                serie.setFechaIngreso(fecha);
                System.out.println("Numero " + serie.getId());
                //em.persist(serie);
                serviciosRest.registrarSerie(serie);

            }
        };
        return Response.ok(so).build();
    }
    
    @GET
    @Path("listar_agendamientos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarAgendamientos() {
        StreamingOutput so = new StreamingOutput() {
            @Override
            public void write(OutputStream outputStream) throws IOException, WebApplicationException {
                try {
                    serviciosRest.listarAgendamienetos(outputStream);
                } catch (Exception e) {
                    System.out.println("Error en listar comunas: " + e.getMessage());
                }
            }
        };
        return Response.ok(so).build();
    }
}
