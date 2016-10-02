package work.model.service;

import java.util.HashMap;

import work.model.dao.UserDao;

public class UserService {
	
	private UserDao dao = UserDao.getInstance();
	
	/**
	 * 로그인
	 * @param uId 회원아이디
	 * @param uPw 회원비밀번호
	 * @return HashMap
	 */
	public HashMap<String, String> login(String uId, String uPw){
		System.out.println("userService : " + uId + uPw);
		return dao.login(uId, uPw);
	}
	
	/**
	 * 회원 중복 조회
	 * @param uId 회원아이디
	 * @return true / false
	 */
	public boolean dupliUser(String uId) {
		return dao.dupliUser(uId);
	}
	
	/**
	 * 네이버 회원가입
	 * @param uId 회원아이디
	 * @param name 이름(닉네임)
	 * @param gender 성별
	 * @param age 나이
	 * @param email 이메일
	 * @param uImg 프로필사진
	 * @return
	 */
	public int insertUser(String uId, String name, String gender, String age, String email,String uImg)  {
		return dao.insertUser(uId, name, gender, age, email, uImg);
	}
	
	/**
	 * 카카오 회원 회원가입
	 * @param uId 회원아이디
	 * @param name 이름
	 * @param uImg 프로필사진
	 * @return
	 */
	public int insertUser(String uId, String name ,String uImg)  {
		return dao.insertUser(uId, name, uImg);
	}
	
	/**
	 * 일반 회원가입
	 * @param uId 회원아이디(필수)
	 * @param uPw 비밀번호(필수)
	 * @param name 이름(필수)
	 * @param gender 성별
	 * @param age 나이
	 * @param mobile 핸드폰(필수)
	 * @param email 이메일
	 * @param uImg 프로필사진
	 * @return
	 */
	public int insertUser(String uId, String uPw, String name, String gender, String age, String mobile, String email,String uImg)  {
		String pathUimg = "images\\user\\" + uImg;

		return dao.insertUser(uId, uPw, name, gender, age, mobile, email, pathUimg);
	}
	
	/**
	 * 로그인 시 회원 이미지 업데이트
	 * @param uImg 회원프로필사진
	 * @param uId 회원아이디
	 * @return
	 */
	public int updateUserImg(String uImg, String uId) {
		return dao.updateUserImg(uImg, uId);
	}
		
	/**
	 * 비밀번호 찾기하기 전 회원이 존재하는지 검색
	 * 비밀번호 찾기 1단계
	 * @param uId 회원아이디
	 * @param name 이름
	 * @return
	 */
	public int selectPw(String uId, String name){
		return dao.selectPw(uId, name);
	}
	
	/**
	 * 비밀번호 찾기 2단계
	 * @param uId 회원아이디
	 * @param uPw 비밀번호
	 * @return
	 */
	public int selectPw2(String uId, String uPw){
		return dao.selectPw2(uId, uPw);
	}
	
	/**
	 * 아이디 찾기
	 * @param mobile 핸드폰
	 * @param name 이름
	 * @return
	 */
	public String selectId(String mobile, String name){
		return dao.selectId(mobile, name);
	}
		
}
