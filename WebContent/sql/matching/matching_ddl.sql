DROP TABLE matching;
PURGE RECYCLEBIN;  
DROP SEQUENCE match_count;



CREATE SEQUENCE match_count;

CREATE TABLE matching(
m_id             NUMBER(5) NOT NULL PRIMARY KEY,
b_id              NUMBER(5),
m_user1         VARCHAR2(20),
m_user2         VARCHAR2(20),
m_user3         VARCHAR2(20),
m_user4         VARCHAR2(20),
m_user5         VARCHAR2(20),
b_start_match  DATE,
b_end_match   DATE
);

COMMIT;

