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

    @MapsId("customerPersonId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_person_id", nullable = false)
    private Customer customer;

    @MapsId("bananaBarcode")
    @ManyToOne(optional = false)
    @JoinColumn(name = "banana_barcode", nullable = false)
    private Banana banana;
}