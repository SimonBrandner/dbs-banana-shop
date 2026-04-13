SELECT
    banana.barcode,
    shelf.price_per_kg,
    banana.weight
FROM
    shelf
    INNER JOIN banana ON shelf.row_number = banana.shelf_row_number
    AND shelf.column_number = banana.shelf_column_number;
