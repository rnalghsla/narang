/**
 * 
 */
package work.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import work.model.dto.Board;

/**
 * @author ������
 *
 */
public class BoardDao {
	private FactoryDao factory = FactoryDao.getInstance();

	/**
	 * �⺻ ������ : jdbc driver �ε� ����
	 */
	private BoardDao(){}//�⺻�����ڸ� ��������� �ȸ���� �����Ͻÿ� ���� ����� �Ǹ鼭 public���� ���������
	//singleTon pattern�� ���� �ǹǷ� ���� private���� �������Ѵ�.

	private static BoardDao instance = new BoardDao();

	public static BoardDao getInstance(){
		return instance;
	}

	/**
	 * 1. �Խ��� ����ϴ� �޼���
	 * @param board �Խñ�
	 * @return ������ 0���� ū��, ���н� 0�� ��ȯ
	 */
	public int insertBoard(Board board){
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		//String BoardNum = board.getBoardNum();
		//int bId = board.getbId();
		int bTab = board.getbTab();
		String bTitle = board.getbTitle();
		String uId = board.getuId();
		String bContent = board.getbContent();
		String bPlace = board.getbPlace();
		int bLimit = board.getbLimit();
		String bStartMatch = board.getbStartMatch();
		String bEndMatch = board.getbEndMatch();
		String bImg1 = board.getbImg1();
		String bImg2 = board.getbImg2();
		String bImg3 = board.getbImg3();
		String bDate = board.getbDate();
		int bHit = board.getbHit();
		
		try {
			conn = factory.getConnection();
			String sql ="Insert into board values(board_count.nextval,?,?,?,?,?,?,?,?,?,?,?,to_date(sysdate,'yyyy-mm-dd HH24:mi:ss'),?)";
			pstmt = conn.prepareStatement(sql);
			//pstmt.setString(1, BoardNum);
			pstmt.setInt(1, bTab);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, uId);
			pstmt.setString(4, bContent);
			pstmt.setString(5, bPlace);
			pstmt.setInt(6, bLimit);
			pstmt.setString(7, bStartMatch);
			pstmt.setString(8, bEndMatch);
			pstmt.setString(9, bImg1);
			pstmt.setString(10, bImg2);
			pstmt.setString(11, bImg3);
			//pstmt.setString(12, bDate);
			pstmt.setInt(12, bHit);
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error : �Խñ� ��� ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;		
	}
	
	/**
	 * 2. �� �� ���� updateMyBoard 
	 * @param bId �Խñ� ��ȣ
	 * @param bTitle �Խñ� ����
	 * @param uId �ۼ���
	 * @param bContent ����
	 * @param bPlace ���
	 * @param bLimit �ο�����
	 * @param bStartMatch ���۽ð�
	 * @param bEndMatch ���ð�
	 * @param bImg1 �̹���1
	 * @param bImg2 �̹���2
	 * @param bImg3 �̹���3
	 * @return ������ 0���� ū�� �׷��� ������ 0�� ��ȯ
	 */
	public int updateMyBoard(int bId, String bTitle, String uId, String bContent, String bPlace, int bLimit, String bStartMatch,String bEndMatch, String bImg1, String bImg2, String bImg3){
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = factory.getConnection();
			//1.sql������� ����
			String sql ="update board set b_title=?, b_content=?, b_place=?,b_limit=?, b_start_match=to_date(?, 'yyyy-mm-dd HH24:MI:SS'), b_end_match=to_date(?, 'yyyy-mm-dd HH24:MI:SS'), b_Img1=?, b_img2=?, b_img3=? where b_id=? and u_id=?";
			pstmt = conn.prepareStatement(sql);
			//2. ?���εǵ��� ���ް� ����
			pstmt.setString(1, bTitle);
			pstmt.setString(2, bContent);
			pstmt.setString(3, bPlace);
			pstmt.setInt(4, bLimit);
			pstmt.setString(5, bStartMatch);
			pstmt.setString(6, bEndMatch);
			pstmt.setString(7, bImg1);
			pstmt.setString(8, bImg2);
			pstmt.setString(9, bImg3);
			pstmt.setInt(9, bId);
			pstmt.setString(9, uId);

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error : �Ϻ� ������Ʈ ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;		
	}

	/**
	 * 3. �� �� ���� deleteMyBoard
	 * @param bId �Խñ� ��ȣ
	 * @param uId ȸ�����̵�
	 * @return ������ 0���� ū��, ���н� 0�� ��ȯ
	 */
	public int deleteMyBoard(int bId, String uId){
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = factory.getConnection();
			String sql ="Delete from board where b_id=? and u_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bId);
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
	 * 4. �Խñ� �󼼺��� selectOneBoard
	 * @param bId �Խñ۹�ȣ
	 * @return ������ board��ü�� ��ȯ, ���н� 0�� ��
	 */
	public Board selectOneBoard(int bId){
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		try {
			conn = factory.getConnection();
			String sql ="Select * from board where B_ID=?";
			String sql2 ="update board set b_hit=b_hit+1 where B_ID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);
			pstmt.setInt(1, bId);
			pstmt2.setInt(1, bId);
			pstmt2.executeUpdate();
			rs = pstmt.executeQuery();

			//int bId = 0;
			int bTab = 0;
			String bTitle = null;
			String uId = null;
			String bContent = null;
			String bPlace = null;
			int bLimit = 0;
			String bStartMatch = null;
			String bEndMatch = null;
			String bImg1 = null;
			String bImg2 = null;
			String bImg3 = null;
			String bDate = null;
			int bHit = 0;

			if(rs.next()){
				bTab = rs.getInt("b_tab");
				bTitle = rs.getString("b_title");
				uId = rs.getString("u_id");
				bContent = rs.getString("b_content");
				bPlace = rs.getString("b_place");
				bLimit = rs.getInt("b_limit");
				bStartMatch = rs.getString("b_start_match");
				bEndMatch = rs.getString("b_end_match");
				bImg1 = rs.getString("b_img1");
				bImg2 = rs.getString("b_img2");
				bImg3 = rs.getString("b_img3");
				bDate = rs.getString("b_date");
				bHit = rs.getInt("b_hit");
				
				Board board = new Board(bId,bTab,bTitle, uId, bContent, bPlace, bLimit, bStartMatch, bEndMatch, bImg1, bImg2, bImg3, bDate, bHit);
				return board;
			}
		} catch (SQLException e) {
			System.out.println("Error : ����ȸ ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return null;

	}
	
	/**
	 * 5. �Խñ� ��ü(���)���� selectAllBoard
	 * @param bTab �ǹ�ȣ
	 * @return ������ list�� ��ȯ ���н� null�� ��ȯ
	 */
	public ArrayList<Board> selectAllBoard(int bTab){
		ArrayList<Board> list = new ArrayList<Board>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = factory.getConnection();
			String sql ="Select * from board where b_tab=?";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			int bId = 0;
			//int bTab = 0;
			String bTitle = null;
			String uId = null;
			String bContent = null;
			String bPlace = null;
			int bLimit = 0;
			String bStartMatch = null;
			String bEndMatch = null;
			String bImg1 = null;
			String bImg2 = null;
			String bImg3 = null;
			String bDate = null;
			int bHit = 0;

			while(rs.next()){
				bId = rs.getInt("b_id");
				bTab = rs.getInt("b_tab");
				bTitle = rs.getString("b_title");
				uId = rs.getString("u_id");
				bContent = rs.getString("b_content");
				bPlace = rs.getString("b_place");
				bLimit = rs.getInt("b_limit");
				bStartMatch = rs.getString("b_start_match");
				bEndMatch = rs.getString("b_end_match");
				bImg1 = rs.getString("b_img1");
				bImg2 = rs.getString("b_img2");
				bImg3 = rs.getString("b_img3");
				bDate = rs.getString("b_date");
				bHit = rs.getInt("b_hit");
				
				Board board = new Board(bId,bTab,bTitle, uId, bContent, bPlace, bLimit, bStartMatch, bEndMatch, bImg1, bImg2, bImg3, bDate, bHit);
				list.add(board);			
			}
			return list;
		} catch (SQLException e) {
			System.out.println("Error : ��ü��ȸ ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return null;
	}

	/**
	 * 6. �Խñ� �˻� selectBoard - ����, �۳���, ��� �����߿� ��ġ�ϴ� ���� �ִ��� �����������
	 * @param keyword �˻���
	 * @return ������ list�� ��ȯ, ���н� null
	 */
	public ArrayList<Board> selectBoard(String keyword){
		ArrayList<Board> list = new ArrayList<Board>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = factory.getConnection();
			String sql ="Select * from board where (b_title like ? and b_tab=1) or (b_content like ? and b_tab=1) or (b_place like ? and b_tab=1)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+keyword+"%");
			pstmt.setString(3, "%"+keyword+"%");
			rs = pstmt.executeQuery();

			int bId = 0;
			int bTab = 0;
			String bTitle = null;
			String uId = null;
			String bContent = null;
			String bPlace = null;
			int bLimit = 0;
			String bStartMatch = null;
			String bEndMatch = null;
			String bImg1 = null;
			String bImg2 = null;
			String bImg3 = null;
			String bDate = null;
			int bHit = 0;

			while(rs.next()){
				bId = rs.getInt("b_id");
				bTab = rs.getInt("b_tab");
				bTitle = rs.getString("b_title");
				uId = rs.getString("u_id");
				bContent = rs.getString("b_content");
				bPlace = rs.getString("b_place");
				bLimit = rs.getInt("b_limit");
				bStartMatch = rs.getString("b_start_match");
				bEndMatch = rs.getString("b_end_match");
				bImg1 = rs.getString("b_img1");
				bImg2 = rs.getString("b_img2");
				bImg3 = rs.getString("b_img3");
				bDate = rs.getString("b_date");
				bHit = rs.getInt("b_hit");
				
				Board board = new Board(bId,bTab,bTitle, uId, bContent, bPlace, bLimit, bStartMatch, bEndMatch, bImg1, bImg2, bImg3, bDate, bHit);
				list.add(board);			
			}
			return list;
		} catch (SQLException e) {
			System.out.println("Error : ��ü��ȸ ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return null;
	}
	
	/**
	 * 7. ��ȸ�� topN ��ȸ selectBoardTopNHit - 3�� n���� �ٲٰ� ������ �޴´�
	 * @param keyword
	 * @return
	 */
	public ArrayList<Board> selectBoardTopNHit(int num){
		ArrayList<Board> list = new ArrayList<Board>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = factory.getConnection();
			String sql ="select * from (select * from board order by b_hit desc) where rownum <=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			int bId = 0;
			int bTab = 0;
			String bTitle = null;
			String uId = null;
			String bContent = null;
			String bPlace = null;
			int bLimit = 0;
			String bStartMatch = null;
			String bEndMatch = null;
			String bImg1 = null;
			String bImg2 = null;
			String bImg3 = null;
			String bDate = null;
			int bHit = 0;

			while(rs.next()){
				bId = rs.getInt("b_id");
				bTab = rs.getInt("b_tab");
				bTitle = rs.getString("b_title");
				uId = rs.getString("u_id");
				bContent = rs.getString("b_content");
				bPlace = rs.getString("b_place");
				bLimit = rs.getInt("b_limit");
				bStartMatch = rs.getString("b_start_match");
				bEndMatch = rs.getString("b_end_match");
				bImg1 = rs.getString("b_img1");
				bImg2 = rs.getString("b_img2");
				bImg3 = rs.getString("b_img3");
				bDate = rs.getString("b_date");
				bHit = rs.getInt("b_hit");
				
				Board board = new Board(bId,bTab,bTitle, uId, bContent, bPlace, bLimit, bStartMatch, bEndMatch, bImg1, bImg2, bImg3, bDate, bHit);
				list.add(board);			
			}
			return list;
		} catch (SQLException e) {
			System.out.println("Error : ���� 3�� �˻� ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return null;
	}
	
	/**
	 * 8. �����ڰ� �Խñ� ���� deleteBoard
	 * @param bId �Խñ� ��ȣ
	 * @return ������ 0���� ū���� ��ȯ ���н� 0�� ��ȯ
	 */
	public int deleteBoard(int bId){
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = factory.getConnection();
			String sql ="Delete from board where b_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bId);
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
	 * 9. ��õ���� �����ֱ� selectBoardRecommend
	 * @param tab �ǹ�ȣ
	 * @return ������ list�� ��ȯ, ���н� null�� ��ȯ
	 */
	public ArrayList<Board> selectBoardRecommend(int tab){
		ArrayList<Board> list = new ArrayList<Board>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = factory.getConnection();
			String sql ="select * from (select * from board order by b_hit desc) where b_tab=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, tab);
			rs = pstmt.executeQuery();

			int bId = 0;
			int bTab = 0;
			String bTitle = null;
			String uId = null;
			String bContent = null;
			String bPlace = null;
			int bLimit = 0;
			String bStartMatch = null;
			String bEndMatch = null;
			String bImg1 = null;
			String bImg2 = null;
			String bImg3 = null;
			String bDate = null;
			int bHit = 0;

			while(rs.next()){
				bId = rs.getInt("b_id");
				bTab = rs.getInt("b_tab");
				bTitle = rs.getString("b_title");
				uId = rs.getString("u_id");
				bContent = rs.getString("b_content");
				bPlace = rs.getString("b_place");
				bLimit = rs.getInt("b_limit");
				bStartMatch = rs.getString("b_start_match");
				bEndMatch = rs.getString("b_end_match");
				bImg1 = rs.getString("b_img1");
				bImg2 = rs.getString("b_img2");
				bImg3 = rs.getString("b_img3");
				bDate = rs.getString("b_date");
				bHit = rs.getInt("b_hit");
				
				Board board = new Board(bId,bTab,bTitle, uId, bContent, bPlace, bLimit, bStartMatch, bEndMatch, bImg1, bImg2, bImg3, bDate, bHit);
				list.add(board);			
			}
			return list;
		} catch (SQLException e) {
			System.out.println("Error : ��õ �˻� ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return null;
	}
	
	
	public ArrayList<Board> selectMyBoard(String uId){
		ArrayList<Board> list = new ArrayList<Board>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = factory.getConnection();
			String sql ="Select * from board where u_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uId);
			rs = pstmt.executeQuery();

			int bId = 0;
			int bTab = 0;
			String bTitle = null;
			//String uId = null;
			String bContent = null;
			String bPlace = null;
			int bLimit = 0;
			String bStartMatch = null;
			String bEndMatch = null;
			String bImg1 = null;
			String bImg2 = null;
			String bImg3 = null;
			String bDate = null;
			int bHit = 0;

			while(rs.next()){
				bId = rs.getInt("b_id");
				bTab = rs.getInt("b_tab");
				bTitle = rs.getString("b_title");
				uId = rs.getString("u_id");
				bContent = rs.getString("b_content");
				bPlace = rs.getString("b_place");
				bLimit = rs.getInt("b_limit");
				bStartMatch = rs.getString("b_start_match");
				bEndMatch = rs.getString("b_end_match");
				bImg1 = rs.getString("b_img1");
				bImg2 = rs.getString("b_img2");
				bImg3 = rs.getString("b_img3");
				bDate = rs.getString("b_date");
				bHit = rs.getInt("b_hit");
				
				Board board = new Board(bId,bTab,bTitle, uId, bContent, bPlace, bLimit, bStartMatch, bEndMatch, bImg1, bImg2, bImg3, bDate, bHit);
				list.add(board);			
			}
			return list;
		} catch (SQLException e) {
			System.out.println("Error : �� �Խñ� �˻� ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return null;
	}
}
