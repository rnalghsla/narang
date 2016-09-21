--board 테이블 dml

--1. 글등록 insertBoard
Insert into board values(board_count.nextval,1,'맛집에 초대합니다', 'kim','저번에 왔던 곳인데 맛있어서 회원님들 초대합니다','삼육대학교 스와레',3, to_date(sysdate,'yyyy-mm-dd HH24:MI:SS'), to_date(sysdate,'yyyy-mm-dd HH24:mi:ss'), null,null,null, to_char(sysdate, 'yyyy-mm-dd'),0);

--2. 내 글 수정 updateMyBoard
update board set b_title='맛집(수정)', b_content='맛있어요', b_place='삼육대 스와레',b_limit=2, b_start_match=to_date('2016-09-11 12:00:00', 'yyyy-mm-dd HH24:MI:SS'), b_end_match=to_date('2016-09-11 15:00:00', 'yyyy-mm-dd HH24:MI:SS'), b_img1='aa.png', b_img2='bb.png', b_img3='cc.png' where b_id=1 and u_id='kim';

--3. 내 글 삭제 deleteMyBoard
Delete from board where b_id=1 and u_id='kim';

--4. 게시글 상세보기 selectOneBoard
Select * from board where B_ID=1;

--5. 게시글 전체(목록)보기 selectAllBoard
Select * from board where b_tab=1;

--6. 게시글 검색 selectBoard - 제목, 글내용, 장소 세개중에 일치하는 것이 있는지 다중쿼리사용
Select * from board where (b_title like '%홍원%' and b_tab=1) or (b_content like '%홍원%' and b_tab=1) or (b_place like '%홍원%' and b_tab=1);

--7. 조회수 topN 조회 selectBoardTopNHit - 3을 n으로 바꾸고 변수로 받는다
select * from (select * from board order by b_hit desc) where rownum <=3;

--8. 관리자가 게시글 삭제 deleteBoard
Delete from board where b_id=1;

--9. 추천모임 보여주기 selectBoardRecommend
select * from (select * from board order by b_hit desc) where b_tab=1;

--10. 내가 쓴 게시글 보기 - selectMyBoard
Select * from board where u_id='moon';




