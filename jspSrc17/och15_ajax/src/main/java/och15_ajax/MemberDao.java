package och15_ajax;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDao {
	//Singleton --> Resource 절감
	private static MemberDao instance;
	//Default
	private MemberDao() {
		
	}
	
	public static MemberDao getInstance() {
		if (instance == null) {
			instance = new MemberDao();
		}
		return instance;
	}
	
	//DBCP
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public int confirm(String id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 1;
		String sql = "SELECT id FROM member1 WHERE id=?";
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			//select가 아닌 나머지는 executeUpdate!!
			rs = pstmt.executeQuery();
			
//			if (rs.next()) {
//				String dbId = rs.getString(1);
//				if (dbId.equals(id)) result = 1;
//				else result = 0;
//			}
			
			if (rs.next()) result = 1;
			else result = 0;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)		rs.close();
			if (pstmt != null)	pstmt.close();
			if (conn != null)	conn.close();
		}
		return result;
	}
}
