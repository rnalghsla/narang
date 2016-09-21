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
	 * �Ű��� ��ȸ (���� ȸ��)
	 * 
	 * @return �Ű���
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
	 * �Ű��� ��ü ��ȸ (��ü ȸ��)
	 * 
	 * @return �Ű��� ��ü ��ȸ
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
	 * �Ű��� �߰� �޼���
	 * 
	 * @param n_id �Ű��ȣ
	 * @param send_u_id �Ű��ѻ�� id
	 * @param rec_u_id �Ű� ���� ȸ�� id
	 * @param table_name �Ű�� �Խñ� �̸�
	 * @param table_id �Ű�� �Խñ� ��ȣ
	 * @return �Ű�Ϸ�� 1 �Ű� �Ұ��ɽ� 0 ��ȯ
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
	 * �Ű��� ���� �޼��� 
	 * 
	 * @param rec_u_id �Ű���� ȸ�� id
	 * @return �����Ϸ�� 1 �����Ұ��ɽ� 0 ��ȯ
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
	
//-------------------------�Ű� ���õ� ����߿��� users table���� �����ϴ� �޼���� ------------------------------------------------------------
	
	/**
	 * ������Ʈ ��� 
	 * 
	 * @param u_id ȸ�����̵�
	 * @return ��ω����� 1 ��ϺҰ����ϸ� 0 ��ȯ
	 */
	public int updateBlackListTrue(String u_id){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = factory.getConnection();
			String sql = "Update users set grade=��B�� where u_id=?";
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
	 * �Ű���� �� ��� ���� 
	 * 
	 * @param uId ���� ���̵�
	 * @return ����� B�� ���׷��̵�
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
	 * ������Ʈ ���� ��� (blacklist_count�� 10 �̻��� ȸ�� )
	 * 
	 * @return ������ ������ ���ڸ�ŭ ���н� 0 ��ȯ
	 */
	public int updateBlackListTrueAll(){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = factory.getConnection();
			String sql = "Update users set grade=��B�� where BLACKLIST_COUNT >= 10";
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
	 * ������Ʈ���� �Ϲ�ȸ������ ȸ��
	 * 
	 * @param u_id ȸ�����̵�
	 * @return �Ϲ�ȸ�� ����� 1 ���н� 0 ��ȯ
	 */
	public int updateBlackListFalse(String u_id){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = factory.getConnection();
			String sql = "Update users set grade=��G�� where u_id = ?";
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
	 * ������ �Ű� ���� �޼��� 
	 * 
	 * @param rec_u_id �Ű����ȸ�� id
	 * @return �Ű� �����Ǹ� count 1 ���� �� 1 ��ȯ  �ƴϸ� 0 ��ȯ
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
