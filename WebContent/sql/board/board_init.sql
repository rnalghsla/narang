delete from board;


ALTER SESSION SET NLS_DATE_FORMAT='YYYY-MM-DD HH24:MI:SS' ;


Insert into board 
values(board_count.nextval,1,'������ �ʴ��մϴ�', 'kim','������ �Դ� ���ε� ���־ ȸ���Ե� �ʴ��մϴ�','�������б� ���ͷ�',3,
to_date(sysdate,'yyyy-mm-dd HH24:MI:SS'),
to_date(sysdate,'yyyy-mm-dd HH24:mi:ss'),
null,null,null,
to_char(sysdate, 'yyyy-mm-dd'),
0);

Insert into board
values(board_count.nextval,1,'���� ���ְ��� �ܰ����Դϴ�', 'kim','����ϱ� ���� ��3���� �鸣�� ���� �Ұ��մϴ�.','�������б� ���න����',3,
to_date('2016-01-02 00:00:00','yyyy-mm-dd HH24:mi:ss'),
to_date(sysdate,'yyyy-mm-dd HH24:mi:ss'),
null,null,null,
to_char(sysdate, 'yyyy-mm-dd'),
0);

Insert into board
values(board_count.nextval,1,'�츮 �б��� ���� �԰Ÿ�!', 'kim','�αⰡ ���Ƽ� �׻� �ټ��� �Դ� ���Դϴ�!!','�������б� ��ū���ö�',2,
to_date('2016-01-02 00:00:00','yyyy-mm-dd HH24:mi:ss'),
to_date(sysdate,'yyyy-mm-dd HH24:mi:ss'),
null,null,null,
to_char(sysdate, 'yyyy-mm-dd'),
0);

Insert into board
values(board_count.nextval,1,'���̹��Ŷ�', 'moon','�װ��� ���� ��� ���� �� ���� ��...','�������б� ���̹���',5,
to_date('2016-01-02 00:00:00','yyyy-mm-dd HH24:mi:ss'),
to_date(sysdate,'yyyy-mm-dd HH24:mi:ss'),
null,null,null,
to_char(sysdate, 'yyyy-mm-dd'),
0);

Insert into board
values(board_count.nextval,1,'���� ������ �ѳ� ���� ��', 'moon','�װ��� �������б� �Ĺ� �ϴ�����... ���� ���մϴ�','�������б� �ϴ�����',5,
to_date('2016-01-02 00:00:00','yyyy-mm-dd HH24:mi:ss'),
to_date(sysdate,'yyyy-mm-dd HH24:mi:ss'),
null,null,null,
to_char(sysdate, 'yyyy-mm-dd'),
0);

Insert into board
values(board_count.nextval,1,'�������� ��������?', 'moon','���� ����ϰ� �̻ڰ� ���Ϳ�! ����1�κп� 8000��!! �ʹ����־�� ','�������б� û�ͻ��',5,
to_date('2016-01-02 00:00:00','yyyy-mm-dd HH24:mi:ss'),
to_date(sysdate,'yyyy-mm-dd HH24:mi:ss'),
null,null,null,
to_char(sysdate, 'yyyy-mm-dd'),
0);

Insert into board
values(board_count.nextval,1,'���� ���� ���İ�Ƽ�� �׷��� ���ִ�� ���Ǻ� �����մϴ�', 'moon','ģ���� ��õ����µ� �ٻڴ뼭 ���� ���� �� �����ؿ�!!','�������б� ���ν� ȭ������',4,
to_date('2016-01-02 00:00:00','yyyy-mm-dd HH24:mi:ss'),
to_date(sysdate,'yyyy-mm-dd HH24:mi:ss'),
null,null,null,
to_char(sysdate, 'yyyy-mm-dd'),
0);

Insert into board
values(board_count.nextval,1,'�������� �ؽ���������?', 'moon','�䵵 �Ĵµ� �Դٰ� �ؽ�����������?!? �ؽ��������� �����ϴ»�� ���� ��','�������б� ���̽���',3,
to_date('2016-01-02 00:00:00','yyyy-mm-dd HH24:mi:ss'),
to_date(sysdate,'yyyy-mm-dd HH24:mi:ss'),
null,null,null,
to_char(sysdate, 'yyyy-mm-dd'),
0);

Insert into board
values(board_count.nextval,1,'¥��� ��Ǻ�~~', 'moon','���� �ҳ��� ���� ���𵨸��ߴ��. ���� �ٲ���� �Ծ�� ���� ���Ǻ�???','�������б� �߱���',3,
to_date('2016-01-02 00:00:00','yyyy-mm-dd HH24:mi:ss'),
to_date(sysdate,'yyyy-mm-dd HH24:mi:ss'),
null,null,null,
to_char(sysdate, 'yyyy-mm-dd'),
0);

Insert into board
values(board_count.nextval,1,'�������� ȫ�� ¥��', 'moon','�ݹ� �޴��� ���� ȫ��!! �Բ��ؿ�!!','�������б� ȫ��',5,
to_date('2016-01-02 00:00:00','yyyy-mm-dd HH24:mi:ss'),
to_date(sysdate,'yyyy-mm-dd HH24:mi:ss'),
null,null,null,
to_char(sysdate, 'yyyy-mm-dd'),
0);
