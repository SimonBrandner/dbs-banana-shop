package cz.cvut.fel.dbs.bananashop.tables;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "phone_number")
public class PhoneNumber {
    @Id
    @Column(name = "phone_number", nullable = false, unique = true)
    private String phone_number;

    @ManyToOne(optional = false)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;
}