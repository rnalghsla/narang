delete from review;

INSERT INTO REVIEW 
VALUES(REVIEW_COUNT.NEXTVAL, 1, 'janek10', '스와레 맛있어요', '삼육대 후문에 마녀떡볶이 뒤에 위치해 있는데 맛있어요.', to_char(sysdate, 'yyyy/mm/dd'),'images/suwarae.png',0);

INSERT INTO REVIEW 
VALUES(REVIEW_COUNT.NEXTVAL, 1, 'kim', '모아밀터 더러워요', '삼육대 후문에 위치해 있는데 맛있지만, 약간 더럽다는 소문이 있어요.', to_char(sysdate, 'yyyy/mm/dd'),'images/moa.png',0);

INSERT INTO REVIEW 
VALUES(REVIEW_COUNT.NEXTVAL, 0, 'admin', '공지사항입니다.', '개인정보 보호에 최선을 다하겠습니다.', to_char(sysdate, 'yyyy/mm/dd'),null,0);

INSERT INTO REVIEW 
VALUES(REVIEW_COUNT.NEXTVAL, 0, 'admin', '다른 사람을 모욕하는 글을 쓰지 맙시다', '혹시나 그런 글 들이 보이면 그 글에 신고버튼을 눌러주세요.', to_char(sysdate, 'yyyy/mm/dd'),null,0);

INSERT INTO REVIEW 
VALUES(REVIEW_COUNT.NEXTVAL, 1, 'moon', '마녀떡볶이 맛있어요', '삼육대 후문에 마녀떡볶이에 주먹밥 떡볶이 맛있습니다.', to_char(sysdate, 'yyyy/mm/dd'),'images/judduck.png',0);

INSERT INTO REVIEW 
VALUES(REVIEW_COUNT.NEXTVAL, 1, 'kim', '도로시 화덕피자 존맛', '거기에 로제 파스타 진짜 맛있어요.', to_char(sysdate, 'yyyy/mm/dd'),'images/Dorothy.png',0);

INSERT INTO REVIEW 
VALUES(REVIEW_COUNT.NEXTVAL, 3, 'park', '이 영화 추천이요.', '해리포터와 비밀의 방 진짜 재미있어요. 꼭 보세요. 두번 보세요.', to_char(sysdate, 'yyyy/mm/dd'),'images/harry.png',0);

INSERT INTO REVIEW 
VALUES(REVIEW_COUNT.NEXTVAL, 0, 'admin', '다른 사람을 모욕하는 글을 쓰지 맙시다2', '5번의 기회를 드립니다.5번의 경고가 모이면 블랙리스트에 추가되니까 조심하세요.', to_char(sysdate, 'yyyy/mm/dd'),null,0);

INSERT INTO REVIEW 
VALUES(REVIEW_COUNT.NEXTVAL, 1, 'jung', '스와레 듣던대로 맛있어요', '연어 덮밥 맛있어요.', to_char(sysdate, 'yyyy/mm/dd'),'images/suwarae2.png',0);

INSERT INTO REVIEW 
VALUES(REVIEW_COUNT.NEXTVAL, 4, 'lee', '오늘 롤스킨 새로 나왔는데 짱이쁨', '징크스 신스킨 완전 이뻐요. 하하', to_char(sysdate, 'yyyy/mm/dd'),'images/skin.png',0);

INSERT INTO REVIEW 
VALUES(REVIEW_COUNT.NEXTVAL, 5, 'kim', '일산 호수공원 운동하기 정말 좋아요.', '자전거, 농구, 조깅, 걷기 등 다향하게 많은 운동이 가능해요.', to_char(sysdate, 'yyyy/mm/dd'),'images/hosu.png',0);

INSERT INTO REVIEW 
VALUES(REVIEW_COUNT.NEXTVAL, 2, 'moon', '뮤지컬 캣츠 봤어요', '너무 감동적이고 재밌어서 눈물이 다 났어요. 히트다 히트. 꼭보세요. 두번 보세요', to_char(sysdate, 'yyyy/mm/dd'),'images/cats.png',0);

commit;




