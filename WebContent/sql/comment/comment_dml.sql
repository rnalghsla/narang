-- 1. ��۵��
Insert into comments
values(comments_count.nextval, 1, ��park��, ���� ���� ���ƿ�~��, sysdate)

-- 2. ��ۼ���
Update comments set
c_content = ������� ��� �ǳ���?�� where c_id= 3

-- 3. �Խñ� Ŭ���� ��� ����Ʈ ����
Select * From comments Where b_id=3

-- 4. ��ۻ���
-- ������ :
Delete from comments Where c_id=2 and u_id='kim'
-- �Ϲ�ȸ�� : 
Delete from comments Where c_id=2

-- 5. ���� �� ��� ���� 
Select * From comments Where u_id=��park��