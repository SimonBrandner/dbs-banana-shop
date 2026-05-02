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
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
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

== View

Sometimes it may be useful to see which employees sold bananas to some customers. Therefore, we introduce a view:

#sql-code("./sql/sold_to_view.sql")

== Trigger

Some of our customers have special privileges -- they can get some bananas for free. It would be quite unfortunate if a customer thought they can get a certain banana for free, even though it has already been sold. For this reason we introduce the following trigger:

#sql-code("./sql/can_get_for_free_trigger.sql")

== Index

The `person_id` column is not the primary; therefore, it will not be indexed. However, it will often be used during joins, and so we are going to index it:

#sql-code("./sql/phone_number_person_id_index.sql")
