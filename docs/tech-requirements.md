# JING technical requirements

Purpose: recreate a subset of [thing](https://github.com/flurium/thing) in Java Spring Boot.

## General parts

The system will contain several parts:

1. authentication/authorization
2. seller functionality
3. Search/filter of products
4. customer functionality
5. admin panel

### Authentication/authorization

The goal is to provide a simple email + password way to log in/sign up users.
I suggest having only 1 form for it: if the user exists Jing will log in, if not Jing will create a new one and log in.

### Seller functionality

As a seller, you should be able to manage your products. Jing will not provide the functionality to edit product fields after publishing, because it's unsafe for customers. Functionality:

- publish product
- delete product
- get all products you have published

### Search/filter

We want to provide good filter functionality on our main page.
Filters:

- category
- price
- name starts with

If time allows, make autocomplete on typing.

### Customer functionality

Except for search customers should be able to:

- add the product to cart
- rate/comment items

### Admin panel

If time allows. Admin can manage categories, and delete products.

## Database

| user     |                  |
| -------- | ---------------- |
| id       | long             |
| ...      |                  |
| products | list of product  |
| comments | list of comments |
| answers  | list of answers  |

| product     |                          |
| ----------- | ------------------------ |
| id          | long                     |
| name        | string                   |
| price       | double                   |
| description | string                   |
| image       | string                   |
| state       | published or draft       |
| category    | category                 |
| user        | user                     |
| properties  | list of product_property |

| property |         |
| -------- | ------- |
| id       | long    |
| name     | string  |
| value    | string  |
| product  | product |

In comment and answer the user is nullable because after the user is deleted user's answers can be still useful.

| comment |                |
| ------- | -------------- |
| id      | long           |
| date    | datetime       |
| grade   | int 1..5       |
| content | string         |
| user    | nullable user  |
| product | product        |
| answers | list of answer |

| answer  |               |
| ------- | ------------- |
| id      | long          |
| content | string        |
| date    | datetime      |
| comment | comment       |
| user    | nullable user |

| category |                  |
| -------- | ---------------- |
| id       | long             |
| name     | string           |
| products | list of products |

| order   |         |
| ------- | ------- |
| id      | long    |
| amount  | int     |
| is_paid | bool    |
| user    | user    |
| product | product |

\*if is_paid == false then the order is still in the cart.

## Design

Overall requirements:

- simple
- clean

I think of using one of the classless css frameworks:

- pico css
- sakura css
- mirabo css

### Pages

- home/search/filter - search form, show products and categories.
- auth - login or register
- product - show concrete product info and properties, try to show 5-10 similar products (lazy load). admin should have a delete product button.
- comments - comments for concrete products, answers, write answer form
- cart - show orders which aren't paid
- history - show orders which are paid
- create a product - create a new product form, then go to add properties form. can add state (published/draft), while draft we can edit it.
- my products - show all user products
- my sales - show the history of orders of user's products
- categories management - add, delete, and edit categories only for admins

## Team

### Roman Koshchei

Team Leads. Look after other teammates. Do global decisions.

### Mariia Shcherbak

Really beautiful. If she understands she will do excellent.

### Lytvynenko Vitalii

Great man. Have fantastic thoughts. Do some difficult stuff.

### Fliud Dmytro

Funny guy. Need some help to keep the quality of the code.
