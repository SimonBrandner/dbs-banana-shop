CREATE TABLE person (
    birth_number varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    name varchar(255) NOT NULL,
    city varchar(255) NOT NULL,
    street varchar(255) NOT NULL,
    postal_code varchar(255) NOT NULL,
    UNIQUE (birth_number),
    UNIQUE (email),
    PRIMARY KEY (birth_number)
);

CREATE TABLE phone_number (
    phone_number varchar(255) NOT NULL,
    person_birth_number varchar(255) NOT NULL REFERENCES person(birth_number),
    UNIQUE (phone_number),
    PRIMARY KEY (phone_number)
);

CREATE TABLE employee (
    person_birth_number varchar(255) NOT NULL REFERENCES person(birth_number),
    UNIQUE (person_birth_number),
    PRIMARY KEY (person_birth_number)
);

CREATE TABLE customer (
    person_birth_number varchar(255) NOT NULL REFERENCES person(birth_number),
    UNIQUE (person_birth_number),
    PRIMARY KEY (person_birth_number)
);

CREATE TABLE shelf(
    row_number integer NOT NULL,
    column_number integer NOT NULL,
    price_per_kg decimal NOT NULL,
    UNIQUE (column_number, row_number),
    PRIMARY KEY (column_number, row_number)
);

CREATE TABLE banana (
    barcode integer NOT NULL,
    weight decimal NOT NULL,
    shelf_row_number integer NOT NULL,
    shelf_column_number integer NOT NULL,
    UNIQUE (barcode),
    PRIMARY KEY (barcode),
    FOREIGN KEY (shelf_row_number, shelf_column_number) REFERENCES shelf(row_number, column_number)
);

CREATE TABLE banana_order(
    creation_time TIMESTAMPTZ NOT NULL,
    customer_birth_number varchar(255) NOT NULL REFERENCES customer(person_birth_number),
    employee_birth_number varchar(255) NOT NULL REFERENCES employee(person_birth_number),
    UNIQUE (creation_time, customer_birth_number),
    PRIMARY KEY (creation_time, customer_birth_number)
);

CREATE TABLE supervises(
    junior varchar(255) NOT NULL REFERENCES employee(person_birth_number),
    senior varchar(255) NOT NULL REFERENCES employee(person_birth_number),
    UNIQUE (junior),
    PRIMARY KEY (junior)
);

CREATE TABLE can_get_for_free(
    customer_birth_number varchar(255) NOT NULL REFERENCES customer(person_birth_number),
    banana_barcode integer NOT NULL REFERENCES banana(barcode),
    UNIQUE (customer_birth_number, banana_barcode),
    PRIMARY KEY (customer_birth_number, banana_barcode)
);

CREATE TABLE order_contains_banana(
    banana_barcode integer NOT NULL REFERENCES banana(barcode),
    customer_birth_number varchar(255) NOT NULL,
    order_creation_time TIMESTAMPTZ NOT NULL,
    UNIQUE (banana_barcode),
    PRIMARY KEY (banana_barcode),
    FOREIGN KEY (order_creation_time, customer_birth_number) REFERENCES banana_order(creation_time, customer_birth_number)
);
