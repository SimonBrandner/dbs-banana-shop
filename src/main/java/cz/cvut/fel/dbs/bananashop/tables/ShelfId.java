package cz.cvut.fel.dbs.bananashop.tables;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Embeddable
public class ShelfId implements Serializable {
    private Integer rowNumber;
    private Integer columnNumber;

    public ShelfId() {}

    public ShelfId(Integer rowNumber, Integer columnNumber) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShelfId that = (ShelfId) o;
        return Objects.equals(rowNumber, that.rowNumber) &&
                Objects.equals(columnNumber, that.columnNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowNumber, columnNumber);
    }
}
