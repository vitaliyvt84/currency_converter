DROP DATABASE currency_converter;
CREATE DATABASE currency_converter
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Ukraine.1251'
    LC_CTYPE = 'Russian_Ukraine.1251';

DROP TABLE convert_operation;
CREATE TABLE convert_operation
(
    id bigint NOT NULL,
    currency_rate double precision,
    date date,
    first_currency character varying(255) COLLATE pg_catalog."default",
    result_sum double precision,
    second_currency character varying(255) COLLATE pg_catalog."default",
    source_sum double precision,
    CONSTRAINT convert_operation_pkey PRIMARY KEY (id)
);
ALTER TABLE convert_operation OWNER to postgres;
	

DROP TABLE currency;
CREATE TABLE currency
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    char_code character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT currency_pkey PRIMARY KEY (id)
);
ALTER TABLE currency OWNER to postgres;


DROP TABLE currency_rate;
CREATE TABLE currency_rate
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    char_code character varying(255) COLLATE pg_catalog."default",
    date date,
    rate double precision,
    CONSTRAINT currency_rate_pkey PRIMARY KEY (id)
);
ALTER TABLE currency_rate OWNER to postgres;