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
 * @author ������
 *
 */
public class MatchingDao {
	private FactoryDao factory = FactoryDao.getInstance();

	/**
	 * �⺻ ������ : jdbc driver �ε� ����
	 */
	private MatchingDao(){}//�⺻�����ڸ� ��������� �ȸ���� �����Ͻÿ� ���� ����� �Ǹ鼭 public���� ���������
	//singleTon pattern�� ���� �ǹǷ� ���� private���� �������Ѵ�.

	private static MatchingDao instance = new MatchingDao();

	public static MatchingDao getInstance(){
		return instance;
	}
	
	/**
	 * 1. ��Ī���� insertMatching => board�� ���������� ����������ϳ� ��ĥ��?
	 * @param matching ��Ī ��ü
	 * @return ������ 0���� ū�� ���н� 0�� ��ȯ
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
				System.out.println("Error : ��û ���� ����");
				e.printStackTrace();
			} finally {
				factory.close(conn, pstmt);
			}
			return 0;		
	}
	
	/**
	 * 2. ��Ī��� updateMatchingSetId - ������ ��ȣ�� ���� �ٸ� �������� �־��ش�.
	 * @param mId ��Ī��ȣ
	 * @param num ���°ĭ�� ���� �������� �˷��ִ� index��ȣ(0,1,2,3,4)
	 * @param mUser ���� ����� ���̵� 
	 * @return ������ 0���� ū�� ���н� 0�� ��ȯ
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
			System.out.println("Error : ��û ��� ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;		
	}
	
	/**
	 * 3. ��Ī���� updateMatchingRemoveId - ������ ��ȣ�� ���� �ٸ� �������� �־��ش�.
	 * @param mId ��Ī��ȣ
	 * @param num ���°ĭ�� ���� �������� �˷��ִ� index��ȣ(0,1,2,3,4)
	 * @param mUser ������ �����
	 * @return ������ 0���� ū��, ���н� 0�� ��ȯ
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
			System.out.println("Error : ���� ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt);
		}
		return 0;		
	}
	
	/**
	 * 4. �ߺ����� ���� �Ұ� selectMatchingCompareDate -�̰��� 0���� ũ�� �����Ѵٴ� ��, 0���� ������ �ߺ������ʴ´ٴ¶�
	 * @param bId �ߺ����� üũ�ϰ����ϴ� �Խ��ǹ�ȣ
	 * @param uId �� ���̵�
	 * @return ������ 0���� ū��, ���н� 0�� ��ȯ
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
			System.out.println("Error : �ߺ���ȸ ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return null;

	}
	
	/**
	 * 5. ���� ������ ��Ī ���� selectMyMatching
	 * @param uId �����̵�
	 * @return list�� ��ȯ ���н� null�� ��ȯ
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
			System.out.println("Error : �ߺ���ȸ ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return null;
	}
	
	/**
	 * 6. ������ ������ ����ó�� selectFullMatching
	 * @return ������ �������迭�� ��ȯ. �Խñ� ��ȣ�� ��ȯ. �װ��� ����ó���ϸ� ��
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
			System.out.println("Error : ���� ���� ��ȸ ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return null;
	}
	
	/**
	 * 7. �ð��� ������ ����� �� ����ó�� selectOldMatching
	 * @return ������ ������ �迭. ���н� null�� ��ȯ
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
			System.out.println("Error : ������ ���� ��ȸ ����");
			e.printStackTrace();
		} finally {
			factory.close(conn, pstmt, rs);
		}
		return null;
	}
}
