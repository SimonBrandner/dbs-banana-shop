CREATE TABLE order_contains_banana(
    banana_barcode uuid NOT NULL REFERENCES banana(barcode) ON DELETE RESTRICT,
    customer_person_id uuid NOT NULL,
    order_creation_time TIMESTAMPTZ NOT NULL,
    UNIQUE (banana_barcode),
    PRIMARY KEY (banana_barcode),
    FOREIGN KEY (order_creation_time, customer_person_id) REFERENCES banana_order(creation_time, customer_person_id) ON DELETE RESTRICT
);
