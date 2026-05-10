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

    @Column(name = "price_per_kg")
    private BigDecimal price_per_kg ;



    public Shelf() {}

    public Shelf(ShelfId id, BigDecimal pricePerKg) {
        this.id = id;
        this.price_per_kg = pricePerKg;
    }

    public ShelfId getId() { return id; }
    public void setId(ShelfId id) { this.id = id; }

    public BigDecimal getPricePerKg() { return price_per_kg; }
    public void setPricePerKg(BigDecimal price_per_kg) { this.price_per_kg = price_per_kg; }
}