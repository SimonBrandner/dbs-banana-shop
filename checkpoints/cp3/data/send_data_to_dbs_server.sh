#!/bin/bash

#this shouldn't be secret info, .env not used.
DB_HOST="slon.felk.cvut.cz"
DB_USER="copakond"
DB_NAME="brandsi1"
CSV_DIR="checkpoints/cp3/data"

psql -h $DB_HOST -U $DB_USER -d $DB_NAME -c "\copy person FROM '$CSV_DIR/person.csv' WITH (FORMAT csv, HEADER true);"
psql -h $DB_HOST -U $DB_USER -d $DB_NAME -c "\copy phone_number FROM '$CSV_DIR/phone_number.csv' WITH (FORMAT csv, HEADER true);"
psql -h $DB_HOST -U $DB_USER -d $DB_NAME -c "\copy employee FROM '$CSV_DIR/employee.csv' WITH (FORMAT csv, HEADER true);"
psql -h $DB_HOST -U $DB_USER -d $DB_NAME -c "\copy customer FROM '$CSV_DIR/customer.csv' WITH (FORMAT csv, HEADER true);"
psql -h $DB_HOST -U $DB_USER -d $DB_NAME -c "\copy shelf FROM '$CSV_DIR/shelf.csv' WITH (FORMAT csv, HEADER true);"
psql -h $DB_HOST -U $DB_USER -d $DB_NAME -c "\copy banana FROM '$CSV_DIR/banana.csv' WITH (FORMAT csv, HEADER true);"
psql -h $DB_HOST -U $DB_USER -d $DB_NAME -c "\copy supervises FROM '$CSV_DIR/supervises.csv' WITH (FORMAT csv, HEADER true);"
psql -h $DB_HOST -U $DB_USER -d $DB_NAME -c "\copy banana_order FROM '$CSV_DIR/banana_order.csv' WITH (FORMAT csv, HEADER true);"
psql -h $DB_HOST -U $DB_USER -d $DB_NAME -c "\copy order_contains_banana FROM '$CSV_DIR/order_contains_banana.csv' WITH (FORMAT csv, HEADER true);"
psql -h $DB_HOST -U $DB_USER -d $DB_NAME -c "\copy can_get_for_free FROM '$CSV_DIR/can_get_for_free.csv' WITH (FORMAT csv, HEADER true);"

echo "done"