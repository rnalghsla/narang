1. �Ϲ��ı� ����
Insert into review values(seq_review_rid.nextval, ?,?,?,?,?,?,0);

2. �������� �۾���
Insert into review values(seq_review_rid, ?,?,?,?,?,Null,0);

3. �ı� �ۼ���
Update review set r_title=?, r_content=?, r_img=? where r_id=? and u_id=?

4. �ı� �ۻ���
�Ϲ�ȸ�� : Delete from review where r_id=? and u_id=?
������ : Delete from review where r_id=?

5. �ı� �󼼺���
Select * from review where r_id=?

6. ��ȸ�� ����
Update review set r_hit=r_hit+1 where r_id=?

7. �ı� ��ü����
Select * from review where r_notice=1

8. �������� ��ü����
Select * from review where r_notice=0

9. ���� �� �ı� ����
Select * from review where u_id=?

10. �ı��������� �˻�
Select * from review where r_title=%?%
