CREATE VIEW sold_to_view AS
SELECT
    employee_person.name AS employee_name,
    customer_person.name AS customer_name
FROM
    banana_order
    INNER JOIN customer ON banana_order.customer_person_id = customer.person_id
    INNER JOIN person customer_person ON customer.person_id = customer_person.person_id
    INNER JOIN employee ON banana_order.employee_person_id = employee.person_id
    INNER JOIN person employee_person ON employee.person_id = employee_person.person_id;
