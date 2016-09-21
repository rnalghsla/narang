DROP TABLE basket;
PURGE RECYCLEBIN;  
DROP SEQUENCE basket_count;



CREATE SEQUENCE basket_count;

CREATE TABLE basket(
bk_id                  NUMBER(5) NOT NULL PRIMARY KEY,
u_id                    VARCHAR2(20),
b_id                    NUMBER(5),
bk_date               DATE
);

COMMIT;

