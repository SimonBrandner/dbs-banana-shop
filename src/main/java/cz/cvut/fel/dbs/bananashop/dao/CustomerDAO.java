package cz.cvut.fel.dbs.bananashop.dao;

import cz.cvut.fel.dbs.bananashop.tables.Customer;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

public class CustomerDAO {
    private EntityManager em;

    public CustomerDAO(EntityManager em) {
        this.em = em;
    }

    public void insertCustomer(Customer c) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(c);
        et.commit();
    }

    public Customer findById(UUID id) {
        return em.find(Customer.class, id);
    }

    public List<Customer> getAll() {
        return em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
    }
}