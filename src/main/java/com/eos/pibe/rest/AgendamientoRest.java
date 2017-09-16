package com.eos.pibe.rest;

import com.eos.pibe.dao.AgendamientoDao;
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
    ServiciosRest serviciosRest;

    @Inject
    AgendamientoDao agendamientoDao;

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
}
