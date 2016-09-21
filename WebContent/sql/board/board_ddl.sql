DROP TABLE board;
PURGE RECYCLEBIN;  
DROP SEQUENCE board_count;



CREATE SEQUENCE board_count;

CREATE TABLE board(
B_ID                   NUMBER(5)  NOT NULL PRIMARY KEY,
B_TAB                 NUMBER(5),
B_TITLE               VARCHAR2(100),
U_ID                  VARCHAR2(20),
B_CONTENT        VARCHAR2(800),
B_PLACE             VARCHAR2(300),
B_LIMIT              NUMBER(5),
B_START_MATCH  DATE,
B_END_MATCH    DATE, 
B_IMG1              VARCHAR2(20),     
B_IMG2              VARCHAR2(20),
B_IMG3              VARCHAR2(20),
B_DATE              DATE,
B_HIT                NUMBER(20) 
);

COMMIT;

