package och12;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;

//SingleTon + DBCP
public class MemberDao {
	private static MemberDao instance;
	private MemberDao() {
	}
	
	//singleton
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
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
	public int check (String id, String passwd) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "SELECT passwd FROM member2 WHERE id=?";
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			//id OK
			if(rs.next()) {
				String dbPassword = rs.getString(1);
				if (dbPassword.equals(passwd)) result = 1;
				else result = 0;
			} else result = -1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return result;
	}
	
	public int insert(Member member) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			conn = getConnection();
			
			String sql = "INSERT INTO member2 VALUES (?,?,?,?,?,sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getName());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getAddress());
			pstmt.setString(5, member.getTel());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (pstmt != null)	pstmt.close();
			if (conn != null)	conn.close();
		}
		return result;
	}
	
	public int confirm(String id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 1;
		String sql = "SELECT id FROM member2 WHERE id=?";
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) result = 1;
			else		   result = 0;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)	rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return result;
	}
	
	public List<Member> list() throws SQLException {
		//return형이 List로 선언되어 있다면 반드시 아래 처럼 List를 선언해주어야 한다.
		List<Member> list = new ArrayList<Member>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM member2";
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				do {
					Member m = new Member();
					m.setId(rs.getString(1));
					m.setPasswd(rs.getString(2));
					m.setName(rs.getString(3));
					m.setAddress(rs.getString(4));
					m.setTel(rs.getString(5));
					m.setReg_date(rs.getDate(6));
					list.add(m);
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return list;
	}
	
	public Member select(String id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM member2 WHERE id=?";
		ResultSet rs = null;
		Member member = new Member();
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				member.setId(rs.getString(1));
				member.setPasswd(rs.getString(2));
				member.setName(rs.getString(3));
				member.setAddress(rs.getString(4));
				member.setTel(rs.getString(5));
				member.setReg_date(rs.getDate(6));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return member;
	}
	
	public int update(Member member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE member2 SET passwd=?, name=?, address=?, tel=? WHERE id=?";
		int result = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getPasswd());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getAddress());
			pstmt.setString(4, member.getTel());
			pstmt.setString(5, member.getId());
			
			result = pstmt.executeUpdate();
			
			if (result > 0)  result = 1;
			else			 result = 0;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	public int delete(String id, String passwd) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		result = check(id,passwd);
		
		if (result !=1) return result;
		String sql = "DELETE member2 WHERE id=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (pstmt != null)	pstmt.close();
			if (conn != null)	conn.close();
		}
		
//		String sql = "DELETE member2 WHERE id=? AND passwd=?";
//		try {
//			conn = getConnection();
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, id);
//			pstmt.setString(2, passwd);
//			result = pstmt.executeUpdate();
//			System.out.println("result1->"+result);
//			
//			Member member = new Member();
//			String dbPasswd = member.getPasswd();
//			String dbId = member.getId();
//			
//			if (dbId.equals(id)) {
//				if (dbPasswd.equals(passwd)) {
//					result = 1;
//				} else	result = 0;
//			} else		result = -1;
//			System.out.println("result2->"+result);
//			
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		} finally {
//			if (pstmt != null)	pstmt.close();
//			if (conn != null)	conn.close();
//		}
		return result;
	}
}
