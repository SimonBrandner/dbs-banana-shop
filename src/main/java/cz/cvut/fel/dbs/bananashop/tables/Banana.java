package cz.cvut.fel.dbs.bananashop.tables;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "banana")
public class Banana {
    @Id
    @Column(name = "barcode", nullable = false, unique = true)
    private UUID barcode;

    @Column(name = "weight", nullable = false)
    private BigDecimal weight;

    @ManyToOne(optional = false)
    @JoinColumns({
            @JoinColumn(name = "shelf_row_number", referencedColumnName = "row_number", nullable = false),
            @JoinColumn(name = "shelf_column_number", referencedColumnName = "column_number", nullable = false)
    })
    private Shelf shelf;
}