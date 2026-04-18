CREATE TABLE person(
    person_id uuid NOT NULL,
    birth_number varchar(255),
    email varchar(255),
    name varchar(255),
    city varchar(255),
    street varchar(255),
    postal_code varchar(255),
    UNIQUE (person_id),
    PRIMARY KEY (person_id)
);
