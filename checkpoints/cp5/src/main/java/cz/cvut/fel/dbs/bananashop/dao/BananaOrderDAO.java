package cz.cvut.fel.dbs.bananashop.dao;

import cz.cvut.fel.dbs.bananashop.tables.BananaOrder;
import cz.cvut.fel.dbs.bananashop.tables.BananaOrderId;
import jakarta.persistence.*;
import java.util.List;

public class BananaOrderDAO {
    private EntityManager em;

    public BananaOrderDAO(EntityManager em) {
        this.em = em;
    }

    public void insertOrder(BananaOrder o) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(o);
        et.commit();
    }

    public BananaOrder findById(BananaOrderId id) {
        return em.find(BananaOrder.class, id);
    }

    public List<BananaOrder> getAll() {
        return em.createQuery("SELECT o FROM BananaOrder o", BananaOrder.class).getResultList();
    }
}