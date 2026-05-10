package cz.cvut.fel.dbs.bananashop.dao;

import cz.cvut.fel.dbs.bananashop.tables.CanGetForFree;
import cz.cvut.fel.dbs.bananashop.tables.CanGetForFreeId;
import jakarta.persistence.*;
import java.util.List;

public class CanGetForFreeDAO {
    private EntityManager em;

    public CanGetForFreeDAO(EntityManager em) {
        this.em = em;
    }

    public void insertSpecialRight(CanGetForFree c) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(c);
        et.commit();
    }

    public CanGetForFree findById(CanGetForFreeId id) {
        return em.find(CanGetForFree.class, id);
    }
}