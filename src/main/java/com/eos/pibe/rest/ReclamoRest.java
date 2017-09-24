package com.eos.pibe.rest;

import com.eos.pibe.services.ReclamoService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.OutputStream;

@Path("reclamo")
@Stateless
public class ReclamoRest {

    @Inject
    ReclamoService reclamoService;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerReclamo(@PathParam("id") final Long id) {

        StreamingOutput so = new StreamingOutput() {
            @Override
            public void write(OutputStream outputStream) throws IOException, WebApplicationException {
                try {
                    reclamoService.obtenerReclamo(outputStream, id);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        return Response.ok(so).build();
    }
}
