package cz.cvut.fel.dbs.bananashop.tables;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "supervises")
public class Supervises {
    @Id
    @Column(name = "junior", nullable = false)
    private UUID juniorId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "junior", nullable = false)
    private Employee junior;

    @ManyToOne(optional = false)
    @JoinColumn(name = "senior", nullable = false)
    private Employee senior;



    public Supervises() {}

    public UUID getJuniorId() { return juniorId; }
    public void setJuniorId(UUID juniorId) { this.juniorId = juniorId; }

    public Employee getJunior() { return junior; }
    public void setJunior(Employee junior) { this.junior = junior; }

    public Employee getSenior() { return senior; }
    public void setSenior(Employee senior) { this.senior = senior; }
}