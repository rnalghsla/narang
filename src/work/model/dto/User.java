package work.model.dto;

import java.io.Serializable;

public class User implements Serializable{
	
	/** ȸ�� ���̵� */
	private String uId;

	/** ȸ�� ��й�ȣ */
	private String uPw;

	/** ȸ�� �̸� */
	private String name;
	
	/** ȸ�� ���� */
	private String gender;
	
	/** ȸ�� ���� */
	private String age;
	
	/** ȸ�� ��ȭ��ȣ */
	private String mobile;
	
	/** ȸ�� ���� */
	private String email;
	
	/** ȸ�� ������Ʈ ī��Ʈ, ���� */
	private int blacklistCount;
	
	/** ȸ�� �����ʻ��� */
	private String uImg;
	
	/** ȸ�� ���  
	 * �Ϲ�ȸ����G��, �����ڡ�A�� 
	 * ���̹� ȸ����N��,Ż��X�� 
	 * ������Ʈ��B��
	*/
	private String grade;
	
	/** �⺻ ������ */
	public User() {}
	
	/** ������  ������*/
	public User(String uId, String uPw, String name, String gender, String age, String mobile,
			String email, int blacklistCount, String uImg, String grade) {
		this.uId = uId;
		this.uPw = uPw;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.mobile = mobile;
		this.email = email;
		this.blacklistCount = blacklistCount;
		this.uImg = uImg;
		this.grade = grade;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [uId=");
		builder.append(uId);
		builder.append(", uPw=");
		builder.append(uPw);
		builder.append(", name=");
		builder.append(name);
		builder.append(", gender=");
		builder.append(gender);
		builder.append(", age=");
		builder.append(age);
		builder.append(", mobile=");
		builder.append(mobile);
		builder.append(", email=");
		builder.append(email);
		builder.append(", blacklistCount=");
		builder.append(blacklistCount);
		builder.append(", uImg=");
		builder.append(uImg);
		builder.append(", grade=");
		builder.append(grade);
		builder.append("]");
		return builder.toString();
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getuPw() {
		return uPw;
	}

	public void setuPw(String uPw) {
		this.uPw = uPw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getBlacklistCount() {
		return blacklistCount;
	}

	public void setBlacklistCount(int blacklistCount) {
		this.blacklistCount = blacklistCount;
	}

	public String getuImg() {
		return uImg;
	}

	public void setuImg(String uImg) {
		this.uImg = uImg;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

}
