#let sql-code(path) = {
  let sql = read(path)

  raw(sql, lang: "sql")
}

= SQL

In this document we describe our SQL schema and queries.

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

=== Banana price and weight

Retrieves the price and weight for all bananas.

#sql-code("./queries/2_banana_price_and_weight.sql")

=== Expensive bananas

Retrieves all shelves with bananas whose prices is bigger or equal to 10.

#sql-code("./queries/3_selecting_expensive_banans.sql")

=== Average banana weight in shelf

Computes the average weight of bananas in each shelf.

#sql-code("./queries/4_average_banana_weight_in_shelf.sql")

=== Heaviest bananas

Retrieves the 10 heaviest bananas in the store.

#sql-code("./queries/5_top_10_heaviest_bananas.sql")

=== Supervised employee who did not process any order

Finds all lazy employees who require supervision.

#sql-code("./queries/6_supervised_employees_who_did_not_process_an_order.sql")

=== List of all customers

Retrieves all customers who made a purchase at some point.

#sql-code("./queries/7_list_of_all_customers.sql")
