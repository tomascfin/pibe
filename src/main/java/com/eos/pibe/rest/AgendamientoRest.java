package com.eos.pibe.rest;

import com.eos.pibe.dao.AgendamientoDao;
import com.eos.pibe.services.AgendamientoService;
import com.eos.pibe.services.ServiciosRest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.OutputStream;

@Path("Agendamiento")
@Stateless
public class AgendamientoRest {

    @Inject
    AgendamientoService agendamientoService;

    @DELETE
    @Path("eliminar_agendamiento")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarAgendamiento(@QueryParam("id") final Long id) {
        StreamingOutput so = new StreamingOutput() {
            @Override
            public void write(OutputStream outputStream) throws IOException, WebApplicationException {

               // agendamientoService.eliminarAgendamiento(id);
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
                    agendamientoService.listarAgendamienetos(outputStream);
                } catch (Exception e) {
                    System.out.println("Error en listar comunas: " + e.getMessage());
                }
            }
        };
        return Response.ok(so).build();
    }
}
