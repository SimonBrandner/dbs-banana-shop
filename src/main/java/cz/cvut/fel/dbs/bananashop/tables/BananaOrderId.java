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
    private OffsetDateTime creationTime;

    @Column(name = "customer_person_id", nullable = false)
    private UUID customerPersonId;

    public BananaOrderId() {}

    public BananaOrderId(OffsetDateTime creationTime, UUID customerPersonId) {
        this.creationTime = creationTime;
        this.customerPersonId = customerPersonId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BananaOrderId that = (BananaOrderId) o;
        return Objects.equals(creationTime, that.creationTime) &&
                Objects.equals(customerPersonId, that.customerPersonId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creationTime, customerPersonId);
    }
}
