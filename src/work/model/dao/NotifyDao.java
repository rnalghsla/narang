package work.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import work.model.dto.Notify;

public class NotifyDao {
	private static NotifyDao instance = new NotifyDao();
	private FactoryDao factory = FactoryDao.getInstance();
	
	public static NotifyDao getInstance() {
		return instance;
	}
	
	public NotifyDao(){}
	
	/**
	 * 신고목록 조회 (개인 회원)
	 * 
	 * @return 신고목록
	 */
	public Notify selectNotifyUser(String rec_u_id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = factory.getConnection();
			String sql = "Select * from notify where rec_u_id = ?";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				int n_id = rs.getInt("n_id");
				String send_u_id = rs.getString("send_u_id");
				String table_name = rs.getString("table_name");
				int table_id = rs.getInt("table_id");
				return new  Notify( n_id,  send_u_id, rec_u_id, table_name,table_id);
			}
		} catch (SQLException e) {
			System.out.println("error: selectNotifyUser error");
			e.printStackTrace();
		} finally {

			factory.close(conn, stmt, rs);
		}
		return null;
	}
	
	/**
	 * 신고목록 전체 조회 (전체 회원)
	 * 
	 * @return 신고목록 전체 조회
	 */
	public ArrayList<Notify> selectNotifyUserAll() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Notify> list = new ArrayList<Notify>();
		try {
			conn = factory.getConnection();
			String sql = "Select * from notify";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				int n_id = rs.getInt("n_id");
				String send_u_id = rs.getString("send_u_id");
				String rec_u_id = rs.getString("rec_u_id");
				String table_name = rs.getString("table_name");
				int table_id = rs.getInt("table_id");
				list.add(new  Notify( n_id,  send_u_id, rec_u_id, table_name,table_id));
			}
			return list;
		} catch (SQLException e) {
			System.out.println("error: selectNotifyUserAll error");
			e.printStackTrace();
		} finally {

			factory.close(conn, stmt, rs);
		}
		return null;
	}
	
	/**
	 * 신고자 추가 메서드
	 * 
	 * @param n_id 신고번호
	 * @param send_u_id 신고한사람 id
	 * @param rec_u_id 신고 당한 회원 id
	 * @param table_name 신고된 게시글 이름
	 * @param table_id 신고된 게시글 번호
	 * @return 신고완료시 1 신고 불가능시 0 반환
	 */
	public int insertNotifyUser(String n_id, String send_u_id, String rec_u_id, String table_name, String table_id)  {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = factory.getConnection();
			String sql = "Insert into notify values( ?,  ?,  ?,  ?,  ? )";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, n_id);
			stmt.setString(2, send_u_id);
			stmt.setString(3, rec_u_id);
			stmt.setString(4, table_name);
			stmt.setString(5, table_id);
			return stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("error: insertNotifyUser error");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt);
		}
		return 0;
	}
	
	/**
	 * 신고자 삭제 메서드 
	 * 
	 * @param rec_u_id 신고당한 회원 id
	 * @return 삭제완료시 1 삭제불가능시 0 반환
	 */
	public int deleteNotifyUser(String rec_u_id)  {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = factory.getConnection();
			String sql = "Delete from notify where rec_u_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, rec_u_id);
			return stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("error: deleteNotifyUser error");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt);
		}
		return 0;
	}
	
//-------------------------신고에 관련된 사안중에서 users table에서 동작하는 메서드들 ------------------------------------------------------------
	
	/**
	 * 블랙리스트 등록 
	 * 
	 * @param u_id 회원아이디
	 * @return 등록됬으면 1 등록불가능하면 0 반환
	 */
	public int updateBlackListTrue(String u_id){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = factory.getConnection();
			String sql = "Update users set grade=‘B’ where u_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, u_id);
			int rows = stmt.executeUpdate();
			return rows;
		} catch (SQLException e) {
			System.out.println("error: updateBlackListTrue error");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt);
		}
		return 0;
	}
/*	*//*
	 * 신고당한 자 등급 변경 
	 * 
	 * @param uId 유저 아이디
	 * @return 등급을 B로 업그레이드
	 *//*
	public int updateUserGradeCountBlackList(String uId){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = factory.getConnection();
			String sql = "update users set grade = 'B' where u_id = ? ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, uId);
			return stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("error: updateUserGradeCountBlackList error");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt);
		}
		return 0;
	}*/
	
	/**
	 * 블랙리스트 전부 등록 (blacklist_count가 10 이상인 회원 )
	 * 
	 * @return 성공시 변경한 숫자만큼 실패시 0 반환
	 */
	public int updateBlackListTrueAll(){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = factory.getConnection();
			String sql = "Update users set grade=‘B’ where BLACKLIST_COUNT >= 10";
			stmt = conn.prepareStatement(sql);
			int rows = stmt.executeUpdate();
			return rows;
		} catch (SQLException e) {
			System.out.println("error: updateBlackListTrueAll error");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt);
		}
		return 0;
	}
	
	/**
	 * 블랙리스트에서 일반회원으로 회복
	 * 
	 * @param u_id 회원아이디
	 * @return 일반회원 변경시 1 실패시 0 반환
	 */
	public int updateBlackListFalse(String u_id){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = factory.getConnection();
			String sql = "Update users set grade=‘G’ where u_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, u_id);
			int rows = stmt.executeUpdate();
			return rows;
		} catch (SQLException e) {
			System.out.println("error: updateBlackListFalse error");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt);
		}
		return 0;
	}
	
	/**
	 * 관리자 신고 인정 메서드 
	 * 
	 * @param rec_u_id 신고당한회원 id
	 * @return 신고가 인증되면 count 1 증가 및 1 반환  아니면 0 반환
	 */
	public int NotifyUserAgree(String rec_u_id)  {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = factory.getConnection();
			String sql = "update users set blacklist_count = blacklist_count +1 where u_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, rec_u_id);
			return stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("error: NotifyUserAgree error");
			e.printStackTrace();
		} finally {
			factory.close(conn, stmt);
		}
		return 0;
	}
}
