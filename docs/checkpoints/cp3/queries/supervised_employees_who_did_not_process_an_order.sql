SELECT
    supervises.junior AS employee_person_id
FROM
    supervises
EXCEPT
SELECT
    banana_order.employee_person_id AS employee_person_id
FROM
    banana_order;
