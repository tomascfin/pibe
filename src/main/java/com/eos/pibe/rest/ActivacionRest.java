package com.eos.pibe.rest;

import com.eos.pibe.services.ServiciosRest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.OutputStream;

@Path("activacion")
@Stateless
public class ActivacionRest {

    @Inject
    ServiciosRest serviciosRest;

    @PersistenceContext(unitName = "pibe_db")
    private EntityManager em;


    @GET
    @Path("obtener_listado_series")
    @Produces(MediaType.APPLICATION_JSON)
    public Response prueba(@DefaultValue("true") @QueryParam("activado") final boolean bool,
                           @DefaultValue("") @QueryParam("valor") final String idEntidad) {

        return null;
    }

}
