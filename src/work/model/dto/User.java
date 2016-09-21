package work.model.dto;

import java.io.Serializable;

public class User implements Serializable{
	
	/** 회원 아이디 */
	private String uId;

	/** 회원 비밀번호 */
	private String uPw;

	/** 회원 이름 */
	private String name;
	
	/** 회원 성별 */
	private String gender;
	
	/** 회원 나이 */
	private String age;
	
	/** 회원 전화번호 */
	private String mobile;
	
	/** 회원 메일 */
	private String email;
	
	/** 회원 블랙리스트 카운트, 여부 */
	private int blacklistCount;
	
	/** 회원 프로필사진 */
	private String uImg;
	
	/** 회원 등급  
	 * 일반회원’G’, 관리자‘A’ 
	 * 네이버 회원’N’,탈퇴’X’ 
	 * 블랙리스트’B’
	*/
	private String grade;
	
	/** 기본 생성자 */
	public User() {}
	
	/** 생성자  재정의*/
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
