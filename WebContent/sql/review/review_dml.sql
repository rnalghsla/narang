1. 일반후기 쓰기
Insert into review values(seq_review_rid.nextval, ?,?,?,?,?,?,0);

2. 공지사항 글쓰기
Insert into review values(seq_review_rid, ?,?,?,?,?,Null,0);

3. 후기 글수정
Update review set r_title=?, r_content=?, r_img=? where r_id=? and u_id=?

4. 후기 글삭제
일반회원 : Delete from review where r_id=? and u_id=?
관리자 : Delete from review where r_id=?

5. 후기 상세보기
Select * from review where r_id=?

6. 조회수 증가
Update review set r_hit=r_hit+1 where r_id=?

7. 후기 전체보기
Select * from review where r_notice=1

8. 공지사항 전체보기
Select * from review where r_notice=0

9. 내가 쓴 후기 보기
Select * from review where u_id=?

10. 후기제목으로 검색
Select * from review where r_title=%?%
