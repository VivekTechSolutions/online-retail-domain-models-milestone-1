Online Retail Inventory Management System
============================================

Introduction
------------
The Online Retail Inventory Management System is a backend application developed using Spring Boot, designed to manage suppliers, products, and orders in an online retail store. The system ensures proper stock tracking, order creation, and relational integrity between entities. It is structured to provide clean, maintainable, and scalable REST APIs.

Objectives
---------
The main objectives of this project are:

To create a centralized inventory management system for retail products.
To maintain suppliers, products, and orders with proper relationships.
To ensure data integrity using foreign key constraints.
To provide RESTful APIs for CRUD operations for all entities.
To implement validation and exception handling, e.g., preventing orders with insufficient stock.

system Design and Database Structure
-------------------------------------
The system is designed around three main entities:

1. Supplier
************
Represents the company or individual supplying products.

Fields:

id (Primary Key)
name (Unique)
Relationship: One supplier can provide multiple products (one-to-many).

2. Product
   ********

Represents a product available for sale.

Fields:

id (Primary Key)
name (Unique)
price
stockQuantity

supplierId (Foreign Key to Supplier)
Relationship: Each product is associated with a supplier.

3. Order
-------
Represents a purchase order placed by a customer.

Fields:

id (Primary Key)
productId (Foreign Key to Product)
quantity
orderDate

Relationship: Each order is linked to exactly one product.
Database Integrity

Foreign keys ensure that:

Every product must have a valid supplier.

Every order must reference an existing product.

Attempting to insert a product without a valid supplier or an order for a non-existent product will throw an error.

Functional Features
-------------------
Supplier Management
------------------

Add, view, and delete suppliers.

Product Management
Add, view, and delete products.
Associate products with suppliers.
Track stock quantity.

Order Management

Create new orders only if sufficient stock exists.
View and delete orders.
Automatically reduce stock quantity after an order.
