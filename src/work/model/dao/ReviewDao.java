package work.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import work.model.dto.Review;

public class ReviewDao {
	private FactoryDao factory = FactoryDao.getInstance();
	
	private static ReviewDao instance = new ReviewDao();
	
	private ReviewDao() {}
	
	public static ReviewDao getInstance() {
		return instance;
	}
	
	/**
	 * 1. 일반 후기 쓰기 : insertGeneralReview
	 * Insert into review values(seq_review_rid.nextval, ?,?,?,?,?,?,?);
	 */
	public int  insertGeneralReview(Review dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int rType = dto.getrType();
		String uId = dto.getuId();
		String rTitle = dto.getrTitle();
		String rContent = dto.getrContent();
		String rDate = dto.getrDate();
		String rImg = dto.getrImg();
		int rHit = dto.getrHit();
		
		try {
			conn = factory.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into review ");
			sql.append("values(seq_review_rid.nextval, ?, ?, ?, ?, ?, ?, ?)");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, rType);
			pstmt.setString(2, uId);
			pstmt.setString(3, rTitle);
			pstmt.setString(4, rContent);
			pstmt.setString(5, rDate);
			pstmt.setString(6, rImg);
			pstmt.setInt(7, rHit);
			
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
	 * 2. 공지사항글쓰기 : insertNoticeReview
	 * Insert into review values(seq_review_rid.nextval, ?,?,?,?,?,null,0);
	 */
	public int  insertNoticeReview(Review dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int rType = dto.getrType();
		String uId = dto.getuId();
		String rTitle = dto.getrTitle();
		String rContent = dto.getrContent();
		String rDate = dto.getrDate();
		String rImg = dto.getrImg();
		int rHit = dto.getrHit();
		
		try {
			conn = factory.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into comments ");
			sql.append("values(seq_review_rid.nextval, ?, ?, ?, ?, ?, ?, ?)");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, rType);
			pstmt.setString(2, uId);
			pstmt.setString(3, rTitle);
			pstmt.setString(4, rContent);
			pstmt.setString(5, rDate);
			pstmt.setString(6, rImg);
			pstmt.setInt(7, rHit);
			
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
	 * 3. 후기 글 수정 : updateReview
	 * Update review set r_title=?, r_content=?, r_img=? 
	 * where r_id=? and u_id=?
	 */
	public int updateReview(Review dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int rId = dto.getrId();
		String uId = dto.getuId();
		String rTitle = dto.getrTitle();
		String rContent = dto.getrContent();
		String rImg = dto.getrImg();
		
		try{
			conn = factory.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update review set ");
			sql.append("r_title=?, r_content=?, r_img=? ");
			sql.append("where r_id=? and u_id=?");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, rTitle);
			pstmt.setString(2, rContent);
			pstmt.setString(3, rImg);
			pstmt.setInt(4, rId);
			pstmt.setString(5, uId);
			
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
	 * 4. 후기 글 삭제
	 * Delete from review where r_id=? and u_id=?
	 */
	public int userDeleteReview(int rId, String uId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = factory.getConnection();
			String sql = "delete from review where r_id=? u_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rId);
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
	 * 4. 후기 글 삭제(관리자)
	 * Delete from review where r_id=?
	 */
	public int adminDeleteReview(int rId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = factory.getConnection();
			String sql = "delete from review where r_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rId);
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
	 * 5. 후기 상세보기 : selectOneReview
	 * Select * from review where r_id=?
	 */
	public Review selectOneReview(int rId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Review dto = null;
		
		try {
			conn = factory.getConnection();
			String sql = "select * from review where r_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int rType = rs.getInt("r_type");
				String uId = rs.getString("u_id");
				String rTitle = rs.getString("r_title");
				String rContent = rs.getString("r_content");
				String rDate = rs.getString("r_date");
				String rImg = rs.getString("r_img");
				int rHit = rs.getInt("r_hit");
				
				dto = new Review(rId, rType, uId, rTitle, rContent, rDate, rImg, rHit);
			}
		} catch (SQLException e) {
			System.out.println("Error : 조회 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);			
		}
		return dto;
	}
	
	/**
	 * 6. 후기 전체 보기 : selectAllReview
	 * Select * from review where r_type=1
	 */
	public ArrayList<Review> selectAllReview() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Review> list = new ArrayList<Review>();
		Review dto = null;
		
		try {
			conn = factory.getConnection();
			String sql = "select * from review where r_type=1";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int rId = 0;
			int rType = 0;
			String uId = null;
			String rTitle = null;
			String rContent = null;
			String rDate = null;
			String rImg = null;
			int rHit = 0;
			
			while(rs.next()) {
				rId = rs.getInt("r_id");
				rType = rs.getInt("r_type");
				uId = rs.getString("u_id");
				rTitle = rs.getString("r_title");
				rContent = rs.getString("r_content");
				rDate = rs.getString("r_date");
				rImg = rs.getString("r_img");
				rHit = rs.getInt("r_hit");
				
				dto = new Review(rId, rType, uId, rTitle, rContent, rDate, rImg, rHit);
				list.add(dto);
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
	 * 7. 공지사항 전체보기 : selectNoticeReview
	 * Select * from review where r_notice=0
	 */
	public ArrayList<Review> selectNoticeReview() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Review> list = new ArrayList<Review>();
		
		try {
			conn = factory.getConnection();
			String sql = "select * from review where r_type=0";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int rId = 0;
			int rType = 0;
			String uId = null;
			String rTitle = null;
			String rContent = null;
			String rDate = null;
			String rImg = null;
			int rHit = 0;
			
			while(rs.next()) {
				rId = rs.getInt("r_id");
				rType = rs.getInt("r_type");
				uId = rs.getString("u_id");
				rTitle = rs.getString("r_title");
				rContent = rs.getString("r_content");
				rDate = rs.getString("r_date");
				rImg = rs.getString("r_img");
				rHit = rs.getInt("r_hit");
				
				list.add(new Review(rId, rType, uId, rTitle, rContent, rDate, rImg, rHit));
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
	 * 9. 내가 쓴 후기 보기 : selectMyReview
	 * Select * from review where u_id=?
	 */
	public ArrayList<Review> selectMyReview(String uId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Review> list = new ArrayList<Review>();
		
		try {
			conn = factory.getConnection();
			String sql = "select * from review where u_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uId);
			rs = pstmt.executeQuery();
			
			int rId = 0;
			int rType = 0;
			String rTitle = null;
			String rContent = null;
			String rDate = null;
			String rImg = null;
			int rHit = 0;
			
			while (rs.next()) {
				rId = rs.getInt("r_id");
				rType = rs.getInt("r_type");
				rTitle = rs.getString("r_title");
				rContent = rs.getString("r_content");
				rDate = rs.getString("r_date");
				rImg = rs.getString("r_img");
				rHit = rs.getInt("r_hit");
				
				list.add(new Review(rId, rType, uId, rTitle, rContent, rDate, rImg, rHit));
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
	 * 10. 후기 제목으로 검색 : selectReviewTitle
	 * Select * from review where r_title=%?%
	 */
	public ArrayList<Review> selectReviewTitle(String title) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Review> list = new ArrayList<Review>();
		
		try {
			conn = factory.getConnection();
			String sql = "Select * from review where r_title=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+title+"%");
			rs = pstmt.executeQuery();
			
			int rId = 0;
			int rType = 0;
			String uId = null;
			String rTitle = null;
			String rContent = null;
			String rDate = null;
			String rImg = null;
			int rHit = 0;
			
			while (rs.next()) {
				rId = rs.getInt("r_id");
				rType = rs.getInt("r_type");
				uId = rs.getString("u_id");
				rTitle = rs.getString("r_title");
				rContent = rs.getString("r_content");
				rDate = rs.getString("r_date");
				rImg = rs.getString("r_img");
				rHit = rs.getInt("r_hit");
				
				list.add(new Review(rId, rType, uId, rTitle, rContent, rDate, rImg, rHit));
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
	 * 11. 후기 조회수 증가
	 * Update review set r_hit=r_hit+1 where r_id=? and u_id<>?
	 */ // 세션에 담긴 아이디 말고 선택한 글의 아이디값으로 조건 비교하기
	public int updateReviewHit(int rId, String uId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = factory.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update review set ");
			sql.append("r_hit=r_hit+1 ");
			sql.append("where r_id=? and u_id<>?");
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, rId);
			pstmt.setString(2, uId);
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error : 변경 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);			
		}
		return 0;
	}
}
