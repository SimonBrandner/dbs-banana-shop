package cz.cvut.fel.dbs.bananashop.tables;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "person_id", nullable = false, unique = true)
    private UUID person_id;

    @Column(name = "birth_number")
    private String birth_number;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "postal_code")
    private String postal_code;
}