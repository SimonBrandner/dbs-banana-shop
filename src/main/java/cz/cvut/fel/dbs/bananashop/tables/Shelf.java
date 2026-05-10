package cz.cvut.fel.dbs.bananashop.tables;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "shelf", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"column_number", "row_number"})
})

public class Shelf {
    @EmbeddedId
    private ShelfId id;

    @Column(name = "row_number")
    private int row_number;

    @Column(name = "column_number")
    private int column_number;

    @Column(name = "price_per_kg")
    private BigDecimal price_per_kg ;
}