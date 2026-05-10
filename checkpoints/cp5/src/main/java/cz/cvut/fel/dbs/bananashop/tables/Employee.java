package cz.cvut.fel.dbs.bananashop.tables;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "employee")
public class Employee extends Person {
    public Employee() {}
}