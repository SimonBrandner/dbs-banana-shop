CREATE TABLE can_get_for_free(
    customer_person_id uuid NOT NULL REFERENCES customer(person_id) ON DELETE RESTRICT,
    banana_barcode uuid NOT NULL REFERENCES banana(barcode) ON DELETE RESTRICT,
    UNIQUE (customer_person_id, banana_barcode),
    PRIMARY KEY (customer_person_id, banana_barcode)
);
