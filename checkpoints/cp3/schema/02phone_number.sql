CREATE TABLE phone_number(
    phone_number varchar(255) NOT NULL,
    person_id uuid NOT NULL REFERENCES person(person_id) ON DELETE RESTRICT,
    UNIQUE (phone_number),
    PRIMARY KEY (phone_number)
);
