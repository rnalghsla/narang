-- 1. 일반후기 쓰기
Insert into review values(review_count.nextval, 1, 'park', '후기작성', '자연별곡 맛있어요!!', to_date(sysdate,'yyyy-mm-dd'), 'images.png',0);

-- 2. 공지사항 글쓰기
Insert into review 
values(seq_review_rid, 0, 'park', '공지합니다', '공지사항 내용입니다.', to_date(sysdate,'yyyy-mm-dd'), null, 0);

-- 3. 후기 글수정
Update review set r_title='후기입니다.', r_content='밀정 재밌어요~', r_img='images.png' where r_id=5 and u_id='jung'

--4. 후기 글삭제
-- 일반회원 : 
Delete from review where r_id=2 and u_id='lee'
--관리자 : 
Delete from review where r_id=1

-- 5. 후기 상세보기
Select * from review where r_id=2

-- 6. 후기 전체보기
-- 일반회원 : 
Select * from review where r_type=1
-- 관리자 : 
select * from review where r_type<>0 and r_type<>6

-- 8. 공지사항 전체보기
Select * from review where r_type=0

-- 9. 내가 쓴 후기 보기
Select * from review where u_id='park'

-- 10. 후기제목으로 검색
Select * from review where r_title like '%후기%'

-- 11. 조회수 증가
Update review set r_hit=r_hit+1 where r_id=3
