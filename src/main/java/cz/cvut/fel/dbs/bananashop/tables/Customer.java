package cz.cvut.fel.dbs.bananashop.tables;

import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class Customer extends Person {
    @ManyToOne(optional = false)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;
}