CREATE TABLE shelf(
    row_number integer NOT NULL CHECK (row_number >= 0),
    column_number integer NOT NULL CHECK (column_number >= 0),
    price_per_kg decimal NOT NULL CHECK (price_per_kg >= 0),
    UNIQUE (column_number, row_number),
    PRIMARY KEY (column_number, row_number)
);
