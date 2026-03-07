# Check Point 1

In this document, we describe our ER diagram and the reasoning behind it.

## Team Members

- Šimon Brandner
- Ondřej Čopák

## ER Digram

![ER Diagram](./erdiagram.png)

## Description

In this section we provide the description of entities and their relations.

#### Banana

Central to our model is the **banana** entity. It has two attributes: a **barcode** (which identifies it) and a **weight (kg)** which is going to become useful once it is purchased. A banana may be **contained** inside _one_ (we avoid multiple **customers** purchasing the same **banana**) **order**. _Every_ banana is located in _one_ (given its macroscopic properties it may not be in two places at once) **shelf**. A **banana** may be **showcase sample** for _precisely one_ (???) **shelf**.

#### Shelf

A shelf is identified with its **row** and **column** number inside the shop. It also has an attribute **price/kg** which is going to play a role in determining the prices of bananas. _Each_ shelf has _one_ (there is no need for multiple of them) **showcase sample** **banana**.

#### Order

An order's only attribute is its **creation time**. It is identified by the **creation time** and the **customer** that **placed** it. It **contains** (at least _one_; an empty order is nonsensical) the **bananas** that were purchased. **Each** order has to be processed by an **employee** (letting multiple **employees** **process** _one_ **order** would be ineffective).

#### Person

A **person** is the base for the **customer** and **employee** entities. A **person** is identified by an **e-mail address** _or_ a **birth number**. This entity also has a **name**, **address** (composed of **city**, **street**, and **postal code**), and a **phone number**. _Each_ person must be an **employee** or a **customer** (there is no good reason to include other **persons** in our model). Some **persons** may be both **employees** and **customers** (this allows for the punchline where an **employee** starts eating all the **bananas**).

#### Employee

An **employee** _may_ (we consider the owner to be an employee and they do not have any **supervisor**) have a **supervisor** who is also an **employee**.

#### Customer

A **customer** _may_ **place** _any number_ (they become a potential **customer** once they enter the shop; not when they buy something) of **orders**. Some **customers** have special rights and they **can get** _some_ (there is no reason to limit their special rights to just one banana) **bananas for free**.
