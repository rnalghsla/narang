--board ���̺� dml

--1. �۵�� insertBoard
Insert into board values(board_count.nextval,1,'������ �ʴ��մϴ�', 'kim','������ �Դ� ���ε� ���־ ȸ���Ե� �ʴ��մϴ�','�������б� ���ͷ�',3, to_date(sysdate,'yyyy-mm-dd HH24:MI:SS'), to_date(sysdate,'yyyy-mm-dd HH24:mi:ss'), null,null,null, to_char(sysdate, 'yyyy-mm-dd'),0);

--2. �� �� ���� updateMyBoard
update board set b_title='����(����)', b_content='���־��', b_place='������ ���ͷ�',b_limit=2, b_start_match=to_date('2016-09-11 12:00:00', 'yyyy-mm-dd HH24:MI:SS'), b_end_match=to_date('2016-09-11 15:00:00', 'yyyy-mm-dd HH24:MI:SS'), b_img1='aa.png', b_img2='bb.png', b_img3='cc.png' where b_id=1 and u_id='kim';

--3. �� �� ���� deleteMyBoard
Delete from board where b_id=1 and u_id='kim';

--4. �Խñ� �󼼺��� selectOneBoard
Select * from board where B_ID=1;

--5. �Խñ� ��ü(���)���� selectAllBoard
Select * from board where b_tab=1;

--6. �Խñ� �˻� selectBoard - ����, �۳���, ��� �����߿� ��ġ�ϴ� ���� �ִ��� �����������
Select * from board where (b_title like '%ȫ��%' and b_tab=1) or (b_content like '%ȫ��%' and b_tab=1) or (b_place like '%ȫ��%' and b_tab=1);

--7. ��ȸ�� topN ��ȸ selectBoardTopNHit - 3�� n���� �ٲٰ� ������ �޴´�
select * from (select * from board order by b_hit desc) where rownum <=3;

--8. �����ڰ� �Խñ� ���� deleteBoard
Delete from board where b_id=1;

--9. ��õ���� �����ֱ� selectBoardRecommend
select * from (select * from board order by b_hit desc) where b_tab=1;

--10. ���� �� �Խñ� ���� - selectMyBoard
Select * from board where u_id='moon';




