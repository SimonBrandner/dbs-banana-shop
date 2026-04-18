CREATE TABLE banana_order(
    creation_time TIMESTAMPTZ NOT NULL,
    customer_person_id uuid REFERENCES customer(person_id) ON DELETE RESTRICT,
    employee_person_id uuid REFERENCES employee(person_id) ON DELETE RESTRICT,
    UNIQUE (creation_time, customer_person_id),
    PRIMARY KEY (creation_time, customer_person_id)
);
