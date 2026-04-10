SELECT
    banana.shelf_row_number,
    banana.shelf_column_number,
    AVG(weight) AS average_weight
FROM
    banana
GROUP BY
    banana.shelf_row_number,
    banana.shelf_column_number;
