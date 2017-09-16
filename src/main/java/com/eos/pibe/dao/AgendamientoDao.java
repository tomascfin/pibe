package com.eos.pibe.dao;

import com.eos.pibe.model.Agendamiento;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class AgendamientoDao {

    @PersistenceContext(unitName = "pibe_db")
    private EntityManager em;

    public void eliminarAgendamiento(Long id) {
        Agendamiento agendamiento = em.find(Agendamiento.class, id);
        try {

            if (agendamiento != null) {
                em.remove(agendamiento);
            }

        } catch (Exception e) {
            System.out.println("Error en la bd es: " + e.getMessage());
        }
    }

    public List<Agendamiento> listarAgendamientos() {

        List<Agendamiento> agendamientos = em.createNamedQuery("Agendamiento.findAll").getResultList();

        return agendamientos;
    }
}
