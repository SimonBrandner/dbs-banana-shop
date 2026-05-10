package cz.cvut.fel.dbs.bananashop.dao;

import cz.cvut.fel.dbs.bananashop.tables.PhoneNumber;
import jakarta.persistence.*;
import java.util.List;

public class PhoneNumberDAO {
    private EntityManager em;

    public PhoneNumberDAO(EntityManager em) {
        this.em = em;
    }

    public void insertPhoneNumber(PhoneNumber pn) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(pn);
        et.commit();
    }

    public List<PhoneNumber> getByPersonId(java.util.UUID personId) {
        TypedQuery<PhoneNumber> q = em.createQuery("SELECT pn FROM PhoneNumber pn WHERE pn.person.person_id = :pid", PhoneNumber.class);
        q.setParameter("pid", personId);
        return q.getResultList();
    }
}