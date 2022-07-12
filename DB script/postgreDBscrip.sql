CREATE ROLE demo_user WITH LOGIN NOSUPERUSER INHERIT CREATEDB NOCREATEROLE REPLICATION CONNECTION LIMIT -1 PASSWORD 'demo_pass';

DROP DATABASE currency_converter;
CREATE DATABASE currency_converter WITH OWNER = demo_user ENCODING = 'UTF8' CONNECTION LIMIT = -1;

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
	
CREATE TABLE currency
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    char_code character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT currency_pkey PRIMARY KEY (id)
);

CREATE TABLE currency_rate
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    char_code character varying(255) COLLATE pg_catalog."default",
    date date,
    rate double precision,
    CONSTRAINT currency_rate_pkey PRIMARY KEY (id)
);