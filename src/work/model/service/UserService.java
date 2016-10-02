package work.model.service;

import java.util.HashMap;

import work.model.dao.UserDao;

public class UserService {
	
	private UserDao dao = UserDao.getInstance();
	
	/**
	 * �α���
	 * @param uId ȸ�����̵�
	 * @param uPw ȸ����й�ȣ
	 * @return HashMap
	 */
	public HashMap<String, String> login(String uId, String uPw){
		System.out.println("userService : " + uId + uPw);
		return dao.login(uId, uPw);
	}
	
	/**
	 * ȸ�� �ߺ� ��ȸ
	 * @param uId ȸ�����̵�
	 * @return true / false
	 */
	public boolean dupliUser(String uId) {
		return dao.dupliUser(uId);
	}
	
	/**
	 * ���̹� ȸ������
	 * @param uId ȸ�����̵�
	 * @param name �̸�(�г���)
	 * @param gender ����
	 * @param age ����
	 * @param email �̸���
	 * @param uImg �����ʻ���
	 * @return
	 */
	public int insertUser(String uId, String name, String gender, String age, String email,String uImg)  {
		return dao.insertUser(uId, name, gender, age, email, uImg);
	}
	
	/**
	 * īī�� ȸ�� ȸ������
	 * @param uId ȸ�����̵�
	 * @param name �̸�
	 * @param uImg �����ʻ���
	 * @return
	 */
	public int insertUser(String uId, String name ,String uImg)  {
		return dao.insertUser(uId, name, uImg);
	}
	
	/**
	 * �Ϲ� ȸ������
	 * @param uId ȸ�����̵�(�ʼ�)
	 * @param uPw ��й�ȣ(�ʼ�)
	 * @param name �̸�(�ʼ�)
	 * @param gender ����
	 * @param age ����
	 * @param mobile �ڵ���(�ʼ�)
	 * @param email �̸���
	 * @param uImg �����ʻ���
	 * @return
	 */
	public int insertUser(String uId, String uPw, String name, String gender, String age, String mobile, String email,String uImg)  {
		String pathUimg = "images\\user\\" + uImg;

		return dao.insertUser(uId, uPw, name, gender, age, mobile, email, pathUimg);
	}
	
	/**
	 * �α��� �� ȸ�� �̹��� ������Ʈ
	 * @param uImg ȸ�������ʻ���
	 * @param uId ȸ�����̵�
	 * @return
	 */
	public int updateUserImg(String uImg, String uId) {
		return dao.updateUserImg(uImg, uId);
	}
		
	/**
	 * ��й�ȣ ã���ϱ� �� ȸ���� �����ϴ��� �˻�
	 * ��й�ȣ ã�� 1�ܰ�
	 * @param uId ȸ�����̵�
	 * @param name �̸�
	 * @return
	 */
	public int selectPw(String uId, String name){
		return dao.selectPw(uId, name);
	}
	
	/**
	 * ��й�ȣ ã�� 2�ܰ�
	 * @param uId ȸ�����̵�
	 * @param uPw ��й�ȣ
	 * @return
	 */
	public int selectPw2(String uId, String uPw){
		return dao.selectPw2(uId, uPw);
	}
	
	/**
	 * ���̵� ã��
	 * @param mobile �ڵ���
	 * @param name �̸�
	 * @return
	 */
	public String selectId(String mobile, String name){
		return dao.selectId(mobile, name);
	}
		
}
