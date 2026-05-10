package cz.cvut.fel.dbs.bananashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bananashop");
        EntityManager em = emf.createEntityManager();

        System.out.println("DB Connected! :D");

        em.close();
        emf.close();
    }
}