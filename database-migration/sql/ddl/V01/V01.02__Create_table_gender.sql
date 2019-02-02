CREATE TABLE SN_GENDER
(
  ID         BIGINT DEFAULT NEXTVAL('SQ_ID_GENERATOR') NOT NULL,
  VERSION    INT    DEFAULT 0,
  NATURAL_ID VARCHAR(255)                              NOT NULL,
  NAME       VARCHAR(255)                              NOT NULL,
  CONSTRAINT PK_GENDER PRIMARY KEY (ID)
);