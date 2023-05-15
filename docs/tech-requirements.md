# JING technical requirements

Purpose: recreate subset of [thing](https://github.com/flurium/thing) in Java Spring.

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
- special properties (if time allows)

### Customer functionality

Except of search customers should be able to:

- add item to cart
- rate/comment items

### Admin panel

If time allows. Admin can manage categories, delete products, ban users.

## Design

Overall requirements:

- simple
- clean

I think of using one of classless css frameworks:

- pico css
- sakura css
- mirabo css
