package cz.cvut.fel.dbs.bananashop.dao;

import cz.cvut.fel.dbs.bananashop.tables.Employee;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

public class EmployeeDAO {
    private EntityManager em;

    public EmployeeDAO(EntityManager em) {
        this.em = em;
    }

    public void insertEmployee(Employee e) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(e);
        et.commit();
    }

    public List<Employee> getAll() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
    }
}