CREATE TABLE `customer`
(
    `id`                bigint       NOT NULL AUTO_INCREMENT,
    `uuid`              varchar(36)  NOT NULL,

    `name`              varchar(255) NOT NULL,
    `document`          varchar(255) NOT NULL,

    `input_date`        datetime(6) NOT NULL,
    `last_updated_date` datetime(6) NOT NULL,
    `version`           int(11)     NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY (`uuid`)
);
