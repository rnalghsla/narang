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
	 * @param dto 입력한 댓글에 대한 정보
	 * @return 성공한 쿼리문 라인 수 반환
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
			System.out.println("Error : 등록 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;
	}
	
	/**
	 * 2. 댓글 수정
	 * @param cId 댓글번호
	 * @param cContent 댓글내용
	 * @return 성공한 쿼리문 라인 수 반환
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
	 * 3. 게시글 클릭시 댓글 리스트 보기
	 * @param bId 게시글 번호
	 * @return 게시글마다 작성된 댓글 리스트
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
			System.out.println("Error : 조회 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return list;
	}
	
	/**
	 * 4. 회원 : 댓글 삭제
	 * @param cId 댓글번호
	 * @param uId 아이디
	 * @return 성공한 쿼리문 라인 수 반환
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
			System.out.println("Error : 삭제 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);			
		}
		return 0;
	}
	
	/**
	 * 4-1. 관리자 : 댓글 삭제
	 * @param cId 댓글 번호
	 * @return 성공한 쿼리문 라인 수 반환
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
			System.out.println("Error : 삭제 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);			
		}
		return 0;
	}
	
	/**
	 * 5. 내가 쓴 댓글 보기
	 * @param uId 아이디
	 * @return 내가 작성한 댓글 리스트 반환
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
}
