package cz.cvut.fel.dbs.bananashop.tables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class BananaOrderId implements Serializable {
    @Column(name = "creation_time", nullable = false)
    private OffsetDateTime creation_time;

    @Column(name = "customer_person_id", nullable = false)
    private UUID customer_person_id;



    public BananaOrderId() {}

    public BananaOrderId(OffsetDateTime creationTime, UUID customerPersonId) {
        this.creation_time = creationTime;
        this.customer_person_id = customerPersonId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BananaOrderId that = (BananaOrderId) o;
        return Objects.equals(creation_time, that.creation_time) &&
                Objects.equals(customer_person_id, that.customer_person_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creation_time, customer_person_id);
    }


    public OffsetDateTime getCreationTime() { return creation_time; }
    public void setCreationTime(OffsetDateTime creation_time) { this.creation_time = creation_time; }

    public UUID getCustomerPersonId() { return customer_person_id; }
    public void setCustomerPersonId(UUID customer_person_id) { this.customer_person_id = customer_person_id; }
}
