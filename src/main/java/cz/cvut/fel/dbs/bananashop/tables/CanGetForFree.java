package cz.cvut.fel.dbs.bananashop.tables;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "can_get_for_free")
public class CanGetForFree {
    @EmbeddedId
    private CanGetForFreeId id;

    @MapsId("customer_person_id")
    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_person_id", nullable = false)
    private Customer customer;

    @MapsId("banana_barcode")
    @ManyToOne(optional = false)
    @JoinColumn(name = "banana_barcode", nullable = false)
    private Banana banana;



    public CanGetForFree() {}

    public CanGetForFreeId getId() { return id; }
    public void setId(CanGetForFreeId id) { this.id = id; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public Banana getBanana() { return banana; }
    public void setBanana(Banana banana) { this.banana = banana; }
}