--matching dml
--1. ��Ī���� insertMatching
Insert into matching values(match_count.nextval, 1, null, null, null, null, null, to_date(sysdate,'yyyy-mm-dd HH24:mi:ss'), to_date(sysdate+1,'yyyy-mm-dd HH24:mi:ss'));

--2. ��Ī��� updateMatchingSetId - ������ ��ȣ�� ���� �ٸ� �������� �־��ش�.
Update matching set M_user1='park' where m_id=1;
Update matching set M_user2='park' where m_id=1;
Update matching set M_user3='park' where m_id=1;
Update matching set M_user4='park' where m_id=1;
Update matching set M_user5='park' where m_id=1;

--3. ��Ī���� updateMatchingRemoveId - ������ ��ȣ�� ���� �ٸ� �������� �־��ش�.
Update matching set M_user1=null where m_id=1 and m_user1='moon';
Update matching set M_user2=null where m_id=1 and m_user1='moon';
Update matching set M_user3=null where m_id=1 and m_user1='moon';
Update matching set M_user4=null where m_id=1 and m_user1='moon';
Update matching set M_user5=null where m_id=1 and m_user1='moon';

--4. �ߺ����� ���� �Ұ� selectMatchingCompareDate -�̰��� 0���� ũ�� �����Ѵٴ� ��, 0���� ������ �ߺ������ʴ´ٴ¶�
select b.b_id
from (select m_id, b_start_match
         from matching
         where m_id=6) m , board b
where m.b_start_match-b.b_end_match < 0;
-- ���ؼ� �����̸� ��Ī�Ұ�, ����̸� ��Ī����
select distinct * from 
(select b_id, b_end_match from board where b_id=2) b, matching m
where m.b_start_match-b.b_end_match < 0 and 'kim' in (m.m_user1, m.m_user2, m.m_user3, m.m_user4, m.m_user5);
--�������� ���� �����̴�

5. ���� ������ ��Ī ���� selectMyMatching
select * from matching where 'kim' in (m_user1, m_user2, m_user3, m_user4, m_user5);

6. ������ ������ ����ó�� selectFullMatching
select b_id from matching where m_user1 is not null and m_user2 is not null and m_user3 is not null and m_user4 is not null and m_user5 is not null;
--(���������ӿ��� ���������� �� ����ó�� Ǯ���ִ� �͵�)

7. �ð��� ������ ����� �� ����ó�� selectOldMatching
select b_id from matching where b_end_match - to_date(sysdate, 'yyyy-MM-dd HH24:Mi:ss') <0;
