package cz.cvut.fel.dbs.bananashop.tables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Embeddable
public class CanGetForFreeId implements Serializable {
    @Column(name = "customer_person_id", nullable = false)
    private UUID customerPersonId;

    @Column(name = "banana_barcode", nullable = false)
    private UUID bananaBarcode;

    public CanGetForFreeId() {}

    public CanGetForFreeId(UUID customerPersonId, UUID bananaBarcode) {
        this.customerPersonId = customerPersonId;
        this.bananaBarcode = bananaBarcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CanGetForFreeId that = (CanGetForFreeId) o;
        return Objects.equals(customerPersonId, that.customerPersonId) &&
                Objects.equals(bananaBarcode, that.bananaBarcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerPersonId, bananaBarcode);
    }
}
