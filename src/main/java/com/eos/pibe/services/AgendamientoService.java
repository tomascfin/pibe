package com.eos.pibe.services;

import com.eos.pibe.dao.AgendamientoDao;
import com.eos.pibe.model.Agendamiento;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.stream.JsonGenerator;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class AgendamientoService {

    @Inject
    AgendamientoDao agendamientoDao;

    public void eliminarAgendamiento(Long id) {
        agendamientoDao.eliminarAgendamiento(id);
    }

    public void listarAgendamienetos(OutputStream os) {
        List<Agendamiento> agendamientos= agendamientoDao.listarAgendamientos();
        JsonGenerator gen = Json.createGenerator(os);
        gen.writeStartObject();
        gen.writeStartArray("events");

        for (Agendamiento agendamiento : agendamientos) {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            gen.writeStartObject();
            gen.write("id", agendamiento.getId());
            //gen.write("title", agendamiento.getNombre());
            Timestamp timeStamp = agendamiento.getFechaInicio();
            gen.write("start", sdf.format(timeStamp));
            gen.write("end", sdf.format(agendamiento.getFechaTermino()));
            switch (agendamiento.getPrioridad()) {
                case 1:
                    gen.write("className", "highPriority");
                    break;
                case 2:
                    gen.write("className", "lowPriority");
            }
            gen.write("stick", "true");
            gen.write("detalle", agendamiento.getDetalleagendamiento());
            gen.write("title", agendamiento.getDetalleagendamiento());
            gen.write("telefonoContacto", agendamiento.getTelefonoContacto());
            gen.write("nombreContacto", agendamiento.getNombreContacto());
            gen.write("emailContacto", agendamiento.getEmailContacto());
            gen.write("nombreEstablecimiento", agendamiento.getEntidad().getNombreEntidad());
            gen.writeEnd();

        }
        gen.writeEnd();
        gen.writeEnd();
        gen.flush();
        gen.close();

    }
}
