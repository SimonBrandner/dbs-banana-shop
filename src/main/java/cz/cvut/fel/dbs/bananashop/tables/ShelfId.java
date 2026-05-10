package cz.cvut.fel.dbs.bananashop.tables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ShelfId implements Serializable {
    @Column(name = "row_number")
    private Integer row_number;

    @Column(name = "column_number")
    private Integer column_number;



    public ShelfId() {}

    public ShelfId(Integer rowNumber, Integer columnNumber) {
        this.row_number = rowNumber;
        this.column_number = columnNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShelfId that = (ShelfId) o;
        return Objects.equals(row_number, that.row_number) &&
                Objects.equals(column_number, that.column_number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row_number, column_number);
    }


    public Integer getRowNumber() { return row_number; }
    public void setRowNumber(Integer row_number) { this.row_number = row_number; }

    public Integer getColumnNumber() { return column_number; }
    public void setColumnNumber(Integer column_number) { this.column_number = column_number; }
}
