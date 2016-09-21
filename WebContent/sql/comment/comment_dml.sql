1. 댓글등록
Insert into comments
values(comments_count.nextval, 1, ‘park’, ‘이 모임 좋아요~’, sysdate);

2. 댓글수정
Update comments set
c_content = ‘비오면 어떻게 되나요?’ where c_id= 3;

3. 내가 쓴 댓글 보기 
Select * From comments Where u_id=‘park’;

4. 댓글삭제
Delete from comments Where c_id=2;