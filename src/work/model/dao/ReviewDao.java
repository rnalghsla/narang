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
	 * 1. �Ϲ� �ı� ���� : insertGeneralReview
	 * @param dto �Է��� �ı� ����
	 * @return ������ ������ ���� �� ��ȯ
	 */
	public int  inserReview(Review dto) {
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
			sql.append("values(review_count.nextval, ?, ?, ?, ?, ?, ?, ?)");
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
			System.out.println("Error : ��� ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;
	}
	
	/**
	 * 2. �������ױ۾��� : insertNoticeReview
	 * @param dto �Է��� �������� ����
	 * @return ������ ������ ���� �� ��ȯ
	 */
//	public int  insertNoticeReview(Review dto) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		int rType = dto.getrType();
//		String uId = dto.getuId();
//		String rTitle = dto.getrTitle();
//		String rContent = dto.getrContent();
//		String rDate = dto.getrDate();
//		String rImg = dto.getrImg();
//		int rHit = dto.getrHit();
//		
//		try {
//			conn = factory.getConnection();
//			StringBuilder sql = new StringBuilder();
//			sql.append("insert into comments ");
//			sql.append("values(seq_review_rid.nextval, ?, ?, ?, ?, ?, ?, ?)");
//			pstmt = conn.prepareStatement(sql.toString());
//			
//			pstmt.setInt(1, rType);
//			pstmt.setString(2, uId);
//			pstmt.setString(3, rTitle);
//			pstmt.setString(4, rContent);
//			pstmt.setString(5, rDate);
//			pstmt.setString(6, rImg);
//			pstmt.setInt(7, rHit);
//			
//			return pstmt.executeUpdate();
//		} catch (SQLException e) {
//			System.out.println("Error : ��� ����");
//			e.printStackTrace();
//		} finally {
//			factory.close(conn, pstmt);
//		}
//		return 0;
//	}
	
	/**
	 * 3. �ı� �� ���� : updateReview
	 * @param dto ������ �ı� �� ����
	 * @return ������ ������ ���� �� ��ȯ
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
			System.out.println("Error : ���� ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);			
		}
		return 0;
	}

	/**
	 * 4. �ı� �� ����(�Ϲ�ȸ��)
	 * @param rId �ı�� ��ȣ
	 * @param uId ���̵�
	 * @return ������ ������ ���� �� ��ȯ
	 */
	public int userDeleteReview(int rId, String uId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = factory.getConnection();
			String sql = "delete from review where r_id=? and u_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rId);
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
	 * 4-1. �ı� �� ����(������)
	 * @param rId �ı�۹�ȣ
	 * @return ������ ������ ���μ�
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
			System.out.println("Error : ���� ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);			
		}
		return 0;
	}
	
	/**
	 * 5. �ı� �󼼺��� : selectOneReview
	 * @param rId �ı��ȣ
	 * @return Ŭ���� �ı� ���� 
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
			System.out.println("Error : ��ȸ ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);			
		}
		return dto;
	}
	
	/**
	 * 6. �ı� ��ü ���� : userSelectAllReview
	 * @param �ı� Ÿ��(1:��, 2:����, 3:����, 4: �, 5:��ȭ)
	 * @return ��ü �ı� ����Ʈ ��ȯ
	 */
	public ArrayList<Review> userSelectAllReview(int rType) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Review> list = new ArrayList<Review>();
		Review dto = null;
		
		try {
			conn = factory.getConnection();
			String sql = "select * from review where r_type=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rType);
			rs = pstmt.executeQuery();
			
			int rId = 0;
			String uId = null;
			String rTitle = null;
			String rContent = null;
			String rDate = null;
			String rImg = null;
			int rHit = 0;
			
			while(rs.next()) {
				rId = rs.getInt("r_id");
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
			System.out.println("Error : ��ȸ ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return list;
	}
	
	/**
	 * 6-1. ������ �ı� ��ü����
	 * @return ��ü �ı� ����Ʈ
	 */
	public ArrayList<Review> adminSelectAllReview() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Review> list = new ArrayList<Review>();
		Review dto = null;
		
		try {
			conn = factory.getConnection();
			String sql = "select * from review where r_type<>0 and r_type<>6";
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
			System.out.println("Error : ��ȸ ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return list;
	}
	
	/**
	 * 7. �������� ��ü���� : selectNoticeReview
	 * @return �������� ��ü ����Ʈ ��ȯ
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
			System.out.println("Error : ��ȸ ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return list;
	}
	
	/**
	 * 9. ���� �� �ı� ���� : selectMyReview
	 * @param uId ���̵�
	 * @return ���� �� �ı� ��ü ����Ʈ ��ȯ
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
			System.out.println("Error : ��ȸ ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);			
		}
		return list;
	}
	
	/**
	 * 10. �ı� �������� �˻� : selectReviewTitle
	 * @param title �Է��� �ı� ����
	 * @return �Է��� �ı��������� �˻��� �ı� ����Ʈ
	 */
	public ArrayList<Review> selectReviewTitle(String title) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Review> list = new ArrayList<Review>();
		
		try {
			conn = factory.getConnection();
			String sql = "Select * from review where r_title Like ?";
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
			System.out.println("Error : ��ȸ ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);			
		}
		return list;
	}
	
	/**
	 * 11. �ı� ��ȸ�� ����
	 * @param rId �ı� ��ȣ
	 * @param uId Ŭ���� �ı���� ���̵� 
	 * @return ������ ������ ���μ�
	 */
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
			System.out.println("Error : ���� ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);			
		}
		return 0;
	}
}
