package cz.cvut.fel.dbs.bananashop.tables;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.JOINED)
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


    public Person() {}

    public Person(UUID person_id, String name, String email) {
        this.person_id = person_id;
        this.name = name;
        this.email = email;
    }

    public UUID getPersonId() { return person_id; }
    public void setPersonId(UUID person_id) { this.person_id = person_id; }

    public String getBirthNumber() { return birth_number; }
    public void setBirthNumber(String birth_number) { this.birth_number = birth_number; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getPostalCode() { return postal_code; }
    public void setPostalCode(String postal_code) { this.postal_code = postal_code; }
}