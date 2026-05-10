package cz.cvut.fel.dbs.bananashop.dao;

import cz.cvut.fel.dbs.bananashop.tables.Person;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

public class PersonDAO {
    private EntityManager em;

    public PersonDAO(EntityManager em) {
        this.em = em;
    }

    public void insertPerson(Person p) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(p);
        et.commit();
    }

    public void deletePerson(Person p) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.remove(em.contains(p) ? p : em.merge(p));
        et.commit();
    }

    public Person findById(UUID id) {
        return em.find(Person.class, id);
    }

    public List<Person> getAll() {
        return em.createQuery("SELECT p FROM Person p", Person.class).getResultList();
    }
}