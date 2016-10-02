delete from board;


ALTER SESSION SET NLS_DATE_FORMAT='YYYY-MM-DD HH24:MI:SS' ;


Insert into board 
values(board_count.nextval,1,'맛집에 초대합니다', 'kim','저번에 왔던 곳인데 맛있어서 회원님들 초대합니다','삼육대학교 스와레',3,
to_date(sysdate,'yyyy-mm-dd HH24:MI:SS'),
to_date(sysdate,'yyyy-mm-dd HH24:mi:ss'),
null,null,null,
to_char(sysdate, 'yyyy-mm-dd'),
0);

Insert into board
values(board_count.nextval,1,'제가 자주가는 단골집입니다', 'kim','퇴근하기 전에 주3일은 들르는 곳을 소개합니다.','삼육대학교 마녀떡볶이',3,
to_date('2016-01-02 00:00:00','yyyy-mm-dd HH24:mi:ss'),
to_date(sysdate,'yyyy-mm-dd HH24:mi:ss'),
null,null,null,
to_char(sysdate, 'yyyy-mm-dd'),
0);

Insert into board
values(board_count.nextval,1,'우리 학교의 숨은 먹거리!', 'kim','인기가 많아서 항상 줄서서 먹는 곳입니다!!','삼육대학교 더큰도시락',2,
to_date('2016-01-02 00:00:00','yyyy-mm-dd HH24:mi:ss'),
to_date(sysdate,'yyyy-mm-dd HH24:mi:ss'),
null,null,null,
to_char(sysdate, 'yyyy-mm-dd'),
0);

Insert into board
values(board_count.nextval,1,'싸이버거란', 'moon','그것은 나의 삶에서 빠질 수 없는 것...','삼육대학교 싸이버거',5,
to_date('2016-01-02 00:00:00','yyyy-mm-dd HH24:mi:ss'),
to_date(sysdate,'yyyy-mm-dd HH24:mi:ss'),
null,null,null,
to_char(sysdate, 'yyyy-mm-dd'),
0);

Insert into board
values(board_count.nextval,1,'음식 종류가 넘나 많은 곳', 'moon','그것은 삼육대학교 후문 하늘지기... 양이 어마어마합니다','삼육대학교 하늘지기',5,
to_date('2016-01-02 00:00:00','yyyy-mm-dd HH24:mi:ss'),
to_date(sysdate,'yyyy-mm-dd HH24:mi:ss'),
null,null,null,
to_char(sysdate, 'yyyy-mm-dd'),
0);

Insert into board
values(board_count.nextval,1,'점심으로 보쌈한입?', 'moon','정말 깔끔하고 이쁘게 나와요! 정식1인분에 8000원!! 너무맛있어요 ','삼육대학교 청와삼대',5,
to_date('2016-01-02 00:00:00','yyyy-mm-dd HH24:mi:ss'),
to_date(sysdate,'yyyy-mm-dd HH24:mi:ss'),
null,null,null,
to_char(sysdate, 'yyyy-mm-dd'),
0);

Insert into board
values(board_count.nextval,1,'여기 로제 스파게티가 그렇게 맛있대요 가실분 모집합니다', 'moon','친구가 추천해줬는데 바쁘대서 같이 가실 분 모집해요!!','삼육대학교 도로시 화덕피자',4,
to_date('2016-01-02 00:00:00','yyyy-mm-dd HH24:mi:ss'),
to_date(sysdate,'yyyy-mm-dd HH24:mi:ss'),
null,null,null,
to_char(sysdate, 'yyyy-mm-dd'),
0);

Insert into board
values(board_count.nextval,1,'점심으로 해쉬포테이토?', 'moon','밥도 파는데 게다가 해쉬포테이토라니?!? 해쉬포테이토 좋아하는사람 모집 중','삼육대학교 라이스볼',3,
to_date('2016-01-02 00:00:00','yyyy-mm-dd HH24:mi:ss'),
to_date(sysdate,'yyyy-mm-dd HH24:mi:ss'),
null,null,null,
to_char(sysdate, 'yyyy-mm-dd'),
0);

Insert into board
values(board_count.nextval,1,'짜장면 드실분~~', 'moon','여기 불나고 나서 리모델링했대요. 맛도 바뀌었나 먹어보러 같이 가실분???','삼육대학교 중국관',3,
to_date('2016-01-02 00:00:00','yyyy-mm-dd HH24:mi:ss'),
to_date(sysdate,'yyyy-mm-dd HH24:mi:ss'),
null,null,null,
to_char(sysdate, 'yyyy-mm-dd'),
0);

Insert into board
values(board_count.nextval,1,'하태핫태 홍원 짜장', 'moon','반반 메뉴가 많은 홍원!! 함께해요!!','삼육대학교 홍원',5,
to_date('2016-01-02 00:00:00','yyyy-mm-dd HH24:mi:ss'),
to_date(sysdate,'yyyy-mm-dd HH24:mi:ss'),
null,null,null,
to_char(sysdate, 'yyyy-mm-dd'),
0);
