#show link: underline
#set par(justify: true)

#let sql-code(path) = {
  let sql = read(path)

  raw(sql, lang: "sql")
}

= SQL

In this document we describe our SQL schema and queries.

#figure(
  image("schema/diagram.png", width: 100%),
  caption: [SQL Schema diagram taken from DBeaver],
)

== Tables

Here we list and describe the tables used in our database.

=== Person

#sql-code("./schema/01person.sql")

=== Phone number

#sql-code("./schema/02phone_number.sql")

=== Employee

#sql-code("./schema/03employee.sql")

=== Customer

#sql-code("./schema/04customer.sql")

=== Shelf

#sql-code("./schema/05shelf.sql")

=== Banana

#sql-code("./schema/06banana.sql")

=== Banana order

#sql-code("./schema/07banana_order.sql")

=== Supervises

#sql-code("./schema/08supervises.sql")

=== Can get for free

#sql-code("./schema/09can_get_for_free.sql")

=== Order contains banana

#sql-code("./schema/10order_contains_banana.sql")

== Queries

In this section we describe the queries that can be used to retrieve data from the database.

=== Phone numbers of all users including null

This query retrieve all users phone numbers.

#sql-code("./queries/1_all_phone_numbers_of_all_users_including_null.sql")

A response might look like:

```
name                     |phone_number     |
-------------------------+-----------------+
Jitka Šimková            |+420 705 121 219 |
Jitka Šimková            |+420 608 189 632 |
Jitka Šimková            |+420 795 027 400 |
Dana Petrová             |+420 732 452 410 |
Dana Petrová             |+420 604 013 572 |
Dana Petrová             |+420 734 268 280 |
Helena Soukupová         |+420 702 892 739 |
Helena Soukupová         |+420 603 191 683 |
Ivan Marek               |+420 603 616 204 |
...                      |...              |
```

=== Banana price and weight

Retrieves the price and weight for all bananas.

#sql-code("./queries/2_banana_price_and_weight.sql")

A response might look like:

```
barcode                             |price_per_kg|weight|
------------------------------------+------------+------+
c5be07c2-7ac9-3294-d2c7-07308939c744|       406.2|   6.6|
25768d46-2c99-d15c-092e-9aba6c3ed827|       616.7|   5.6|
4ccee218-08fc-0c01-e210-95de7e48a5f1|       341.3|   5.8|
2c38d326-a66d-3dc2-fbe6-69f4aa6032cf|        72.3|   6.3|
5570540d-2bef-3f8c-4653-9dcf20892e7a|       575.5|   5.8|
4cfc0867-678e-83c0-c477-772990c68e47|       464.1|   7.1|
d3231ded-69e6-143a-49c7-ed7eb758d412|       804.4|   5.0|
92d17f6f-d6a3-5c30-3636-34b5b5419a8e|       440.9|   3.8|
8ed39316-1d8c-3ecb-d4e9-fee80cd65f4f|       544.6|   3.9|
d516ecb7-d985-d87d-122c-bbe77ec0317b|       319.3|   5.6|
1228dc74-f41a-e390-b803-378388cfdab0|       438.5|   4.3|
6fda58ff-c2ff-c11e-c681-d2ca368cdf18|       450.5|   3.5|
...                                 |         ...|   ...|
```

=== Expensive bananas

Retrieves all shelves with bananas whose prices is bigger or equal to 10.

#sql-code("./queries/3_selecting_expensive_banans.sql")

A response might look like:

```
row_number|column_number|price_per_kg|
----------+-------------+------------+
         1|            1|       730.8|
         1|            2|       724.0|
         1|            3|       487.0|
         1|            4|       946.0|
         1|            5|       542.8|
         1|            6|       504.9|
         1|            7|       747.2|
         1|            8|       483.5|
         1|            9|       486.4|
         1|           10|       295.4|
         1|           11|       674.0|
         1|           12|       420.3|
       ...|          ...|         ...|
```

=== Average banana weight in shelf

Computes the average weight of bananas in each shelf.

#sql-code("./queries/4_average_banana_weight_in_shelf.sql")

A response might look like:

```
shelf_row_number|shelf_column_number|average_weight    |
----------------+-------------------+------------------+
              25|                 10|5.0131147540983607|
              18|                 28|4.9541666666666667|
              41|                 49|5.0160000000000000|
              48|                 47|5.3227272727272727|
              18|                 36|5.0392156862745098|
              32|                  5|5.0339622641509434|
               1|                  1|4.8682539682539683|
              29|                 12|4.7255319148936170|
              43|                 31|4.9363636363636364|
              38|                 34|4.8327586206896552|
              40|                 46|5.0596491228070175|
               1|                 41|5.3745098039215686|
             ...|                ...|               ...|
```

=== Heaviest bananas

Retrieves the 10 heaviest bananas in the store.

#sql-code("./queries/5_top_10_heaviest_bananas.sql")

A response might look like:

```
barcode                             |weight|
------------------------------------+------+
61e22441-8872-5eb0-f492-d82ba250c904|  10.0|
b4cc9593-8a11-c950-60ba-8cac568bc1e0|  10.0|
e829da1e-e10a-5cd7-a0c4-4136379b4282|  10.0|
00ca869f-5250-7b7b-ec4e-1b7950fc47e4|  10.0|
9d86b69b-babe-41ce-5b17-e4d3e0c8b9f2|  10.0|
f9d7d912-b920-12ee-3d21-e6f353d95f41|  10.0|
7c9ed525-d725-7d94-4a10-d7b8f50f9ece|  10.0|
bd4bbfff-2a4b-2990-449d-7f8b7fef8e1d|  10.0|
6ef3c8f2-c119-7455-b3a3-0e1165d48652|  10.0|
23866212-f936-77b3-9022-a42e63c14db3|  10.0|
```

=== Supervised employee who did not process any order

Finds all lazy employees who require supervision.

#sql-code("./queries/6_supervised_employees_who_did_not_process_an_order.sql")

A response might look like:

```
employee_person_id                  |
------------------------------------+
e3cb9eae-bc1e-0f2a-0373-a6d39b222283|
36fe7a32-7412-a3df-8d39-4bfbe8298155|
9beee156-ba51-97be-50d9-06264023be24|
39c046ea-d956-6c7c-5104-d5001e1560d8|
1c1d026f-2791-0445-14d5-c3defe7a0bc5|
c05c16f3-e94f-bdb1-ee8c-5887bbbcabc7|
d3bfe7fa-8185-2818-f0ff-33fcc39d4846|
b8cef414-8655-c4e6-654e-9e82385ae2c7|
d12ed90b-8f54-d76a-e962-0fbe6d39e3e9|
29523485-849f-7cbe-4f44-87530122b4cd|
232be929-753d-0cf9-2d5c-9c9418684c46|
                                 ...|
```

=== List of all customers

Retrieves all customers who made a purchase at some point.

A response might look like:

```
name                       |
---------------------------+
Radka Fialová              |
Romana Valentová           |
Věra Svobodová             |
MVDr. Antonín Musil        |
Leona Němečková            |
Tereza Pavlíková           |
Štěpánka Hrušková          |
Julie Blažková             |
Ema Dušková                |
Ester Jandová              |
Norbert Liška              |
...                        |
```

#sql-code("./queries/7_list_of_all_customers.sql")
