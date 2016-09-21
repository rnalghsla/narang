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
	 * 1. 댓글 등록
	 * Insert into comments
	 * values(comments_count.nextval, 1, ‘park’, ‘이 모임 좋아요~’, sysdate);
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
			sql.append("values(?, ?, ?, ?, ?)");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, cId);
			pstmt.setInt(2, bId);
			pstmt.setString(3, uId);
			pstmt.setString(4, cContent);
			pstmt.setString(5, cDate);
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error : 등록 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;
	}
	
	/**
	 * 2. 댓글 수정
	 * Update comments set
	 * c_content = ‘비오면 어떻게 되나요?’ where c_id= 3;
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
			System.out.println("Error : 변경 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);			
		}
		return 0;
	}
	
	/**
	 * 3. 내가 쓴 댓글 보기
	 * Select * From comments Where u_id='park';
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
			System.out.println("Error : 조회 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return list;
	}
	
	/**
	 * 4. 댓글 삭제
	 * Delete from comments Where c_id=2;
	 */
	public int deleteComment(int cId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = factory.getConnection();
			String sql = "delete from comments where c_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cId);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error : 삭제 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);			
		}
		return 0;
	}
}
