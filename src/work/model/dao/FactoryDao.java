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
 * --Ư�� ���� ����� �����ϴ� ���� Ŭ����
 * 
 * ##FactoryDao
 * --DAO Ŭ������ ������ : Connection ����, �ڿ� ����
 * --Singleton Patter ���� ����
 * 
 * ##Dato������
 * 0.jdbc driver �ε� : ������
 * 1.Connection ����
 * 2.�ڿ�����
 * 
 * ##dbserver ���� property ���� �ܺ� �ڿ����� ���
 * --java.io.*
 * --java.util.ResourceBundle : ��Ģ �ؼ�, ��� ��
 * --�ΰ��� ����� �� ���� resourceBundle�� ����ϱ�
 * 
 * ##ResourceBundle ����Ģ
 * 1.�ܺ��ڿ����� ��ġ : classpath(output dir) ���� ��� ���
 * 2.������ Ȯ���� : .properties
 * 3.���� property ��� : key=value
 * 4.���ǻ��� :  key �� value = ��ȣ ���̿� ���� �Ұ�
 * 
 * getString(key):String
 * 
 * 
 * @author ������
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
			System.out.println("DataSource �̸� �˻� ���� �߻�");
			e.printStackTrace();
		}
	}
	
	public static FactoryDao getInstance(){
		return instance;
	}
	
	//���� ��� : Connection ���� ��ȯ
	public Connection getConnection(){
		//Connection Pool (DataSource)���� ���ᰴü �ϳ� �����ͼ� ��ȯ
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			System.out.println("DataSource ���ݰ�ü �������� ���� �߻�");
			e.printStackTrace();
		}
		return null;
	}
	
	//������ : �ڿ�����
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
			System.out.println("Error : �ڿ����� ����");
			e.printStackTrace();
		}
	}
}
