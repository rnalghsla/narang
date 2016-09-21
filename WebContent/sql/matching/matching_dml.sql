--matching dml
--1. 매칭생성 insertMatching
Insert into matching values(match_count.nextval, 1, null, null, null, null, null, to_date(sysdate,'yyyy-mm-dd HH24:mi:ss'), to_date(sysdate+1,'yyyy-mm-dd HH24:mi:ss'));

--2. 매칭등록 updateMatchingSetId - 들어오는 번호에 따라서 다른 쿼리문에 넣어준다.
Update matching set M_user1='park' where m_id=1;
Update matching set M_user2='park' where m_id=1;
Update matching set M_user3='park' where m_id=1;
Update matching set M_user4='park' where m_id=1;
Update matching set M_user5='park' where m_id=1;

--3. 매칭삭제 updateMatchingRemoveId - 들어오는 번호에 따라서 다른 쿼리문에 넣어준다.
Update matching set M_user1=null where m_id=1 and m_user1='moon';
Update matching set M_user2=null where m_id=1 and m_user1='moon';
Update matching set M_user3=null where m_id=1 and m_user1='moon';
Update matching set M_user4=null where m_id=1 and m_user1='moon';
Update matching set M_user5=null where m_id=1 and m_user1='moon';

--4. 중복모임 선택 불가 selectMatchingCompareDate -이값이 0보다 크면 존재한다는 뜻, 0보다 작으면 중복되지않는다는뜻
select b.b_id
from (select m_id, b_start_match
         from matching
         where m_id=6) m , board b
where m.b_start_match-b.b_end_match < 0;
-- 비교해서 음수이면 매칭불가, 양수이면 매칭가능
select distinct * from 
(select b_id, b_end_match from board where b_id=2) b, matching m
where m.b_start_match-b.b_end_match < 0 and 'kim' in (m.m_user1, m.m_user2, m.m_user3, m.m_user4, m.m_user5);
--두쿼리는 같은 쿼리이다

5. 내가 참가한 매칭 보기 selectMyMatching
select * from matching where 'kim' in (m_user1, m_user2, m_user3, m_user4, m_user5);

6. 가득찬 모임은 음영처리 selectFullMatching
select b_id from matching where m_user1 is not null and m_user2 is not null and m_user3 is not null and m_user4 is not null and m_user5 is not null;
--(가득찬모임에서 빠져나갔을 때 음영처리 풀어주는 것도)

7. 시간이 지나서 종료된 글 음영처리 selectOldMatching
select b_id from matching where b_end_match - to_date(sysdate, 'yyyy-MM-dd HH24:Mi:ss') <0;
