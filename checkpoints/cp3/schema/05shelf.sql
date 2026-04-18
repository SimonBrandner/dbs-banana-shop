CREATE TABLE shelf(
    row_number integer NOT NULL,
    column_number integer NOT NULL,
    price_per_kg decimal NOT NULL,
    UNIQUE (column_number, row_number),
    PRIMARY KEY (column_number, row_number)
);
