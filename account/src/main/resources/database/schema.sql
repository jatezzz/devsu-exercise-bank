CREATE SEQUENCE IF NOT EXISTS "PUBLIC"."ACCOUNT_SEQ" START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE IF NOT EXISTS "PUBLIC"."MOVEMENT_SEQ" START WITH 1 INCREMENT BY 50;

DROP TABLE IF EXISTS "PUBLIC"."MOVEMENT";
DROP TABLE IF EXISTS "PUBLIC"."ACCOUNT";

CREATE
CACHED TABLE "PUBLIC"."ACCOUNT"
(
    "ID"        BIGINT                 NOT NULL,
    "BALANCE"   NUMERIC(38, 2),
    "CLIENT_ID" BIGINT                 NOT NULL,
    "NUMBER"    CHARACTER VARYING(255) NOT NULL,
    "STATUS"    BOOLEAN,
    "TYPE"      TINYINT
);
ALTER TABLE "PUBLIC"."ACCOUNT"
    ADD CONSTRAINT "PUBLIC"."ACCOUNT_ID_CONSTRAINT" PRIMARY KEY ("ID");
CREATE
CACHED TABLE "PUBLIC"."MOVEMENT"
(
    "ID"              BIGINT NOT NULL,
    "AMOUNT"          NUMERIC(38, 2),
    "CURRENT_BALANCE" NUMERIC(38, 2),
    "DATE"            TIMESTAMP(6),
    "INITIAL_BALANCE" NUMERIC(38, 2),
    "STATUS"          BOOLEAN,
    "TYPE"            TINYINT,
    "ACCOUNT_ID"      BIGINT
);
ALTER TABLE "PUBLIC"."MOVEMENT"
    ADD CONSTRAINT "PUBLIC"."MOVEMENT_ID_CONSTRAINT" PRIMARY KEY ("ID");
ALTER TABLE "PUBLIC"."MOVEMENT"
    ADD CONSTRAINT "PUBLIC"."MOVEMENT_TYPE_CONSTRAINT" CHECK ("TYPE" BETWEEN 0 AND 1) NOCHECK;
ALTER TABLE "PUBLIC"."ACCOUNT"
    ADD CONSTRAINT "PUBLIC"."ACCOUNT_TYPE_CONSTRAINT" CHECK ("TYPE" BETWEEN 0 AND 1) NOCHECK;
ALTER TABLE "PUBLIC"."ACCOUNT"
    ADD CONSTRAINT "PUBLIC"."ACCOUNT_NUMBER_CONSTRAINT" UNIQUE ("NUMBER");
ALTER TABLE "PUBLIC"."MOVEMENT"
    ADD CONSTRAINT "PUBLIC"."MOVEMENT_ACCOUNT_ID_CONTRAINT" FOREIGN KEY ("ACCOUNT_ID") REFERENCES "PUBLIC"."ACCOUNT" ("ID") NOCHECK;

