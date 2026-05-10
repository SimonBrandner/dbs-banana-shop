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









    /*
    public static void main(String[] args) {
        // Connect to DBS
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bananashop");
        EntityManager em = emf.createEntityManager();
        System.out.println("DB Connected! :D");

        // Load all DAOs
        PersonDAO personDao = new PersonDAO(em);
        CustomerDAO customerDao = new CustomerDAO(em);
        EmployeeDAO employeeDao = new EmployeeDAO(em);
        BananaDAO bananaDao = new BananaDAO(em);
        ShelfDAO shelfDao = new ShelfDAO(em);
        BananaOrderDAO bananaOrderDao = new BananaOrderDAO(em);
        PhoneNumberDAO phoneNumberDao = new PhoneNumberDAO(em);
        SupervisesDAO supervisesDao = new SupervisesDAO(em);
        OrderContainsBananaDAO orderContainsBananaDao = new OrderContainsBananaDAO(em);
        CanGetForFreeDAO canGetForFreeDao = new CanGetForFreeDAO(em);

        // Test function calls
        Customer myCustomer = createCustomer(customerDao, "James Acaster", "james.acaster@gmail.com");
        //verifyCustomer(customerDao, UUID.fromString("768d5423-cae2-4663-8175-cb3314b280f9"));

        // close connection
        em.close();
        emf.close();
    }


    // Temp function for local testing
    private static Shelf createShelf(ShelfDAO shelfDao) {
        Random rand = new Random();
        int row = rand.nextInt(1000) + 1000;
        int col = rand.nextInt(1000) + 1000;

        ShelfId shelfId = new ShelfId(row, col);
        Shelf shelf = new Shelf(shelfId, new BigDecimal("39.90"));

        shelfDao.insertShelf(shelf);
        System.out.println("created shelf (row, col)" + shelfId.getRowNumber() + ", " + shelfId.getColumnNumber());
        return shelf;
    }

    private static Banana createBanana(BananaDAO bananaDao, Shelf shelf, Float weight) {
        UUID barcode = UUID.randomUUID();
        Banana banana = new Banana(barcode, new BigDecimal(weight.toString()), shelf);

        bananaDao.insertBanana(banana);
        System.out.println("created banana: " + barcode);
        return banana;
    }

    private static Customer createCustomer(CustomerDAO customerDao, String name, String email) {
        UUID customerId = UUID.randomUUID();
        Customer customer = new Customer();
        customer.setPersonId(customerId);
        customer.setName(name);
        customer.setEmail(email);

        customerDao.insertCustomer(customer);
        System.out.println("created customer: " + customer.getName() + " (id: " + customerId + ")");
        return customer;
    }

    private static void giveBananaForFree(CanGetForFreeDAO freeDao, Customer customer, Banana banana) {
        CanGetForFreeId freeId = new CanGetForFreeId(customer.getPersonId(), banana.getBarcode());
        CanGetForFree specialRight = new CanGetForFree();
        specialRight.setId(freeId);
        specialRight.setCustomer(customer);
        specialRight.setBanana(banana);

        freeDao.insertSpecialRight(specialRight);
        System.out.println("customer " + customer.getName() + " can now get banana " + banana.getBarcode() + " for free!");
    }

    private static void verifyCustomer(CustomerDAO customerDao, UUID customerId) {
        Customer loadedCustomer = customerDao.findById(customerId);

        if (loadedCustomer != null) {
            System.out.println("customer is in database: " + loadedCustomer.getName());
        } else {
            System.out.println("customer is not in the database");
        }
    }*/
}