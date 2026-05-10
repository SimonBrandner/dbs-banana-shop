package cz.cvut.fel.dbs.bananashop.dao;

import cz.cvut.fel.dbs.bananashop.tables.Shelf;
import cz.cvut.fel.dbs.bananashop.tables.ShelfId;
import jakarta.persistence.*;
import java.util.List;

public class ShelfDAO {
    private EntityManager em;

    public ShelfDAO(EntityManager em) {
        this.em = em;
    }

    public void insertShelf(Shelf s) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(s);
        et.commit();
    }

    public Shelf findById(ShelfId id) {
        return em.find(Shelf.class, id);
    }

    public List<Shelf> getAll() {
        return em.createQuery("SELECT s FROM Shelf s", Shelf.class).getResultList();
    }
}