package cz.cvut.fel.dbs.bananashop.dao;

import cz.cvut.fel.dbs.bananashop.tables.Banana;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

public class BananaDAO {
    private EntityManager em;

    public BananaDAO(EntityManager em) {
        this.em = em;
    }

    public void insertBanana(Banana b) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(b);
        et.commit();
    }

    public Banana findByBarcode(UUID barcode) {
        return em.find(Banana.class, barcode);
    }

    public List<Banana> getAll() {
        return em.createQuery("SELECT b FROM Banana b", Banana.class).getResultList();
    }
}