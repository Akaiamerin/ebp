CREATE DATABASE IF NOT EXISTS ebp;

USE ebp;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS user;

CREATE TABLE user (
    id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);

TRUNCATE TABLE user;

DROP TABLE IF EXISTS profile;

CREATE TABLE profile (
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user (id)
);

TRUNCATE TABLE profile;

DROP TABLE IF EXISTS goods_type;

CREATE TABLE goods_type (
    id INT NOT NULL AUTO_INCREMENT,
    title varchar(20) NOT NULL,
    PRIMARY KEY (id)
);

TRUNCATE TABLE goods_type;

DROP TABLE IF EXISTS goods;

CREATE TABLE goods (
    id INT NOT NULL AUTO_INCREMENT,
    goods_type_id INT NOT NULL,
    title varchar(20) NOT NULL,
    original_price DECIMAL(10, 2) NOT NULL,
    current_price DECIMAL(10, 2) NOT NULL,
    store INT NOT NULL,
    picture MEDIUMTEXT DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (goods_type_id) REFERENCES goods_type (id)
);

TRUNCATE TABLE goods;

DROP TABLE IF EXISTS cart;

CREATE TABLE cart (
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    goods_id INT NOT NULL,
    goods_num INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (goods_id) REFERENCES goods (id)
);

TRUNCATE TABLE cart;

DROP TABLE IF EXISTS order_status;

CREATE TABLE order_status (
    id INT NOT NULL AUTO_INCREMENT,
    status VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);

TRUNCATE TABLE order_status;

DROP TABLE IF EXISTS order_base;

CREATE TABLE order_base (
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    order_time DATETIME NOT NULL,
    order_status_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (order_status_id) REFERENCES order_status (id)
);

TRUNCATE TABLE order_base;

DROP TABLE IF EXISTS order_detail;

CREATE TABLE order_detail (
    id INT NOT NULL AUTO_INCREMENT,
    order_base_id INT NOT NULL,
    goods_id INT NOT NULL,
    goods_num INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (order_base_id) REFERENCES order_base (id),
    FOREIGN KEY (goods_id) REFERENCES goods (id)
);

TRUNCATE TABLE order_detail;

DROP TABLE IF EXISTS persistent_logins;

CREATE TABLE persistent_logins (
    username VARCHAR (64) NOT NULL,
    series VARCHAR (64) NOT NULL,
    token VARCHAR (64) NOT NULL,
    last_used TIMESTAMP NOT NULL,
    PRIMARY KEY (series)
);

TRUNCATE TABLE persistent_logins;

SET FOREIGN_KEY_CHECKS = 1;