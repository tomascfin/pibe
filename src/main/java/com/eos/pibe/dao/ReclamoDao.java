package com.eos.pibe.dao;

import com.eos.pibe.model.Reclamo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ReclamoDao {

    @PersistenceContext(unitName = "pibe_db")
    private EntityManager em;

    public Reclamo obtenerReclamo(Long id){
        Reclamo reclamo = em.find(Reclamo.class, id);

        return reclamo;
    }
}
