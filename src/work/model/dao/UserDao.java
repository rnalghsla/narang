package work.model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import work.model.dto.User;
public class UserDao {
	private static UserDao instance = new UserDao();
	private FactoryDao factory = FactoryDao.getInstance();
	String temp_id;

	public static UserDao getInstance() {
		return instance;
	}
	
	public UserDao(){}
	
	
	/**
	 * 일반 회원 가입
	 * 
	 * @param u_id 아이디
	 * @param u_pw 비밀번호
	 * @param name 이름(닉네임)
	 * @param gender 성별
	 * @param age 나이
	 * @param mobile 모바일
	 * @param email 이메일
	 * @param u_img 프로필사진
	 * @return 회원등록
	 */
	public int insertUser(String u_id, String u_pw, String name, String gender, String age, String mobile, String email,String u_img)  {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = factory.getConnection();
			String sql = "Insert into users values(?,?,?,?,?,?,?,0,?,'G')";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, u_id);
			stmt.setString(2, u_pw);
			stmt.setString(3, name);
			stmt.setString(4, gender);
			stmt.setString(5, age);
			stmt.setString(6, mobile);
			stmt.setString(7, email);
			stmt.setString(8, u_img);
			return stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("error: insertUser error");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt);
		}
		return 0;
	}
	
	/**
	 * 네이버로 로그인하는 사람 등록 
	 * 
	 * @param u_id 아이디
	 * @param name 이름
	 * @param gender 성별
	 * @param age 나이
	 * @param email 이메일
	 * @param u_img 이미지
	 * @return 회원등록
	 */
	public int insertUser(String u_id, String name, String gender, String age, String email,String u_img)  {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = factory.getConnection();
			String sql ="Insert into users values(?,null,?,?,?,null,?,0,?,'N')";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, u_id);
			stmt.setString(2, name);
			stmt.setString(3, gender);
			stmt.setString(4, age);
			stmt.setString(5, email);
			stmt.setString(6, u_img);
			return stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("error: insertUser error");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt);
		}
		return 0;
	}
	
	/**
	 * 카카오로 로그인 하는 회원 등록
	 * 
	 * @param u_id 아이디
	 * @param name 이름(닉네임)
	 * @param u_img 이미지
	 * @return 회원등록
	 */
	public int insertUser(String u_id, String name ,String u_img)  {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = factory.getConnection();
			String sql ="Insert into users values(?,null,?,null,null,null,null,0,?,'K')";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, u_id);
			stmt.setString(2, name);
			stmt.setString(3, u_img);
			return stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("error: insertUser error");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt);
		}
		return 0;
	}
	
	/**
	 * 로그인 메서드 
	 * 
	 * @param u_id 유저아이디
	 * @param u_pw 유저비밀번호
	 * @return 이름, 등급 반환
	 */
	public HashMap<String, String> login(String u_id, String u_pw){
		Connection conn = null;
		PreparedStatement stmt = null;
		HashMap<String, String> map = new HashMap<String, String>();
		ResultSet rs = null;
		try {
			conn = factory.getConnection();
			String sql = " Select name, grade from users where u_id =? and u_pw=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, u_id);
			stmt.setString(2, u_pw);
			rs = stmt.executeQuery();
			while (rs.next()) {
				map.put("name", rs.getString(1));
				map.put("grade", rs.getString(2));
				temp_id = u_id;
			}
			System.out.println("dao : "+map);
			return map;
		} catch (SQLException e) {
			System.out.println("error: login error");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt, rs);
		}
		return null;
	}
	
	/**
	 * 내정보 변경 메서드 
	 * 
	 * @param u_id 회원 아이디
	 * @param name 회원 이름
	 * @param mobile 회원 모바일
	 * @param email 회원 이메일
	 * @param u_img 프로필사진
	 * @return 회원 정보 변경시 1 변경불가능시 0 반환
	 */
	public int updateMyUserInfo(String u_id , String name, String mobile, String email, String u_img){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = factory.getConnection();
			String sql = "Update users set name=? , mobile=?, email=?, u_img = ? where u_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, mobile);
			stmt.setString(3, email);
			stmt.setString(4, u_img);
			stmt.setString(5, u_id);
			int rows = stmt.executeUpdate();
			return rows;
		} catch (SQLException e) {
			System.out.println("error: updateMyUserInfo error");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt);
		}
		return 0;
	}
	
	/**
	 * 비밀번호 변경 메서드 
	 * 
	 * @param u_id 유저아이디
	 * @param u_pw 유저비밀번호
	 * @param ch_pw 유저 변경 비밀번호
	 * @return 비밀번호가 완료되면 1 아니면 0 반환
	 */
	public int updateUserPw(String u_id, String u_pw, String ch_pw){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = factory.getConnection();
			String sql = " Update users set u_pw=? where u_id=? and u_pw=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, ch_pw);
			stmt.setString(2, u_id);
			stmt.setString(3, u_pw);
			int rows = stmt.executeUpdate();
			return rows;
		} catch (SQLException e) {
			System.out.println("error: updateUserPw error");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt);
		}
		return 0;
	}
	
	/**
	 * 내정보 조회 메서드
	 * 
	 * @return 회원 정보
	 */
	public User selectMyUserInfo() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = factory.getConnection();
			String sql = "Select * from users where u_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, temp_id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				String u_pw = rs.getString("u_pw");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				String age = rs.getString("age");
				String mobile = rs.getString("mobile");
				String email = rs.getString("email");
				int blacklistCount = rs.getInt("blacklistCount");
				String uImg = rs.getString("uImg");
				String grade = rs.getString("grade");
				return new User(temp_id, u_pw,  name,  gender,  age,  mobile, email, blacklistCount, uImg,  grade);
			}
		} catch (SQLException e) {
			System.out.println("error: selectMyUserInfo error");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt, rs);
		}
		return null;
	}
	
	/**
	 * 회원 탈퇴 메서드
	 * 
	 * @param u_id 회원아이디
	 * @param u_pw 회원비밀번호
	 * @return 탈퇴성공시 1 아닐시 0 반환
	 */
	public int updateUserGradeNone(String u_id, String u_pw){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = factory.getConnection();
			String sql = "Update users set grade =‘X’ where u_id =? and u_pw=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, u_id);
			stmt.setString(2, u_pw);
			int rows = stmt.executeUpdate();
			return rows;
		} catch (SQLException e) {
			System.out.println("error: updateUserGradeNone error");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt);
		}
		return 0;
	}
	
	/**
	 * 회원 전체 조회
	 * 
	 * @return 모든 회원 정보 조회
	 */
	public ArrayList<User> selectAllUser() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<User> list = new ArrayList<User>();
		try {
			conn = factory.getConnection();
			String sql = "Select * from users";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				String u_id = rs.getString("u_id");
				String u_pw = rs.getString("u_pw");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				String age = rs.getString("age");
				String mobile = rs.getString("mobile");
				String email = rs.getString("email");
				int blacklistCount = rs.getInt("blacklistCount");
				String uImg = rs.getString("uImg");
				String grade = rs.getString("grade");
				list.add(new User(u_id, u_pw,  name,  gender,  age,  mobile, email, blacklistCount, uImg,  grade));
			}
			return list;
		} catch (SQLException e) {
			System.out.println("error: selectAllUser error");
			e.printStackTrace();
		} finally {

			factory.close(conn, stmt, rs);
		}
		return null;
	}
	
	/**
	 * 블랙리스트 전체조회
	 * 
	 * @return 블랙리스트 회원 정보 
	 */
	public ArrayList<User> selectUserBlackList() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<User> list = new ArrayList<User>();
		try {
			conn = factory.getConnection();
			String sql = "Select * from users where grade='B'";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				String u_id = rs.getString("u_id");
				String u_pw = rs.getString("u_pw");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				String age = rs.getString("age");
				String mobile = rs.getString("mobile");
				String email = rs.getString("email");
				int blacklistCount = rs.getInt("blacklistCount");
				String uImg = rs.getString("uImg");
				String grade = rs.getString("grade");
				list.add(new User(u_id, u_pw,  name,  gender,  age,  mobile, email, blacklistCount, uImg,  grade));
			}
			return list;
		} catch (SQLException e) {
			System.out.println("error: selectUserBlackList error");
			e.printStackTrace();
		} finally {

			factory.close(conn, stmt, rs);
		}
		return null;
	}
	
	/**
	 * 한 회원 상세조회
	 * 
	 * @param u_id 회원 아이디
	 * @return 회원 개인정보 조회
	 */
	public User selectOneUser(String u_id){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = factory.getConnection();
			String sql = "Select * from users where u_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, u_id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				String u_pw = rs.getString("u_pw");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				String age = rs.getString("age");
				String mobile = rs.getString("mobile");
				String email = rs.getString("email");
				int blacklistCount = rs.getInt("blacklistCount");
				String uImg = rs.getString("uImg");
				String grade = rs.getString("grade");
				return new User(u_id, u_pw,  name,  gender,  age,  mobile, email, blacklistCount, uImg,  grade);
			}
		} catch (SQLException e) {
			System.out.println("error: selectOneUser error");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt, rs);
		}
		return null;
	}
	
	/**
	 * 전체 회원 정보 변경 메서드
	 * 
	 * @return 전체회원정보가 변경되면 변경된 숫자만큼 아니면 0 반환
	 */
	public int updateAllUserInfo(){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = factory.getConnection();
			String sql = "Update users set grade=‘G’ from user";
			stmt = conn.prepareStatement(sql);
			int rows = stmt.executeUpdate();
			return rows;
		} catch (SQLException e) {
			System.out.println("error: updateAllUserInfo error");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt);
		}
		return 0;
	}
	
	/**
	 * 특정 회원 정보 변경 
	 * 
	 * @param u_id 회원아이디
	 * @param name 이름
	 * @param email 이메일
	 * @param gender 성별
	 * @param age 나이
	 * @param mobile 모바일
	 * @param grade 등급
	 * @return 변경되면 1 변경되지않으면 0 반환
	 */
	public int updateOneUserInfo(String u_id, String name, String email, String gender, String age, String mobile, String grade){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = factory.getConnection();
			String sql = "update users set name=?, email=? , gender=?, age=?, mobile=?, grade=? where u_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, email);
			stmt.setString(3, gender);
			stmt.setString(4, age);
			stmt.setString(5, mobile);
			stmt.setString(6, grade);
			stmt.setString(7, u_id);
			int rows = stmt.executeUpdate();
			return rows;
		} catch (SQLException e) {
			System.out.println("error: updateOneUserInfo error");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt);
		}
		return 0;
	}
	
	/**
	 * 회원 삭제 메서드
	 * 
	 * @param u_id 회원아이디
	 * @param u_pw 회원 비밀번호
	 * @return
	 */
	public int deleteUser(String u_id, String u_pw) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = factory.getConnection();
			String sql = "Delete from users where u_id=? and u_pw = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, u_id);
			stmt.setString(2, u_pw);
			int rows = stmt.executeUpdate();
			return rows;
		} catch (SQLException e) {
			System.out.println("error: deleteUser error");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt);
		}
		return 0;
	}
	
//------------------------------------------------하니 추가----------------------------------------------------------

	/**
	 * 회원 중복 조회
	 * @param uId 회원아이디
	 * @return 존재하면 true, 존재하지 않으면 false
	 */
	public boolean dupliUser(String uId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = factory.getConnection();
			String sql = "select name from users where u_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, uId);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("error: dupliUser error");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt, rs);
		}
		return false;
	}
	
	/**
	 * 로그인때 회원 이미지 업데이트
	 * @param uImg 회원이미지
	 * @param uId 회원아이디
	 * @return
	 */
	public int updateUserImg(String uImg, String uId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = factory.getConnection();
			String sql = "update users set u_img=? where u_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, uImg);
			stmt.setString(2, uId);
			return stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error: UpdateUserImg error");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt, rs);
		}
		return -1;
	}
	
	/**
	 * 아이디찾기
	 * @param mobile 핸드폰
	 * @param name 이름
	 * @return
	 */
	public String selectId(String mobile, String name){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = factory.getConnection();
			String sql = "select u_id from users where mobile=? and name=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, mobile);
			stmt.setString(2, name);
			rs = stmt.executeQuery();
			if (rs.next()) {
				String u_id = rs.getString("u_id");
				return u_id;
			}
		} catch (SQLException e) {
			System.out.println("error: selectPw error");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt, rs);
		}
		return null;
	}
	
	/**
	 * 아이디찾기 1단계
	 * @param uId 회원아이디
	 * @param name 이름
	 * @return
	 */
	public int selectPw(String uId, String name){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = factory.getConnection();
			String sql = "Select * from users where u_id=? and name=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, uId);
			stmt.setString(2, name);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return 1;
			}
		} catch (SQLException e) {
			System.out.println("error: selectPw error");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt, rs);
		}
		return -1;
	}
	
	/**
	 * 비밀번호 찾기 2단계
	 * @param uId 회원아이디
	 * @param uPw 비밀번호
	 * @return
	 */
	public int selectPw2(String uId, String uPw){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		System.out.println("dao :"+uId);
		System.out.println("dao :"+uPw);
		try {
			conn = factory.getConnection();
			String sql = "update users set u_pw=? where u_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, uPw);
			stmt.setString(2, uId);
			return stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error: selectPw2 error");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt, rs);
		}
		return -1;
	}
	
}
