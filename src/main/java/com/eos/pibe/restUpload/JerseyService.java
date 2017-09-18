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

    /*@POST
    @Path("/pdf")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    public Response uploadPdfFile(final FormDataMultiPart multiPart,
                                  @QueryParam("id") final String id) throws Exception {
        List<FormDataBodyPart> bodyParts = multiPart.getFields("file");

        StringBuffer fileDetails = new StringBuffer("");
        System.out.println("tamaño multipart: "+bodyParts.size());
        for (int i = 0; i < bodyParts.size(); i++) {
            BodyPartEntity bodyPartEntity = (BodyPartEntity) bodyParts.get(i).getEntity();
            String fileName = bodyParts.get(i).getContentDisposition().getFileName();
            saveFile(bodyPartEntity.getInputStream(), id + "_" + i);
            fileDetails.append(" File saved at /Volumes/Drive2/temp/file/" + id + "_" + i);
        }
        System.out.println(fileDetails);

        return Response.ok(fileDetails.toString()).build();
    }*/

    @Path("/pdf")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFiles2(@DefaultValue("") @FormDataParam("tags") String tags,
                                 @FormDataParam("file") List<FormDataBodyPart> bodyParts,
                                 @FormDataParam("file") FormDataContentDisposition fileDispositions,
                                 @QueryParam("id") final String id) {

        StringBuffer fileDetails = new StringBuffer("");

		/* Save multiple files */

        for (int i = 0; i < bodyParts.size(); i++) {
			/*
			 * Casting FormDataBodyPart to BodyPartEntity, which can give us
			 * InputStream for uploaded file
			 */
            BodyPartEntity bodyPartEntity = (BodyPartEntity) bodyParts.get(i).getEntity();
            String fileName = bodyParts.get(i).getContentDisposition().getFileName();

            saveFile(bodyPartEntity.getInputStream(), id + "_" + i);

            fileDetails.append(" File saved at C:/Users/Tomas/Pictures" + id + "_" + i);
        }

        System.out.println(fileDetails);

        return Response.ok(fileDetails.toString()).build();
    }

        private void saveFile(InputStream file, String name) {
        try {

            /* Change directory path */
            java.nio.file.Path path = FileSystems.getDefault().getPath("C:/Users/Tomas/Pictures" + name);
			/* Save InputStream as file */
            if(Files.exists(path)){
                System.out.println("File si existe");
                name = name + "_";
                path = FileSystems.getDefault().getPath("C:/Users/Tomas/Pictures" + name +"_");
            }
            Files.copy(file, path);
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }
}
