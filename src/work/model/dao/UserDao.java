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
	 * ȸ�� ����
	 * 
	 * @param u_id ���̵�
	 * @param u_pw ��й�ȣ
	 * @param name �̸�(�г���)
	 * @param gender ����
	 * @param age ����
	 * @param mobile �����
	 * @param email �̸���
	 * @param u_img �����ʻ���
	 * @return ȸ�����
	 */
	public int insertUser(String u_id, String u_pw, String name, String gender, String age, String mobile, String email,String u_img)  {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = factory.getConnection();
			String sql = "Insert into user values(?,?,?,?,?,?,?,0,?,'G')";
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
	 * ���̹��� �α����ϴ� ��� ��� 
	 * 
	 * @param u_id ���̵�
	 * @param name �̸�
	 * @param gender ����
	 * @param age ����
	 * @param email �̸���
	 * @param u_img �̹���
	 * @return ȸ�����
	 */
	public int insertUser(String u_id, String name, String gender, String age, String email,String u_img)  {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = factory.getConnection();
			String sql ="Insert into user values(?,null,?,?,?,null,?,0,?,'N')";
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
	 * īī���� �α��� �ϴ� ȸ�� ���
	 * 
	 * @param u_id ���̵�
	 * @param name �̸�(�г���)
	 * @param u_img �̹���
	 * @return ȸ�����
	 */
	public int insertUser(String u_id, String name ,String u_img)  {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = factory.getConnection();
			String sql ="Insert into user values(?,null,?,null,null,null,null,0,?,'K')";
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
	 * �α��� �޼��� 
	 * 
	 * @param u_id �������̵�
	 * @param u_pw ������й�ȣ
	 * @return �̸�, ��� ��ȯ
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
	 * ������ ���� �޼��� 
	 * 
	 * @param u_id ȸ�� ���̵�
	 * @param name ȸ�� �̸�
	 * @param mobile ȸ�� �����
	 * @param email ȸ�� �̸���
	 * @param u_img �����ʻ���
	 * @return ȸ�� ���� ����� 1 ����Ұ��ɽ� 0 ��ȯ
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
	 * ��й�ȣ ���� �޼��� 
	 * 
	 * @param u_id �������̵�
	 * @param u_pw ������й�ȣ
	 * @param ch_pw ���� ���� ��й�ȣ
	 * @return ��й�ȣ�� �Ϸ�Ǹ� 1 �ƴϸ� 0 ��ȯ
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
	 * ������ ��ȸ �޼���
	 * 
	 * @return ȸ�� ����
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
	 * ȸ�� Ż�� �޼���
	 * 
	 * @param u_id ȸ�����̵�
	 * @param u_pw ȸ����й�ȣ
	 * @return Ż�𼺰��� 1 �ƴҽ� 0 ��ȯ
	 */
	public int updateUserGradeNone(String u_id, String u_pw){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = factory.getConnection();
			String sql = "Update users set grade =��X�� where u_id =? and u_pw=?";
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
	 * ȸ�� ��ü ��ȸ
	 * 
	 * @return ��� ȸ�� ���� ��ȸ
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
	 * ������Ʈ ��ü��ȸ
	 * 
	 * @return ������Ʈ ȸ�� ���� 
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
	 * �� ȸ�� ����ȸ
	 * 
	 * @param u_id ȸ�� ���̵�
	 * @return ȸ�� �������� ��ȸ
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
	 * ��ü ȸ�� ���� ���� �޼���
	 * 
	 * @return ��üȸ�������� ����Ǹ� ����� ���ڸ�ŭ �ƴϸ� 0 ��ȯ
	 */
	public int updateAllUserInfo(){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = factory.getConnection();
			String sql = "Update users set grade=��G�� from user";
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
	 * Ư�� ȸ�� ���� ���� 
	 * 
	 * @param u_id ȸ�����̵�
	 * @param name �̸�
	 * @param email �̸���
	 * @param gender ����
	 * @param age ����
	 * @param mobile �����
	 * @param grade ���
	 * @return ����Ǹ� 1 ������������� 0 ��ȯ
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
	 * ȸ�� ���� �޼���
	 * 
	 * @param u_id ȸ�����̵�
	 * @param u_pw ȸ�� ��й�ȣ
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
	
	
	
	
}
