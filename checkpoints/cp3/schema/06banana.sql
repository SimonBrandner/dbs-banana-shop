CREATE TABLE banana(
    barcode uuid NOT NULL,
    weight decimal NOT NULL CHECK (weight >= 0),
    shelf_row_number integer NOT NULL,
    shelf_column_number integer NOT NULL,
    UNIQUE (barcode),
    PRIMARY KEY (barcode),
    FOREIGN KEY (shelf_row_number, shelf_column_number) REFERENCES shelf(row_number, column_number) ON DELETE RESTRICT
);
