#show link: underline
#set par(justify: true)

#let sql-code(path) = {
  let sql = read(path)

  raw(sql, lang: "sql")
}

= Advanced database features

In this document we describe the advanced features used in our database. The code mentioned here is also available in plaintext form in #link("https://github.com/SimonBrandner/dbs-banana-shop")[a GitHub repository].

== Transaction

When a banana order is created it is paramount that the bananas ordered and the order itself are linked. It would be unpleasant if either got lost. For this reason we have a procedure

#sql-code("./sql/banana_order_transaction.sql")

which we call in a transaction like so:

```sql
SET TRANSACTION ISOLATION LEVEL READ COMMITED;
BEGIN;
CALL order_bananas(
    'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'::uuid,
    'b2ccbc22-8c0b-1ef2-cc6d-2bb9bd380b22'::uuid,
    ARRAY[
        'c1eebc11-1c0b-1ef1-bb1d-1bb9bd380c11',
        'd2eebc22-2c0b-2ef2-bb2d-2bb9bd380d22'
    ]::uuid[]
);
COMMIT;
```

We use `ISOLATION LEVEL READ COMMITED`, because stricter isolation is not required and would only slow down the transaction execution.

== View

Sometimes it may be useful to see which employees sold bananas to some customers. Therefore, we introduce a view:

#sql-code("./sql/sold_to_view.sql")

Running

```sql
SELECT * FROM sold_to_view;
```

gives

```
employee_name                |customer_name              |
-----------------------------+---------------------------+
Ivan Marek                   |Alexandr Ševčík            |
Marie Doležalová             |Dobroslav Bárta            |
Květoslava Musilová          |Vanda Marešová             |
Žofie Křížová                |Ivana Pavlíková            |
Žofie Křížová                |Radoslav Kadlec            |
Vilma Růžičková              |Jan Vlček                  |
Alexander Kratochvíl         |Robert Kopecký             |
Linda Vaňková                |Vladimír Matoušek          |
Ignác Prokop                 |Mahulena Zemanová          |
Sára Vávrová                 |Jaroslav Dvořák            |
Ján Polák                    |Edita Strnadová            |
...                          |...                        |
```

== Trigger

Some of our customers have special privileges -- they can get some bananas for free. It would be quite unfortunate if a customer thought they can get a certain banana for free, even though it has already been sold. For this reason we introduce the following trigger:

#sql-code("./sql/can_get_for_free_trigger.sql")

We can run
```
SELECT * FROM can_get_for_free;
```
to see that the customer `f2f5a861-645d-09c7-82b7-19060e99dde6` can get the banana `49207fba-f789-41bc-976b-3b009a8ae54d` for free.
```
customer_person_id                  |banana_barcode                      |
------------------------------------+------------------------------------+
f2f5a861-645d-09c7-82b7-19060e99dde6|49207fba-f789-41bc-976b-3b009a8ae54d|
```

However, once we run
```sql
INSERT INTO
order_contains_banana(banana_barcode, customer_person_id, order_creation_time)
VALUES
(
'49207fba-f789-41bc-976b-3b009a8ae54d',
'f2f5a861-645d-09c7-82b7-19060e99dde6',
'2026-02-04 08:07:17.370819+01'
);
```
to make the customer order the banana, we can see, by running
```
SELECT * FROM can_get_for_free;
```
again, that the banana was removed:
```
customer_person_id|banana_barcode|
------------------+--------------+
```

== Index

The `person_id` column is not the primary; therefore, it will not be indexed. However, it will often be used during joins, and so we are going to index it:

#sql-code("./sql/phone_number_person_id_index.sql")
