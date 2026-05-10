package cz.cvut.fel.dbs.bananashop.service;

import cz.cvut.fel.dbs.bananashop.dao.*;
import cz.cvut.fel.dbs.bananashop.tables.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public class BananaShop {
    private final EntityManager em;

    private final PersonDAO personDao;
    private final CustomerDAO customerDao;
    private final EmployeeDAO employeeDao;
    private final BananaDAO bananaDao;
    private final ShelfDAO shelfDao;
    private final BananaOrderDAO bananaOrderDao;
    private final PhoneNumberDAO phoneNumberDao;
    private final SupervisesDAO supervisesDao;
    private final OrderContainsBananaDAO orderContainsBananaDao;
    private final CanGetForFreeDAO canGetForFreeDao;

    public BananaShop(EntityManager em) {
        this.em = em;

        this.personDao = new PersonDAO(em);
        this.customerDao = new CustomerDAO(em);
        this.employeeDao = new EmployeeDAO(em);
        this.bananaDao = new BananaDAO(em);
        this.shelfDao = new ShelfDAO(em);
        this.bananaOrderDao = new BananaOrderDAO(em);
        this.phoneNumberDao = new PhoneNumberDAO(em);
        this.supervisesDao = new SupervisesDAO(em);
        this.orderContainsBananaDao = new OrderContainsBananaDAO(em);
        this.canGetForFreeDao = new CanGetForFreeDAO(em);
    }


    public Customer registerNewCustomer(String name, String email) {
        Customer customer = new Customer();
        customer.setPersonId(UUID.randomUUID());
        customer.setName(name);
        customer.setEmail(email);

        customerDao.insertCustomer(customer);
        return customer;
    }

    public Shelf createNewShelf(int row, int col, BigDecimal pricePerKg) {
        Shelf shelf = new Shelf(new ShelfId(row, col), pricePerKg);
        shelfDao.insertShelf(shelf);
        return shelf;
    }

    public Employee registerNewEmployee(String name) {
        Employee employee = new Employee();
        employee.setPersonId(UUID.randomUUID());
        employee.setName(name);
        employeeDao.insertEmployee(employee);
        return employee;
    }

    public Banana stockNewBanana(BigDecimal weight, Shelf shelf) {
        Banana banana = new Banana(UUID.randomUUID(), weight, shelf);
        bananaDao.insertBanana(banana);
        return banana;
    }

    public void grantFreeBanana(Customer customer, Banana banana) {
        CanGetForFree specialRight = new CanGetForFree();
        specialRight.setId(new CanGetForFreeId(customer.getPersonId(), banana.getBarcode()));
        specialRight.setCustomer(customer);
        specialRight.setBanana(banana);

        canGetForFreeDao.insertSpecialRight(specialRight);
    }

    public List<CanGetForFree> getFreeBananasForCustomer(UUID customerId) {
        return em.createQuery(
            "SELECT f FROM CanGetForFree f WHERE f.customer.person_id = :custId", CanGetForFree.class)
            .setParameter("custId", customerId)
            .getResultList();
    }

    public void processBananaOrder(UUID customerId, UUID employeeId, List<UUID> bananaBarcodes) {
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Customer customer = em.find(Customer.class, customerId);
            Employee employee = em.find(Employee.class, employeeId);

            if (customer == null) {
                throw new IllegalArgumentException("customer does not exist !");
            } else if (employee == null) {
                throw new IllegalArgumentException("employee does not exist !");
            }


            BananaOrderId orderId = new BananaOrderId(OffsetDateTime.now(), customerId);
            BananaOrder order = new BananaOrder();
            order.setId(orderId);
            order.setCustomer(customer);
            order.setEmployee(employee);
            em.persist(order);

            for (UUID barcode : bananaBarcodes) {
                Banana banana = em.find(Banana.class, barcode);
                if (banana != null) {
                    OrderContainsBanana ocb = new OrderContainsBanana();
                    ocb.setBananaBarcode(barcode);
                    ocb.setBanana(banana);
                    ocb.setBananaOrder(order);
                    em.persist(ocb);
                }
            }

            tx.commit();
            System.out.println("Order" + order.getId() + "has been successfully completed");

        } catch (Exception e) {
            if (tx.isActive()) { tx.rollback(); }
            System.err.println("ERROR - with processing this order, rolling back");
            throw e;
        }
    }
}