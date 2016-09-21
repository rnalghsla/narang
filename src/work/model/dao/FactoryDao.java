package work.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * ##Factory Pattern
 * --특정 공통 기능을 제공하는 공장 클래스
 * 
 * ##FactoryDao
 * --DAO 클래스의 공통기능 : Connection 생성, 자원 해제
 * --Singleton Patter 적용 설계
 * 
 * ##Dato공통기능
 * 0.jdbc driver 로딩 : 생성자
 * 1.Connection 생성
 * 2.자원해제
 * 
 * ##dbserver 관련 property 파일 외부 자원파일 사용
 * --java.io.*
 * --java.util.ResourceBundle : 규칙 준수, 사용 편리
 * --두가지 방법중 더 편한 resourceBundle을 사용하기
 * 
 * ##ResourceBundle 사용규칙
 * 1.외부자원파일 위치 : classpath(output dir) 기준 상대 경로
 * 2.파일의 확장자 : .properties
 * 3.파일 property 기술 : key=value
 * 4.주의사항 :  key 와 value = 기호 사이에 공백 불가
 * 
 * getString(key):String
 * 
 * 
 * @author 김은지
 *
 */
public class FactoryDao {
	//Connection Pool : javax.sql.DataSource
	//Conded name = java:comp/env/
	//Resource name = "jdbc/Oracle" : context.xml
	private String dsName = "java:comp/env/jdbc/Oracle";
	private DataSource ds;
	
	private static FactoryDao instance = new FactoryDao();
	
	private FactoryDao(){
		try {
			ds = (DataSource)new InitialContext().lookup(dsName);
		} catch (NamingException e) {
			System.out.println("DataSource 이름 검색 오류 발생");
			e.printStackTrace();
		}
	}
	
	public static FactoryDao getInstance(){
		return instance;
	}
	
	//공통 기능 : Connection 생성 반환
	public Connection getConnection(){
		//Connection Pool (DataSource)에게 연결객체 하나 가져와서 반환
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			System.out.println("DataSource 연격객체 가져오기 오류 발생");
			e.printStackTrace();
		}
		return null;
	}
	
	//공통기능 : 자원해제
	public void close(Connection conn, Statement pstmt){
		close(conn, pstmt, null);
	}
	
	public void close(Connection conn, Statement pstmt, ResultSet rs){
		try {
			if(rs != null) {
				rs.close();
			}
			if(pstmt!=null){
				pstmt.close();
			}
			if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("Error : 자원해제 오류");
			e.printStackTrace();
		}
	}
}
