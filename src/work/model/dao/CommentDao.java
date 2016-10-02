package work.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import work.model.dto.Comment;

public class CommentDao {
	private FactoryDao factory = FactoryDao.getInstance();
	
	private static CommentDao instance = new CommentDao();
	
	private CommentDao() {}
	
	public static CommentDao getInstance() {
		return instance;
	}
	
	
	/**
	 * 1. ��� ���
	 * @param dto �Է��� ��ۿ� ���� ����
	 * @return ������ ������ ���� �� ��ȯ
	 */
	public int  insertComment(Comment dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int cId = dto.getcId();
		int bId = dto.getbId();
		String uId = dto.getuId();
		String cContent = dto.getcContent();
		String cDate = dto.getcDate();
		
		try {
			conn = factory.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into comments ");
			sql.append("values(comments_count, ?, ?, ?, ?)");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, bId);
			pstmt.setString(2, uId);
			pstmt.setString(3, cContent);
			pstmt.setString(4, cDate);
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error : ��� ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;
	}
	
	/**
	 * 2. ��� ����
	 * @param cId ��۹�ȣ
	 * @param cContent ��۳���
	 * @return ������ ������ ���� �� ��ȯ
	 */
	public int updateComment(int cId, String cContent) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = factory.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update comments set ");
			sql.append("c_content=? ");
			sql.append("where c_id=?");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, cContent);
			pstmt.setInt(2, cId);
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error : ���� ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);			
		}
		return 0;
	}
	
	/**
	 * 3. �Խñ� Ŭ���� ��� ����Ʈ ����
	 * @param bId �Խñ� ��ȣ
	 * @return �Խñ۸��� �ۼ��� ��� ����Ʈ
	 */
	public ArrayList<Comment> selectComment(int bId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Comment> list = new ArrayList<Comment>();
		
		try {
			conn = factory.getConnection();
			String sql = "select * from comments where b_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bId);
			rs = pstmt.executeQuery();
			
			int cId = 0;
			String uId = null;
			String cContent = null;
			String cDate = null;
			
			while(rs.next()) {
				cId = rs.getInt("c_id");
				uId = rs.getString("u_id");
				cContent = rs.getString("c_content");
				cDate = rs.getString("c_date");
				
				list.add(new Comment(cId, bId, uId, cContent, cDate));
			}
		} catch (SQLException e) {
			System.out.println("Error : ��ȸ ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return list;
	}
	
	/**
	 * 4. ȸ�� : ��� ����
	 * @param cId ��۹�ȣ
	 * @param uId ���̵�
	 * @return ������ ������ ���� �� ��ȯ
	 */
	public int userDeleteComment(int cId, String uId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = factory.getConnection();
			String sql = "delete from comments where c_id=? and u_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cId);
			pstmt.setString(2, uId);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error : ���� ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);			
		}
		return 0;
	}
	
	/**
	 * 4-1. ������ : ��� ����
	 * @param cId ��� ��ȣ
	 * @return ������ ������ ���� �� ��ȯ
	 */
	public int adminDeleteComment(int cId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = factory.getConnection();
			String sql = "delete from comments where c_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cId);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error : ���� ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);			
		}
		return 0;
	}
	
	/**
	 * 5. ���� �� ��� ����
	 * @param uId ���̵�
	 * @return ���� �ۼ��� ��� ����Ʈ ��ȯ
	 */
	public ArrayList<Comment> selectMyComment(String uId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Comment> list = new ArrayList<Comment>();
		
		try {
			conn = factory.getConnection();
			String sql = "select * from comments where u_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uId);
			rs = pstmt.executeQuery();
			
			int cId = 0;
			int bId = 0;
			String cContent = null;
			String cDate = null;
			
			while(rs.next()) {
				cId = rs.getInt("c_id");
				bId = rs.getInt("b_id");
				cContent = rs.getString("c_content");
				cDate = rs.getString("c_date");
				
				list.add(new Comment(cId, bId, uId, cContent, cDate));
			}
		} catch (SQLException e) {
			System.out.println("Error : ��ȸ ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return list;
	}
}
