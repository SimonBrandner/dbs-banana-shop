SELECT shelf.row_number, shelf.column_number, shelf.price_per_kg
FROM shelf
WHERE shelf.price_per_kg >= 10;