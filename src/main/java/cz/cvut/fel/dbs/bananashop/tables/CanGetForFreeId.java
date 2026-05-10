package cz.cvut.fel.dbs.bananashop.tables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class CanGetForFreeId implements Serializable {
    @Column(name = "customer_person_id", nullable = false)
    private UUID customer_person_id;

    @Column(name = "banana_barcode", nullable = false)
    private UUID banana_barcode;



    public CanGetForFreeId() {}

    public CanGetForFreeId(UUID customerPersonId, UUID bananaBarcode) {
        this.customer_person_id = customerPersonId;
        this.banana_barcode = bananaBarcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CanGetForFreeId that = (CanGetForFreeId) o;
        return Objects.equals(customer_person_id, that.customer_person_id) &&
                Objects.equals(banana_barcode, that.banana_barcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer_person_id, banana_barcode);
    }


    public UUID getCustomerPersonId() { return customer_person_id; }
    public void setCustomerPersonId(UUID customer_person_id) { this.customer_person_id = customer_person_id; }

    public UUID getBananaBarcode() { return banana_barcode; }
    public void setBananaBarcode(UUID banana_barcode) { this.banana_barcode = banana_barcode; }
}
