package cz.cvut.fel.dbs.bananashop.tables;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "order_contains_banana")
public class OrderContainsBanana {
    @Id
    @Column(name = "banana_barcode", nullable = false)
    private UUID banana_barcode;

    @OneToOne
    @MapsId
    @JoinColumn(name = "banana_barcode", nullable = false)
    private Banana banana;

    @ManyToOne(optional = false)
    @JoinColumns({
            @JoinColumn(name = "order_creation_time", referencedColumnName = "creation_time", nullable = false),
            @JoinColumn(name = "customer_person_id", referencedColumnName = "customer_person_id", nullable = false)
    })
    private BananaOrder banana_order;


    public OrderContainsBanana() {}

    public UUID getBananaBarcode() { return banana_barcode; }
    public void setBananaBarcode(UUID bananaBarcode) { this.banana_barcode = bananaBarcode; }

    public Banana getBanana() { return banana; }
    public void setBanana(Banana banana) { this.banana = banana; }

    public BananaOrder getBananaOrder() { return banana_order; }
    public void setBananaOrder(BananaOrder bananaOrder) { this.banana_order = bananaOrder; }
}