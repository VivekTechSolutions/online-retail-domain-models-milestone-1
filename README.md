🛒 Online Retail Inventory Management System
==============================================

📖 Introduction
----------------

The Online Retail Inventory Management System is a backend application developed using Spring Boot, designed to manage suppliers, products, and orders in an online retail store. The system ensures proper stock tracking, order creation, and relational integrity between entities. It is structured to provide clean, maintainable, and scalable REST APIs.

🎯 Objectives
-------------
The main objectives of this project are:

🏬 Create a centralized inventory management system for retail products.
📦 Maintain suppliers, products, and orders with proper relationships.
🔑 Ensure data integrity using foreign key constraints.
🌐 Provide RESTful APIs for CRUD operations for all entities.
⚠️ Implement validation and exception handling (e.g., prevent orders with insufficient stock).


The system is designed around three main entities:

1️⃣ Supplier
-----------

Represents the company or individual supplying products.

Fields:
🆔 id (Primary Key)
🏷️ name (Unique)

Relationship: One supplier can provide multiple products (one-to-many).

2️⃣ Product
------------

Represents a product available for sale.

Fields:

🆔 id (Primary Key)

🏷️ name (Unique)
💲 price
📦 stockQuantity

🔗 supplierId (Foreign Key to Supplier)

Relationship: Each product is associated with a supplier.

3️⃣ Order
---------

Represents a purchase order placed by a customer.

Fields:

🆔 id (Primary Key)
🔗 productId (Foreign Key to Product)
🔢 quantity
📅 orderDate

Relationship: Each order is linked to exactly one product.

Database Integrity:
------------------

🔗 Every product must have a valid supplier.

🔗 Every order must reference an existing product.

❌ Attempting to insert a product without a valid supplier or an order for a non-existent product will throw an error.

⚙️ Functional Features
🏢 Supplier Management
------------------------

➕ Add suppliers

👀 View suppliers

❌ Delete suppliers

📦 Product Management
--------------------

➕ Add products

👀 View products

❌ Delete products

🔗 Associate products with suppliers

📊 Track stock quantity

📝 Order Management

➕ Create new orders only if sufficient stock exists

👀 View orders

❌ Delete orders
