DROP DATABASE IF EXISTS db_sales;
CREATE DATABASE db_sales;

USE db_sales;

DROP TABLE IF EXISTS customer;
CREATE TABLE customer
(
id INT NOT NULL AUTO_INCREMENT,
companyName VARCHAR(30),
contactName VARCHAR(30),
address VARCHAR(30),
mailContact VARCHAR(30),
phoneNumber VARCHAR(30),
createdDate DATE,
updatedDate DATE,
deletedDate DATE,
deleted CHAR(1) DEFAULT '0',
PRIMARY KEY (id)
);

DROP TABLE IF EXISTS product;
CREATE TABLE product
(
id INT NOT NULL AUTO_INCREMENT,
productName VARCHAR(30),
unitPrice DOUBLE(8,2),
stock INT,
createdDate DATE,
updatedDate DATE,
deletedDate DATE,
deleted CHAR(1) DEFAULT '0',
PRIMARY KEY (id)
);

DROP TABLE IF EXISTS orders;
CREATE TABLE orders
(
id INT NOT NULL AUTO_INCREMENT,
customerId INT,
orderDate DATETIME,
status VARCHAR(30), 
totalPrice DOUBLE(8,2),
createdDate DATE,
updatedDate DATE,
deletedDate DATE,
deleted CHAR(1) DEFAULT '0',
PRIMARY KEY (id),
FOREIGN KEY (customerId) REFERENCES customer (id) 
);

DROP TABLE IF EXISTS orderDetail;
CREATE TABLE orderDetail
(
id INT NOT NULL AUTO_INCREMENT,
orderId INT NOT NULL,
productId INT NOT NULL,
unitPrice DOUBLE(8,2),
quantity INT,
subTotal DOUBLE(8,2),
PRIMARY KEY (id),
FOREIGN KEY (orderId) REFERENCES orders (id),
FOREIGN KEY (productId) REFERENCES product (id)
);
