package cz.cvut.fel.dbs.bananashop;

import cz.cvut.fel.dbs.bananashop.dao.*;
import cz.cvut.fel.dbs.bananashop.tables.*;
import cz.cvut.fel.dbs.bananashop.service.BananaShop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.util.Random;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        // Connect to DBS
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bananashop");
        EntityManager em = emf.createEntityManager();
        System.out.println("DB connected! :D");


        // Test function calls of the service
        BananaShop service = new BananaShop(em);

        // create new customer
        Customer customer1 = service.registerNewCustomer("James Acaster", "james.acaster@gmail.com");
        System.out.println("customer created: " + customer1.getName() + " email: "  + customer1.getEmail() + " id: " + customer1.getPersonId());

        // create new employee
        Employee cashier = service.registerNewEmployee("Václav Kratochvíl");

        // create new shelf
        Shelf newShelf = service.createNewShelf(120, 124, new BigDecimal("13.1"));
        System.out.println("shelf created: " + newShelf.getId() + " price per kg: " + newShelf.getPricePerKg());

        // create 2 new banans
        Banana banana1 = service.stockNewBanana(new BigDecimal("6.9"), newShelf);
        Banana banana2 = service.stockNewBanana(new BigDecimal("4.2"), newShelf);
        System.out.println("2x bananas added to stock (stock) " + banana1.getBarcode() + ", " + banana2.getBarcode());

        // check which bananas can be purchased for free
        List<CanGetForFree> freeList = service.getFreeBananasForCustomer(customer1.getPersonId());
        System.out.println(customer1.getName() + " can now purchase " + freeList.size() + " banans for free");

        // set banana1 for customer1 for free
        service.grantFreeBanana(customer1, banana1);
        System.out.println(customer1.getName() + " has been granted free banana status for banana " + banana1.getBarcode());

        // check which bananas can be purchased for free again (should be more)
        freeList = service.getFreeBananasForCustomer(customer1.getPersonId());
        System.out.println(customer1.getName() + " can now purchase " + freeList.size() + " banans for free");

        // try purchasing the 2 banans
        System.out.println(customer1.getName() + " is now purchasing bananas " + banana1.getBarcode() + " and " + banana2.getBarcode());
        List<UUID> bananasToBuy = List.of(banana1.getBarcode(), banana2.getBarcode());
        service.processBananaOrder(customer1.getPersonId(), cashier.getPersonId(), bananasToBuy);
        // end of Test

        // close connection
        em.close();
        emf.close();
    }
}
