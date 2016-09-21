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
 * @author 김은지
 *
 */
public class BoardDao {
	private FactoryDao factory = FactoryDao.getInstance();

	/**
	 * 기본 생성자 : jdbc driver 로딩 로직
	 */
	private BoardDao(){}//기본생성자를 명시적으로 안만들면 컴파일시에 새로 만들게 되면서 public으로 만들어져서
	//singleTon pattern에 위배 되므로 직접 private으로 만들어야한다.

	private static BoardDao instance = new BoardDao();

	public static BoardDao getInstance(){
		return instance;
	}

	/**
	 * 1. 게시판 등록하는 메서드
	 * @param board 게시글
	 * @return 성공시 0보다 큰수, 실패시 0을 반환
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
			System.out.println("Error : 게시글 등록 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;		
	}
	
	/**
	 * 2. 내 글 수정 updateMyBoard 
	 * @param bId 게시글 번호
	 * @param bTitle 게시글 제목
	 * @param uId 작성자
	 * @param bContent 내용
	 * @param bPlace 장소
	 * @param bLimit 인원제한
	 * @param bStartMatch 시작시간
	 * @param bEndMatch 끝시간
	 * @param bImg1 이미지1
	 * @param bImg2 이미지2
	 * @param bImg3 이미지3
	 * @return 성공시 0보다 큰수 그렇지 않으면 0을 반환
	 */
	public int updateMyBoard(int bId, String bTitle, String uId, String bContent, String bPlace, int bLimit, String bStartMatch,String bEndMatch, String bImg1, String bImg2, String bImg3){
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = factory.getConnection();
			//1.sql전요통로 개설
			String sql ="update board set b_title=?, b_content=?, b_place=?,b_limit=?, b_start_match=to_date(?, 'yyyy-mm-dd HH24:MI:SS'), b_end_match=to_date(?, 'yyyy-mm-dd HH24:MI:SS'), b_Img1=?, b_img2=?, b_img3=? where b_id=? and u_id=?";
			pstmt = conn.prepareStatement(sql);
			//2. ?매핑되도록 전달값 설정
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
			System.out.println("Error : 일부 업데이트 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;		
	}

	/**
	 * 3. 내 글 삭제 deleteMyBoard
	 * @param bId 게시글 번호
	 * @param uId 회원아이디
	 * @return 성공시 0보다 큰수, 실패시 0을 반환
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
			System.out.println("Error : 삭제 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;		
	}
	
	/**
	 * 4. 게시글 상세보기 selectOneBoard
	 * @param bId 게시글번호
	 * @return 성공시 board객체를 반환, 실패시 0을 반
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
			System.out.println("Error : 상세조회 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return null;

	}
	
	/**
	 * 5. 게시글 전체(목록)보기 selectAllBoard
	 * @param bTab 탭번호
	 * @return 성공시 list를 반환 실패시 null을 반환
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
			System.out.println("Error : 전체조회 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return null;
	}

	/**
	 * 6. 게시글 검색 selectBoard - 제목, 글내용, 장소 세개중에 일치하는 것이 있는지 다중쿼리사용
	 * @param keyword 검색어
	 * @return 성공시 list를 반환, 실패시 null
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
			System.out.println("Error : 전체조회 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return null;
	}
	
	/**
	 * 7. 조회수 topN 조회 selectBoardTopNHit - 3을 n으로 바꾸고 변수로 받는다
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
			System.out.println("Error : 상위 3개 검색 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return null;
	}
	
	/**
	 * 8. 관리자가 게시글 삭제 deleteBoard
	 * @param bId 게시글 번호
	 * @return 성공시 0보다 큰수를 반환 실패시 0을 반환
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
			System.out.println("Error : 삭제 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;		
	}
	
	/**
	 * 9. 추천모임 보여주기 selectBoardRecommend
	 * @param tab 탭번호
	 * @return 성공시 list를 반환, 실패시 null을 반환
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
			System.out.println("Error : 추천 검색 오류");
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
			System.out.println("Error : 내 게시글 검색 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return null;
	}
}
