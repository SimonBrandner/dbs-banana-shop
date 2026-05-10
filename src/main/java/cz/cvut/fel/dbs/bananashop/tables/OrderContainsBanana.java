package cz.cvut.fel.dbs.bananashop.tables;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "order_contains_banana")
public class OrderContainsBanana {
    @Id
    @Column(name = "banana_barcode", nullable = false)
    private UUID bananaBarcode;

    @OneToOne
    @MapsId
    @JoinColumn(name = "banana_barcode", nullable = false)
    private Banana banana;

    @ManyToOne(optional = false)
    @JoinColumns({
            @JoinColumn(name = "order_creation_time", referencedColumnName = "creation_time", nullable = false),
            @JoinColumn(name = "customer_person_id", referencedColumnName = "customer_person_id", nullable = false)
    })
    private BananaOrder bananaOrder;
}