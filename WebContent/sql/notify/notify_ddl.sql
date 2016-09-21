DROP TABLE notify;
PURGE RECYCLEBIN;  
DROP SEQUENCE notify_count;



CREATE SEQUENCE notify_count;

CREATE TABLE notify(
n_id                  NUMBER(5) NOT NULL PRIMARY KEY,
send_u_id           VARCHAR2(20),
rec_u_id             VARCHAR2(20),
table_name         VARCHAR2(20),     
table_id              NUMBER(5)
);

COMMIT;

