/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tomas.utils;


import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
//import org.glassfish.jersey.media.multipart.MultiPartFeature;
//import org.glassfish.jersey.media.multipart.MultiPartFeature;
//import org.glassfish.jersey.media.multipart.MultiPartFeature;
//import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
//import org.glassfish.jersey.media.multipart.FormDataParam;

@ApplicationPath("ws") // set the path to REST web services
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> resources = new HashSet<>();

        resources.add(com.eos.pibe.rest.PibeRest.class);
        //resources.add(com.eos.pibe.restUpload.UploadFileService.class);
          // Add your resources.
        //resources.add(FileUploadService.class);

        // Add additional features such as support for Multipart.
        //resources.add(MultiPartFeature.class);
        //resources.add(com.eos.pibe.rest.PibeUpload.class);

        return resources;
    }
    
}
