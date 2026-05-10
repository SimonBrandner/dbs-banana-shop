package cz.cvut.fel.dbs.bananashop.tables;

import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class Customer extends Person {
    public Customer() {}
}