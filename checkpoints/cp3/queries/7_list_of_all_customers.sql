SELECT person.name
FROM person
WHERE person.person_id IN (
    SELECT banana_order.customer_person_id
    FROM banana_order
);