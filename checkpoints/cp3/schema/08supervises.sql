CREATE TABLE supervises(
    junior uuid NOT NULL REFERENCES employee(person_id) ON DELETE RESTRICT,
    senior uuid NOT NULL REFERENCES employee(person_id) ON DELETE RESTRICT,
    UNIQUE (junior),
    PRIMARY KEY (junior)
);
