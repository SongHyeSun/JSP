package dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

// Singleton + DBCP 만들기
public class BoardDao {
	private static BoardDao instance;
	//반드시 기본생성자를 private으로 만들어주어야 한다.
	private BoardDao() {
	}
	
	private static BoardDao getInstance() {
		if (instance == null) {
			instance = new BoardDao();
		}
		return instance;
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		
		try {
			Context ctx = new InitialContext();
			//ds에 Naming이 OracleDB랑 맞는 것들을 넣어준다는 의미!!
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
}
