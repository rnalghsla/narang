DROP TABLE review;
PURGE RECYCLEBIN;  
DROP SEQUENCE review_count;



CREATE SEQUENCE review_count;

CREATE TABLE review(
r_id          NUMBER(5) NOT NULL PRIMARY KEY,
r_type       VARCHAR2(20),
u_id          VARCHAR2(20),
r_title        VARCHAR2(100),
r_content   VARCHAR2(800),
r_date       DATE,
r_img        VARCHAR2(20),
r_hit          NUMBER(5)
);

COMMIT;

