package com.eos.pibe.services;

import com.eos.pibe.dao.ReclamoDao;
import com.eos.pibe.model.Reclamo;
import com.tomas.utils.Utils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.stream.JsonGenerator;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ReclamoService {

    @Inject
    ReclamoDao reclamoDao;


    public void obtenerReclamo(OutputStream os, Long id) {
        List<String> listaArchivos = new ArrayList<>();
        int contador = 0;
        Reclamo reclamo = reclamoDao.obtenerReclamo(id);
        JsonGenerator gen = Json.createGenerator(os);
        gen.writeStartObject();
        gen.write("id",reclamo.getId());
        gen.write("detalle",reclamo.getDetalleReclamo());
        gen.write("nombreContacto",reclamo.getNombreContacto());
        gen.write("email",reclamo.getEmailContacto());
        gen.write("estadoReclamo",reclamo.getEstadoReclamo());
        gen.write("numeroDeContacto",reclamo.getNumeroContacto());
        gen.write("prioridad",reclamo.getPrioridad());
        gen.write("fechaReclamo",reclamo.getFechaReclamo().toString());
        gen.write("areaEncargada",reclamo.getArea().getNombreArea());
        listaArchivos = Utils.buscarArchivos(String.valueOf(reclamo.getId()));
        gen.writeStartArray("linksImagenes");
        for (String listaArchivo : listaArchivos) {
            gen.writeStartObject();
            contador ++;
            String simbolikLink = "";
           /* try {
              //  Path existingFilePath = Paths.get("C:\\Users\\Tomas\\"+listaArchivo+".png");
               // Path symLinkPath = Paths.get("C:\\payara41\\glassfish\\domains\\domain1\\applications\\test1_link"+contador+".png");
               // simbolikLink = Files.createSymbolicLink(symLinkPath, existingFilePath).toString();

            }catch(IOException e){
                e.printStackTrace();
            }*/

            gen.write("rutaArchivo",listaArchivo);
            //gen.write("rutaArchivo",simbolikLink);
            gen.writeEnd();
        }
        gen.writeEnd();
        gen.writeEnd();
        gen.flush();
        gen.close();
    }
}
