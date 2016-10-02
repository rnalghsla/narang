DROP TABLE users;
PURGE RECYCLEBIN;  
DROP SEQUENCE users_count;



CREATE SEQUENCE users_count;

CREATE TABLE users(
u_id                  VARCHAR2(20) NOT NULL PRIMARY KEY,
u_pw                VARCHAR2(20),
name                VARCHAR2(20),
gender              VARCHAR2(5),
age                  VARCHAR2(20),
mobile              VARCHAR2(20),
email                VARCHAR2(20),
blacklist_count    NUMBER(5),
u_img               VARCHAR2(200),
grade               VARCHAR2(20)
);

COMMIT;

