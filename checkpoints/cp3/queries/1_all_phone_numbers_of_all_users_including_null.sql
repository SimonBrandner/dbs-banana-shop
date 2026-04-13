SELECT person.name, phone_number.phone_number
FROM person
LEFT JOIN phone_number ON person.person_id = phone_number.person_id;