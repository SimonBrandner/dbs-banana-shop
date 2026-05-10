package cz.cvut.fel.dbs.bananashop.tables;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "banana_order")
public class BananaOrder {
    @EmbeddedId
    private BananaOrderId id;

    @MapsId("customer_person_id")
    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_person_id", nullable = false)
    private Customer customer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_person_id", nullable = false)
    private Employee employee;




    public BananaOrder() {}

    public BananaOrderId getId() { return id; }
    public void setId(BananaOrderId id) { this.id = id; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }
}