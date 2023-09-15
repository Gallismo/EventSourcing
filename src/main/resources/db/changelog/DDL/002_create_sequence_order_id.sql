--liquibase formatted sql
--changeset myname:create-multiple-tables splitStatements:true endDelimiter:;

CREATE SEQUENCE `order_id_sequence`
START WITH 1
INCREMENT BY 1;
