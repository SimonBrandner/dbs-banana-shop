CREATE
OR REPLACE VIEW sold_to_view AS
SELECT
    employee_person.name AS employee_name,
    customer_person.name AS customer_name
FROM
    banana_order
    INNER JOIN person customer_person ON banana_order.customer_person_id = customer_person.person_id
    INNER JOIN person employee_person ON banana_order.employee_person_id = employee_person.person_id;
