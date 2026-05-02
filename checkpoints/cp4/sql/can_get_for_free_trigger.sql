CREATE FUNCTION remove_from_can_get_for_free() RETURNS TRIGGER AS
$$
BEGIN
DELETE FROM
    can_get_for_free
WHERE
    can_get_for_free.banana_barcode = NEW.banana_barcode;

RETURN NEW;

END;

$$
LANGUAGE plpgsql;

CREATE TRIGGER can_get_for_free_trigger
AFTER
INSERT
    ON order_contains_banana FOR EACH ROW EXECUTE FUNCTION remove_from_can_get_for_free();
