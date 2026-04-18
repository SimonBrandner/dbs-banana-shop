import csv
import random
import uuid
from faker import Faker
from enum import Enum

class Distribution(Enum):
    LINEAR = 1
    QUADRATIC_MIN = 2 # most of them is near the min
    QUADRATIC_MAX = 3 # most of them is near the max
    NORMAL = 4

# SETTINGS OF PARAMETERS
RANDOM_SEED = 131
CSV_DIR = "checkpoints/cp3/data/" # output dir

NUM_PERSONS = 50000 # each person can be both (independent events)
PROB_CUSTOMER = 0.90
PROB_EMPLOYEE = 0.15
PROB_JUNIOR_EMPLOYEE = 0.30

PHONE_NUMBERS = (0, 5) # min, max
DISTR_PHONE = Distribution.LINEAR

NUM_SHELVES_ROWS = 50
NUM_SHELVES_COLS = 50
SHELF_PRICES = (0, 1000, 0.1) # min, max, step
DISTR_SHELF_PRICES = Distribution.NORMAL

NUM_BANANAS = 131071 # 2^17 - 1
BANANA_WEIGHT = (0.100, 10.000, 0.1) # min, max, step
DISTR_BANANA_WEIGHT = Distribution.NORMAL

NUM_ORDERS = 4200
BANANAS_PER_ORDER = (1, 50) # min, max
DISTR_BANANAS_PER_ORDER = Distribution.QUADRATIC_MIN

PROB_FREE_BANANA = 0.001
FREE_BANANS = (1, 5) # min, max
DISTR_FREE_BANANS = Distribution.QUADRATIC_MIN
# END OF PARAMETER SETTINGS









random.seed(RANDOM_SEED)
Faker.seed(RANDOM_SEED)
fake = Faker('cs_CZ')

generated_persons = []
generated_customers = []
generated_employees = []
generated_shelves = []
generated_bananas = []
available_bananas = []
generated_orders = []




def get_deterministic_uuid():
    return str(uuid.UUID(int=random.getrandbits(128)))

def apply_distribution(min_val, max_val, dist, step=None, is_int=False):
    if dist == Distribution.LINEAR:
        val = random.uniform(min_val, max_val)
    elif dist == Distribution.QUADRATIC_MIN:
        val = min_val + (max_val - min_val) * (random.random() ** 2)
    elif dist == Distribution.QUADRATIC_MAX:
        val = max_val - (max_val - min_val) * (random.random() ** 2)
    elif dist == Distribution.NORMAL:
        mu = (min_val + max_val) / 2
        sigma = (max_val - min_val) / 6
        val = random.gauss(mu, sigma)
        val = max(min_val, min(max_val, val))
    else:
        val = random.uniform(min_val, max_val)

    if step is not None:
        val = round(val / step) * step

    if is_int:
        return int(round(val))
    return val

def generate_persons_and_roles(n):
    with open(CSV_DIR+'person.csv', 'w', newline='', encoding='utf-8') as f_person:
        with open(CSV_DIR+'phone_number.csv', 'w', newline='', encoding='utf-8') as f_phone:
            with open(CSV_DIR+'customer.csv', 'w', newline='', encoding='utf-8') as f_cust:
                with open(CSV_DIR+'employee.csv', 'w', newline='', encoding='utf-8') as f_emp:

                    w_person = csv.writer(f_person)
                    w_phone = csv.writer(f_phone)
                    w_cust = csv.writer(f_cust)
                    w_emp = csv.writer(f_emp)

                    w_person.writerow(['person_id', 'birth_number', 'email', 'name', 'city', 'street', 'postal_code'])
                    w_phone.writerow(['phone_number', 'person_id'])
                    w_cust.writerow(['person_id'])
                    w_emp.writerow(['person_id'])

                    for _ in range(n):
                        p_id = get_deterministic_uuid()
                        generated_persons.append(p_id)

                        w_person.writerow([
                            p_id,
                            fake.birth_number(),
                            fake.unique.email(),
                            fake.name(),
                            fake.city(),
                            fake.street_name(),
                            fake.postcode()
                        ])

                        num_phones = apply_distribution(PHONE_NUMBERS[0], PHONE_NUMBERS[1], DISTR_PHONE, is_int=True)
                        for _ in range(num_phones):
                            w_phone.writerow([fake.unique.phone_number(), p_id])

                        is_customer = random.random() < PROB_CUSTOMER
                        is_employee = random.random() < PROB_EMPLOYEE

                        if not is_customer and not is_employee:
                            is_customer = True

                        if is_customer:
                            generated_customers.append(p_id)
                            w_cust.writerow([p_id])
                        if is_employee:
                            generated_employees.append(p_id)
                            w_emp.writerow([p_id])

def generate_shelves():
    with open(CSV_DIR+'shelf.csv', 'w', newline='', encoding='utf-8') as f:
        w = csv.writer(f)
        w.writerow(['row_number', 'column_number', 'price_per_kg'])

        for r in range(1, NUM_SHELVES_ROWS + 1):
            for c in range(1, NUM_SHELVES_COLS + 1):
                generated_shelves.append((r, c))
                price = apply_distribution(SHELF_PRICES[0], SHELF_PRICES[1], DISTR_SHELF_PRICES, step=SHELF_PRICES[2])
                w.writerow([r, c, round(price, 2)])

def generate_bananas(n):
    with open(CSV_DIR+'banana.csv', 'w', newline='', encoding='utf-8') as f:
        w = csv.writer(f)
        w.writerow(['barcode', 'weight', 'shelf_row_number', 'shelf_column_number'])

        for _ in range(n):
            b_id = get_deterministic_uuid()
            generated_bananas.append(b_id)
            available_bananas.append(b_id)

            weight = apply_distribution(BANANA_WEIGHT[0], BANANA_WEIGHT[1], DISTR_BANANA_WEIGHT, step=BANANA_WEIGHT[2])
            shelf = random.choice(generated_shelves)

            w.writerow([b_id, round(weight, 3), shelf[0], shelf[1]])

def generate_orders_and_contents(n):
    with open(CSV_DIR+'banana_order.csv', 'w', newline='', encoding='utf-8') as f_order:
        with open('order_contains_banana.csv', 'w', newline='', encoding='utf-8') as f_contains:

            w_order = csv.writer(f_order)
            w_contains = csv.writer(f_contains)

            w_order.writerow(['creation_time', 'customer_person_id', 'employee_person_id'])
            w_contains.writerow(['banana_barcode', 'customer_person_id', 'order_creation_time'])

            for _ in range(n):
                if not available_bananas:
                    break

                creation_time = fake.date_time_between(start_date='-1y', end_date='now').isoformat() + "+00:00"
                c_id = random.choice(generated_customers)
                e_id = random.choice(generated_employees)

                order_key = (creation_time, c_id)
                if order_key in generated_orders:
                    continue

                generated_orders.append(order_key)
                w_order.writerow([creation_time, c_id, e_id])

                num_bananas = apply_distribution(BANANAS_PER_ORDER[0], BANANAS_PER_ORDER[1], DISTR_BANANAS_PER_ORDER, is_int=True)
                num_bananas = min(num_bananas, len(available_bananas))

                for _ in range(num_bananas):
                    b_id = available_bananas.pop(random.randrange(len(available_bananas)))
                    w_contains.writerow([b_id, c_id, creation_time])

def generate_supervises():
    with open(CSV_DIR+'supervises.csv', 'w', newline='', encoding='utf-8') as f:
        w = csv.writer(f)
        w.writerow(['junior', 'senior'])

        num_juniors = int(len(generated_employees) * PROB_JUNIOR_EMPLOYEE)
        juniors = random.sample(generated_employees, num_juniors)
        seniors = [e for e in generated_employees if e not in juniors]

        for junior in juniors:
            if seniors:
                senior = random.choice(seniors)
                w.writerow([junior, senior])

def generate_free_bananas_rights():
    with open(CSV_DIR+'can_get_for_free.csv', 'w', newline='', encoding='utf-8') as f:
        w = csv.writer(f)
        w.writerow(['customer_person_id', 'banana_barcode'])

        num_lucky = int(len(generated_customers) * PROB_FREE_BANANA)
        lucky_customers = random.sample(generated_customers, num_lucky)

        for customer in lucky_customers:
            num_free = apply_distribution(FREE_BANANS[0], FREE_BANANS[1], DISTR_FREE_BANANS, is_int=True)
            free_bananas = random.sample(generated_bananas, num_free)
            for b_id in free_bananas:
                w.writerow([customer, b_id])

if __name__ == "__main__":
    print(f"starting data generation (seed: {RANDOM_SEED})")

    generate_persons_and_roles(NUM_PERSONS)
    print(f"generated persons: {len(generated_persons)} ({len(generated_customers)} customers, {len(generated_employees)} employees)")

    generate_shelves()
    print(f"generated shelves: {len(generated_shelves)}")

    generate_bananas(NUM_BANANAS)
    print(f"generated bananas: {len(generated_bananas)}")

    generate_orders_and_contents(NUM_ORDERS)
    print(f"generated orders: {len(generated_orders)}")

    generate_supervises()
    print("generated supervises relationships")

    generate_free_bananas_rights()
    print("generated free banana rights")
    print("done")