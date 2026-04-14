DROP SCHEMA public CASCADE;

CREATE SCHEMA public;

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

CREATE TABLE phone_number(
    phone_number varchar(255) NOT NULL,
    person_id uuid NOT NULL REFERENCES person(person_id) ON DELETE RESTRICT,
    UNIQUE (phone_number),
    PRIMARY KEY (phone_number)
);

CREATE TABLE employee(
    person_id uuid NOT NULL REFERENCES person(person_id) ON DELETE RESTRICT,
    UNIQUE (person_id),
    PRIMARY KEY (person_id)
);

CREATE TABLE customer(
    person_id uuid NOT NULL REFERENCES person(person_id) ON DELETE RESTRICT,
    UNIQUE (person_id),
    PRIMARY KEY (person_id)
);

CREATE TABLE shelf(
    row_number integer NOT NULL,
    column_number integer NOT NULL,
    price_per_kg decimal NOT NULL,
    UNIQUE (column_number, row_number),
    PRIMARY KEY (column_number, row_number)
);

CREATE TABLE banana(
    barcode uuid NOT NULL,
    weight decimal NOT NULL,
    shelf_row_number integer NOT NULL,
    shelf_column_number integer NOT NULL,
    UNIQUE (barcode),
    PRIMARY KEY (barcode),
    FOREIGN KEY (shelf_row_number, shelf_column_number) REFERENCES shelf(row_number, column_number) ON DELETE RESTRICT
);

CREATE TABLE banana_order(
    creation_time TIMESTAMPTZ NOT NULL,
    customer_person_id uuid REFERENCES customer(person_id) ON DELETE RESTRICT,
    employee_person_id uuid REFERENCES employee(person_id) ON DELETE RESTRICT,
    UNIQUE (creation_time, customer_person_id),
    PRIMARY KEY (creation_time, customer_person_id)
);

CREATE TABLE supervises(
    junior uuid NOT NULL REFERENCES employee(person_id) ON DELETE RESTRICT,
    senior uuid NOT NULL REFERENCES employee(person_id) ON DELETE RESTRICT,
    UNIQUE (junior),
    PRIMARY KEY (junior)
);

CREATE TABLE can_get_for_free(
    customer_person_id uuid NOT NULL REFERENCES customer(person_id) ON DELETE RESTRICT,
    banana_barcode uuid NOT NULL REFERENCES banana(barcode) ON DELETE RESTRICT,
    UNIQUE (customer_person_id, banana_barcode),
    PRIMARY KEY (customer_person_id, banana_barcode)
);

CREATE TABLE order_contains_banana(
    banana_barcode uuid NOT NULL REFERENCES banana(barcode) ON DELETE RESTRICT,
    customer_person_id uuid NOT NULL,
    order_creation_time TIMESTAMPTZ NOT NULL,
    UNIQUE (banana_barcode),
    PRIMARY KEY (banana_barcode),
    FOREIGN KEY (order_creation_time, customer_person_id) REFERENCES banana_order(creation_time, customer_person_id) ON DELETE RESTRICT
);

GRANT ALL PRIVILEGES ON DATABASE brandsi1 TO copakond;

GRANT ALL PRIVILEGES ON SCHEMA public TO copakond;

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO copakond;
