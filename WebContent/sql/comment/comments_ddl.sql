DROP TABLE comments;
PURGE RECYCLEBIN;  
DROP SEQUENCE comments_count;



CREATE SEQUENCE comments_count;

CREATE TABLE comments(
c_id         NUMBER(5) NOT NULL PRIMARY KEY,
b_id         NUMBER(5),
u_id         VARCHAR2(20),
c_content  VARCHAR2(800),
c_date      DATE
);

COMMIT;

