-- CREATE DATABASE library_m3s01;

CREATE TABLE IF NOT EXISTS authors(
    id bigserial PRIMARY KEY,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    pseudonym varchar(25)
);

CREATE TABLE IF NOT EXISTS categories(
    id bigserial PRIMARY KEY,
    name varchar(255) NOT NULL,
    description text,
    parent_category_id bigint REFERENCES categories(id)
);

CREATE TABLE IF NOT EXISTS books(
    id bigserial PRIMARY KEY,
    title varchar(100) NOT NULL,
    subtitle varchar(100),
    category_id bigint NOT NULL REFERENCES categories(id),
    author_id bigint NOT NULL REFERENCES authors(id)
);

CREATE TABLE IF NOT EXISTS users(
    id bigserial PRIMARY KEY,
    name varchar(150) NOT NULL,
    email varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    "role" varchar(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS customers(
    id bigserial PRIMARY KEY,
    name varchar(150) NOT NULL,
    document varchar(50),
    user_id bigint NOT NULL REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS loans(
    id bigserial PRIMARY KEY,
    customer_id bigint NOT NULL REFERENCES customers(id),
    lent_date timestamp with time zone NOT NULL DEFAULT now(),
    return_date timestamp with time zone NOT NULL,
    status varchar(10) DEFAULT 'PENDING',
    book_quantity integer NOT NULL
);

CREATE TABLE IF NOT EXISTS loan_books(
    id bigserial PRIMARY KEY,
    loan_id bigint NOT NULL REFERENCES loans(id),
    book_id bigint NOT NULL REFERENCES books(id),
    quantity integer NOT NULL,
    returned_date timestamp with time zone
);
