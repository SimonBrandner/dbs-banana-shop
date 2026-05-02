CREATE PROCEDURE order_bananas(
    v_customer_person_id uuid,
    v_employee_person_id uuid,
    v_banana_barcodes uuid []
) LANGUAGE plpgsql AS
$$
DECLARE
v_timestamp TIMESTAMPTZ := CURRENT_TIMESTAMP;

BEGIN
INSERT INTO
    banana_order(
        creation_time,
        customer_person_id,
        employee_person_id
    )
VALUES
    (
        v_timestamp,
        v_customer_person_id,
        v_employee_person_id
    );

INSERT INTO
    order_contains_banana(
        banana_barcode,
        customer_person_id,
        order_creation_time
    )
SELECT
    unnest(v_banana_barcodes),
    v_customer_person_id,
    v_timestamp;

END;

$$
;
