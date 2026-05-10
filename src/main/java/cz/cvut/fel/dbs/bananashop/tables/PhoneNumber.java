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


    public PhoneNumber() {}

    public String getPhoneNumber() { return phone_number; }
    public void setPhoneNumber(String phone_number) { this.phone_number = phone_number; }

    public Person getPerson() { return person; }
    public void setPerson(Person person) { this.person = person; }
}