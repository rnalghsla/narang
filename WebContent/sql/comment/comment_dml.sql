-- 1. 댓글등록
Insert into comments
values(comments_count.nextval, 1, ‘park’, ‘이 모임 좋아요~’, sysdate)

-- 2. 댓글수정
Update comments set
c_content = ‘비오면 어떻게 되나요?’ where c_id= 3

-- 3. 게시글 클릭시 댓글 리스트 보기
Select * From comments Where b_id=3

-- 4. 댓글삭제
-- 관리자 :
Delete from comments Where c_id=2 and u_id='kim'
-- 일반회원 : 
Delete from comments Where c_id=2

-- 5. 내가 쓴 댓글 보기 
Select * From comments Where u_id=‘park’