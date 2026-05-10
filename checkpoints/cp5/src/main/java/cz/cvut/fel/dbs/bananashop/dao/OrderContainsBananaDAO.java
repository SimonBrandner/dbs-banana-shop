package cz.cvut.fel.dbs.bananashop.dao;

import cz.cvut.fel.dbs.bananashop.tables.OrderContainsBanana;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

public class OrderContainsBananaDAO {
    private EntityManager em;

    public OrderContainsBananaDAO(EntityManager em) {
        this.em = em;
    }

    public void insertRelation(OrderContainsBanana ocb) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(ocb);
        et.commit();
    }

    public OrderContainsBanana findByBarcode(UUID barcode) {
        return em.find(OrderContainsBanana.class, barcode);
    }
}