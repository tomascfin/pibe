package com.eos.pibe.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.io.File;

@Path("/archivos")
public class FileDownloadRest {

    private static final String FILE_PATH = "C:\\fotos_pibe\\";


    @GET
    @Path("/fotos")
    @Produces("image/png")
    public Response getFile(@QueryParam("activado") final String nombreArchivo) {
        File file = new File(FILE_PATH+nombreArchivo);

        Response.ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition","attachment; filename=\""+nombreArchivo+".png\"");
        return response.build();

    }
}
