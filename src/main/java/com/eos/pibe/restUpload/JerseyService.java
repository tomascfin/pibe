package com.eos.pibe.restUpload;

import com.eos.pibe.rest.PibeRest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.List;
import javax.inject.Inject;

import javax.servlet.annotation.WebServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.*;

@Path("/upload")
public class JerseyService {

    @Inject
    PibeRest pibeRest;

    @POST
    @Path("/pdf")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    public Response uploadPdfFile(final FormDataMultiPart multiPart,
                                  @FormDataParam("file")
                                          InputStream fileInputStream,
                                  @QueryParam("id") final String id,
                                  @FormDataParam("file") FormDataContentDisposition fileMetaData) throws Exception {
       /* System.out.println("Entro a upload servlet");
        String UPLOAD_PATH = "/home/tomas/Imágenes/";
        try {
            int read = 0;
            byte[] bytes = new byte[1024];
            String ruta = UPLOAD_PATH + id;
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

    }*/

        List<FormDataBodyPart> bodyParts = multiPart.getFields("file");

        StringBuffer fileDetails = new StringBuffer("");

		/* Save multiple files */
        for (int i = 0; i < bodyParts.size(); i++) {

            BodyPartEntity bodyPartEntity = (BodyPartEntity) bodyParts.get(i).getEntity();
            String fileName = bodyParts.get(i).getContentDisposition().getFileName();
            saveFile(bodyPartEntity.getInputStream(), id+"_"+i);
            fileDetails.append(" File saved at /Volumes/Drive2/temp/file/" + fileName);
        }

		/* Save File 2 */


        //fileDetails.append(" Tag Details : " + multiPart.getField("tags").getValue());
        System.out.println(fileDetails);

        return Response.ok(fileDetails.toString()).build();
    }

    private void saveFile(InputStream file, String name) {
        try {
			/* Change directory path */
            java.nio.file.Path path = FileSystems.getDefault().getPath("/home/tomas/Imágenes/" + name);
			/* Save InputStream as file */
            Files.copy(file, path);
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }
    }
