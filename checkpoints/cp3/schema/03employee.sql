CREATE TABLE employee(
    person_id uuid NOT NULL REFERENCES person(person_id) ON DELETE RESTRICT,
    UNIQUE (person_id),
    PRIMARY KEY (person_id)
);
