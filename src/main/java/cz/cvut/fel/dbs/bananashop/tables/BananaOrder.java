package cz.cvut.fel.dbs.bananashop.tables;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "banana_order")
public class BananaOrder {
    @EmbeddedId
    private BananaOrderId id;

    @MapsId("customerPersonId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_person_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "employee_person_id")
    private Employee employee;
}