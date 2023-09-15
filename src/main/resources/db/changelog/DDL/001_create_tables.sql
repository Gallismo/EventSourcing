--liquibase formatted sql
--changeset myname:create-multiple-tables splitStatements:true endDelimiter:;

CREATE TABLE IF NOT EXISTS `orders_events`
(`creation_date` DATETIME(6) NOT NULL,
`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`order_id` BIGINT NOT NULL,
`data` JSON NOT NULL CHECK (JSON_VALID(`data`)),
`type` VARCHAR(255) NOT NULL);
