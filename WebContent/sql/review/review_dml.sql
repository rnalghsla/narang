-- 1. �Ϲ��ı� ����
Insert into review values(review_count.nextval, 1, 'park', '�ı��ۼ�', '�ڿ����� ���־��!!', to_date(sysdate,'yyyy-mm-dd'), 'images.png',0);

-- 2. �������� �۾���
Insert into review 
values(seq_review_rid, 0, 'park', '�����մϴ�', '�������� �����Դϴ�.', to_date(sysdate,'yyyy-mm-dd'), null, 0);

-- 3. �ı� �ۼ���
Update review set r_title='�ı��Դϴ�.', r_content='���� ��վ��~', r_img='images.png' where r_id=5 and u_id='jung'

--4. �ı� �ۻ���
-- �Ϲ�ȸ�� : 
Delete from review where r_id=2 and u_id='lee'
--������ : 
Delete from review where r_id=1

-- 5. �ı� �󼼺���
Select * from review where r_id=2

-- 6. �ı� ��ü����
-- �Ϲ�ȸ�� : 
Select * from review where r_type=1
-- ������ : 
select * from review where r_type<>0 and r_type<>6

-- 8. �������� ��ü����
Select * from review where r_type=0

-- 9. ���� �� �ı� ����
Select * from review where u_id='park'

-- 10. �ı��������� �˻�
Select * from review where r_title like '%�ı�%'

-- 11. ��ȸ�� ����
Update review set r_hit=r_hit+1 where r_id=3
