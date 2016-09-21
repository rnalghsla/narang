/**
 * 
 */
package work.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import work.model.dto.Matching;

/**
 * @author 김은지
 *
 */
public class MatchingDao {
	private FactoryDao factory = FactoryDao.getInstance();

	/**
	 * 기본 생성자 : jdbc driver 로딩 로직
	 */
	private MatchingDao(){}//기본생성자를 명시적으로 안만들면 컴파일시에 새로 만들게 되면서 public으로 만들어져서
	//singleTon pattern에 위배 되므로 직접 private으로 만들어야한다.

	private static MatchingDao instance = new MatchingDao();

	public static MatchingDao getInstance(){
		return instance;
	}
	
	/**
	 * 1. 매칭생성 insertMatching => board가 생성됐을때 만들어져야하나 합칠까?
	 * @param matching 매칭 객체
	 * @return 성공시 0보다 큰수 실패시 0을 반환
	 */
	public int insertMatching(Matching matching){
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			//int mId = matching.getmId();
			int bId = matching.getbId();
			//String mUser1 = matching.getmUser1();
			//String mUser2 = matching.getmUser2();
			//String mUser3 = matching.getmUser3();
			//String mUser4 = matching.getmUser4();
			//String mUser5 = matching.getmUser5();
			String bStartMatch = matching.getbStartMatch();
			String bEndMatch = matching.getbEndMatch();
			
			try {
				conn = factory.getConnection();
				String sql ="Insert into matching values(match_count.nextval, ?, null, null, null, null, null, ?, ?)";
				pstmt = conn.prepareStatement(sql);
				//pstmt.setString(1, dBoardNum);
				pstmt.setInt(1, bId);
				pstmt.setString(2, bStartMatch);
				pstmt.setString(3, bEndMatch);
				
				return pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println("Error : 요청 생성 오류");
				e.printStackTrace();
			} finally {
				factory.close(conn, pstmt);
			}
			return 0;		
	}
	
	/**
	 * 2. 매칭등록 updateMatchingSetId - 들어오는 번호에 따라서 다른 쿼리문에 넣어준다.
	 * @param mId 매칭번호
	 * @param num 몇번째칸에 값이 들어오는지 알려주는 index번호(0,1,2,3,4)
	 * @param mUser 들어올 사용자 아이디 
	 * @return 성공시 0보다 큰수 실패시 0을 반환
	 */
	public int updateMatchingSetId(int mId, int num, String mUser){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql =  null;
		
		try {
			conn = factory.getConnection();
			
			if(num==0){
				sql ="Update matching set M_user1=? where m_id=?";
			} else if(num==1){
				sql ="Update matching set M_user2=? where m_id=?";
			} else if(num==2){
				sql ="Update matching set M_user3=? where m_id=?";
			} else if(num==3){
				sql ="Update matching set M_user4=? where m_id=?";
			} else if(num==4){
				sql ="Update matching set M_user5=? where m_id=?";
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mUser);
			pstmt.setInt(2, mId);
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error : 요청 등록 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;		
	}
	
	/**
	 * 3. 매칭삭제 updateMatchingRemoveId - 들어오는 번호에 따라서 다른 쿼리문에 넣어준다.
	 * @param mId 매칭번호
	 * @param num 몇번째칸에 값이 들어오는지 알려주는 index번호(0,1,2,3,4)
	 * @param mUser 삭제될 사용자
	 * @return 성공시 0보다 큰수, 실패시 0을 반환
	 */
	public int updateMatchingRemoveId(int mId, int num, String mUser){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql =  null;
		
		try {
			conn = factory.getConnection();
			if(num==0){
				sql ="Update matching set M_user1=null where m_id=? and m_user1=?";
			} else if(num==1){
				sql ="Update matching set M_user2=null where m_id=? and m_user1=?";
			} else if(num==2){
				sql ="Update matching set M_user3=null where m_id=? and m_user1=?";
			} else if(num==3){
				sql ="Update matching set M_user4=null where m_id=? and m_user1=?";
			} else if(num==4){
				sql ="Update matching set M_user5=null where m_id=? and m_user1=?";
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mId);
			pstmt.setString(2, mUser);
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
	 * 4. 중복모임 선택 불가 selectMatchingCompareDate -이값이 0보다 크면 존재한다는 뜻, 0보다 작으면 중복되지않는다는뜻
	 * @param bId 중복선택 체크하고자하는 게시판번호
	 * @param uId 내 아이디
	 * @return 성공시 0보다 큰수, 실패시 0을 반환
	 */
	public ArrayList<Matching> selectMatchingCompareDate(int bId, String uId){
		ArrayList<Matching> list = new ArrayList<Matching>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = factory.getConnection();
			String sql ="select distinct * from (select b_id, b_end_match from board where b_id=?) b, matching m where m.b_start_match-b.b_end_match < 0 and ? in (m.m_user1, m.m_user2, m.m_user3, m.m_user4, m.m_user5)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bId);
			pstmt.setString(2, uId);
			rs = pstmt.executeQuery();

			int mId = 0;
			//String bId = null;
			String mUser1 = null;
			String mUser2 = null;
			String mUser3 = null;
			String mUser4 = null;
			String mUser5 = null;
			String bStartMatch = null;
			String bEndMatch = null;
			String dbEndMatch = null;
			

			while(rs.next()){
				mId = rs.getInt("m_id");
				bId = rs.getInt("b_id");
				mUser1 = rs.getString("m_user1");
				mUser2 = rs.getString("m_user2");
				mUser3 = rs.getString("m_user3");
				mUser4 = rs.getString("m_user4");
				mUser5 = rs.getString("m_user5");
				bStartMatch = rs.getString("m.b_start_match");
				bEndMatch = rs.getString("m.b_end_match");
				dbEndMatch = rs.getString("b.b_end_match");
				
				Matching board = new Matching(mId, bId, mUser1,mUser2, mUser3, mUser4, mUser5, bStartMatch, bEndMatch);
				list.add(board);			
			}
			return list;//return rs.next();
		} catch (SQLException e) {
			System.out.println("Error : 중복조회 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return null;

	}
	
	/**
	 * 5. 내가 참가한 매칭 보기 selectMyMatching
	 * @param uId 내아이디
	 * @return list를 반환 실패시 null을 반환
	 */
	public ArrayList<Matching> selectMyMatching(String uId){
		ArrayList<Matching> list = new ArrayList<Matching>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = factory.getConnection();
			String sql ="select * from matching where ? in (m_user1, m_user2, m_user3, m_user4, m_user5)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uId);
			rs = pstmt.executeQuery();

			int mId = 0;
			int bId = 0;
			String mUser1 = null;
			String mUser2 = null;
			String mUser3 = null;
			String mUser4 = null;
			String mUser5 = null;
			String bStartMatch = null;
			String bEndMatch = null;

			while(rs.next()){
				mId = rs.getInt("m_id");
				bId = rs.getInt("b_id");
				mUser1 = rs.getString("m_user1");
				mUser2 = rs.getString("m_user2");
				mUser3 = rs.getString("m_user3");
				mUser4 = rs.getString("m_user4");
				mUser5 = rs.getString("m_user5");
				bStartMatch = rs.getString("m.b_start_match");
				bEndMatch = rs.getString("m.b_end_match");
				
				Matching board = new Matching(mId, bId, mUser1,mUser2, mUser3, mUser4, mUser5, bStartMatch, bEndMatch);
				list.add(board);			
			}
			return list;//return rs.next();
		} catch (SQLException e) {
			System.out.println("Error : 중복조회 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return null;
	}
	
	/**
	 * 6. 가득찬 모임은 음영처리 selectFullMatching
	 * @return 성공시 정수형배열을 반환. 게시글 번호를 반환. 그것을 음영처리하면 됨
	 */
	public int[] selectFullMatching(){
		int[] bidArray = {0};
		int i=0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = factory.getConnection();
			String sql ="select b_id from matching where m_user1 is not null and m_user2 is not null and m_user3 is not null and m_user4 is not null and m_user5 is not null";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			int bId = 0;

			while(rs.next()){
				bId = rs.getInt("b_id");
				bidArray[i++]=bId;
			}
			return bidArray;
		} catch (SQLException e) {
			System.out.println("Error : 꽉찬 모임 조회 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return null;
	}
	
	/**
	 * 7. 시간이 지나서 종료된 글 음영처리 selectOldMatching
	 * @return 성공시 정수형 배열. 실패시 null이 반환
	 */
	public int[] selectOldMatching(){
		int[] bidArray = {0};
		int i=0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = factory.getConnection();
			String sql ="select b_id from matching where b_end_match - to_date(sysdate, 'yyyy-MM-dd HH24:Mi:ss') <0";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			int bId = 0;

			while(rs.next()){
				bId = rs.getInt("b_id");
				bidArray[i++]=bId;
			}
			return bidArray;
		} catch (SQLException e) {
			System.out.println("Error : 마감된 모임 조회 오류");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return null;
	}
}
