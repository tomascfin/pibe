/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eos.pibe.rest;

import com.eos.pibe.model.Entidad;
import com.eos.pibe.services.ServiciosRest;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

/**
 *
 * @author tomas
 */
@Path("pibe")
public class PibeRest {

    @EJB
    ServiciosRest serviciosRest;

    @PersistenceContext(unitName = "pibe_db")
    private EntityManager em;

    @GET
    @Path("obtener_listado_series")
    @Produces(MediaType.APPLICATION_JSON)
    public Response prueba() {
        StreamingOutput so = new StreamingOutput() {
            @Override
            public void write(OutputStream outputStream) throws IOException, WebApplicationException {
                try {
                    serviciosRest.obtenerListadoSeries(outputStream);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
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
                    System.out.println("Error: " + e.getMessage());
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

    @POST
    @Path("upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail) {
        System.out.println("Entroa  ws upload");
        String fileLocation = "c://upload/" + fileDetail.getFileName();
        System.out.println(fileLocation);
        //saving file  
        try {
            escanear(fileLocation);
             File file = new File(fileLocation);
             file.setWritable(true);
            FileOutputStream out = new FileOutputStream(file);
            
             
            
            int read = 0;
            byte[] bytes = new byte[1024];
            out = new FileOutputStream(new File(fileLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String output = "File successfully uploaded to : " + fileLocation;
        return Response.status(200).entity(output).build();
    }
    
    public void escanear(String file){
     
        try(Scanner scanner = new Scanner(new File(file))) {
        scanner.useDelimiter(",");
        while(scanner.hasNext()){
            System.out.print(scanner.next()+"|");
        }
        }catch (FileNotFoundException e){

            e.printStackTrace();
        }
    }

}
