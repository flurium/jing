# JING technical requirements

Purpose: recreate subset of [thing](https://github.com/flurium/thing) in Java Spring Boot.

## General parts

System will contain several parts:

1. authentication/authorization
2. seller functionality
3. rich search/filter of products
4. customer functionality
5. admin panel

### Authentication/authorization

Goal is to provide simple email + password way to login/sign up users.
I suggest to have only 1 form for it: if user exists Jing will login, if not Jing will create new one and login.

### Seller functionality

As a seller you should be able to manage your products. Jing will not provide functionality to edit product fields, because it's unsafe for customers. Functionality:

- publish product
- delete product
- get all products you have published

### Rich search/filter

We want to provide good filter functionality on our main page.
Filters:

- category
- price
- name starts with

### Customer functionality

Except of search customers should be able to:

- add product to cart
- rate/comment items

### Admin panel

If time allows. Admin can manage categories, delete products.

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
| category    | category                 |
| user        | user                     |
| properties  | list of product_property |

| product_property |          |
| ---------------- | -------- |
| product          | product  |
| property         | property |

| property |        |
| -------- | ------ |
| id       | long   |
| name     | string |

In comment and answer user is nullable, because after user is deleted user's answers can be still useful.

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

\*if is_paid == false then order still in cart.

## Design

Overall requirements:

- simple
- clean

I think of using one of classless css frameworks:

- pico css
- sakura css
- mirabo css
