package cz.cvut.fel.dbs.bananashop.dao;

import cz.cvut.fel.dbs.bananashop.tables.Supervises;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

public class SupervisesDAO {
    private EntityManager em;

    public SupervisesDAO(EntityManager em) {
        this.em = em;
    }

    public void insertSupervision(Supervises s) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(s);
        et.commit();
    }

    public Supervises findByJuniorId(UUID juniorId) {
        return em.find(Supervises.class, juniorId);
    }
}