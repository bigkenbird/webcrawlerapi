USE `webcrawler`;

CREATE TABLE `brand`
(
    `ID`         INT NOT NULL AUTO_INCREMENT,
    `name`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `title`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `url`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `category`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `USER_COUNT` INT                                                       DEFAULT NULL,
    PRIMARY KEY (`ID`),
    UNIQUE KEY `NAME_UNIQUE` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


CREATE TABLE `brand_log`
(
    `ID`         INT NOT NULL AUTO_INCREMENT,
    `NAME`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `TITLE`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `URL`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `CATEGORY`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `USER_COUNT` INT                                                       DEFAULT NULL,
    PRIMARY KEY (`ID`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


CREATE TABLE `post`
(
    `ID`             INT NOT NULL AUTO_INCREMENT,
    `TITLE`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `COMMENT_COUNT`  INT                                                       DEFAULT NULL,
    `ARTICLE_AUTHOR` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `URL`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `BRAND`          varchar(200)                                                  DEFAULT NULL,
    PRIMARY KEY (`ID`),
    UNIQUE KEY `BRAND_TITLE_UNIQUE` (`BRAND`,`TITLE`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `content`
(
    `ID`             int NOT NULL AUTO_INCREMENT,
    `POST_ID`        int NOT NULL,
    `TITLE`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `CONTENT`        TEXT,
    `ARTICLE_AUTHOR` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    PRIMARY KEY (`ID`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
