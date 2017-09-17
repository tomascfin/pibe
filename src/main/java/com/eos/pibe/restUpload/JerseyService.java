package com.eos.pibe.restUpload;

import com.eos.pibe.rest.PibeRest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.inject.Inject;

import javax.servlet.annotation.WebServlet;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/upload")
public class JerseyService {

    @Inject
    PibeRest pibeRest;

    @POST
    @Path("/pdf")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    public Response uploadPdfFile(@FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition fileMetaData) throws Exception {
        System.out.println("Entro a upload servlet");
        String UPLOAD_PATH = "c:/temp/";
        try {
            int read = 0;
            byte[] bytes = new byte[1024];
            String ruta = UPLOAD_PATH + fileMetaData.getFileName();

            OutputStream out = new FileOutputStream(new File(ruta));
            while ((read = fileInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
            System.out.println("termina outputstream");
            pibeRest.escanear(ruta);
        } catch (IOException e) {
            throw new WebApplicationException("Error while uploading file. Please try again !!");
        }
        return Response.status(Response.Status.OK).build();

    }
}
