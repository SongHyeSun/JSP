package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

// Singleton + DBCP 만들기
public class BoardDao {
	private static BoardDao instance;
	//반드시 기본생성자를 private으로 만들어주어야 한다.
	private BoardDao() {
	}
	
	public static BoardDao getInstance() {
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
	
	public int getTotalCnt() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = "SELECT COUNT(*) FROM board";
		
		
		try {
			conn = getConnection();
			stmt  = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		}
		return result;
	}
	
	public List<Board> boardList(int startRow, int endRow) throws SQLException {
		List<Board> boardList = new ArrayList<Board>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM ( SELECT ROWNUM rn, a.*"
				+ "FROM ( SELECT * FROM board ORDER BY ref DESC, re_step) a )"
				+ "WHERE rn BETWEEN ? AND ?";
		System.out.println("boardList sql=>" + sql);
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Board board = new Board();
				board.setNum(rs.getInt("num"));
				board.setWriter(rs.getString("writer"));
				board.setSubject(rs.getString("subject"));
				board.setEmail(rs.getString("email"));
				board.setReadcount(rs.getInt("readcount"));
				board.setIp(rs.getString("ip"));
				board.setRef(rs.getInt("ref"));
				board.setRe_level(rs.getInt("re_level"));
				board.setRe_step(rs.getInt("re_step"));
				board.setReg_date(rs.getDate("reg_date"));
				boardList.add(board);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return boardList;
	}
	
	public Board select(int num) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM board WHERE num="+num;
		Board board = new Board();
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				board.setNum(rs.getInt("num"));
				board.setWriter(rs.getString("writer"));
				board.setSubject(rs.getString("subject"));
				board.setEmail(rs.getString("email"));
				board.setReadcount(rs.getInt("readcount"));
				board.setIp(rs.getString("ip"));
				board.setContent(rs.getString("content"));
				board.setRef(rs.getInt("ref"));
				board.setRe_level(rs.getInt("re_level"));
				board.setRe_step(rs.getInt("re_step"));
				board.setReg_date(rs.getDate("reg_date"));
			}
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if ( rs != null) rs.close();
			if ( stmt != null) stmt.close();
			if ( conn != null) conn.close();
		}
		return board;
	}
	
	public void readCount(int num) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE board SET readcount=readcount+1 WHERE num=?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (pstmt !=null)	pstmt.close();
			if (conn !=null)	conn.close();
		}
	}
	
	public int update(Board board) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE board SET subject=?, writer=?, email=?, passwd=?, content=? WHERE num=?";
		int result = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, board.getSubject());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getEmail());
			pstmt.setString(4, board.getPasswd());
			pstmt.setString(5, board.getContent());
			pstmt.setInt(6, board.getNum());
			
			result = pstmt.executeUpdate();
			
			if ( result > 0) result = 1;
			else			result = 0;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return result;
	}
	
	public int insert(Board board) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		int num = board.getNum();
		ResultSet rs = null;
		//신규 글
		//sql1을 없앨 수 있는 2가지 방법
		//1. 모듈화 시켜준다.  2. sql문에 한번에 넣어준다.
		String sql1 = "SELECT nvl(MAX(num),0) FROM board";
		//신규 글 + 댓글 공용
		String sql3 = "INSERT INTO board VALUES(?,?,?,?,?,?,?,?,?,?,?,sysdate)";
		//홍해의 기적-> 댓글일 때
		String sql2 = "UPDATE board SET re_step = re_step+1 WHERE ref=? AND re_step > ?";
		 
		try {
			//sql1
			conn = getConnection();
			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			rs.next();
			//key인 num 1씩 증가, mysql auto_increment 또는 oracle sequence
			//sequence를 사용 : values(시퀀스명(board_seq).nextval,?,?...)
			
			//단일 key일 경우는 sequence를 따주던지, max를 불러와준다.
			int number = rs.getInt(1)+1;
			rs.close();
			pstmt.close();
			
			//sql2 -> 순수 댓글을 달기 위한 것
			if (num != 0) {
				System.out.println("BoardDAO insert 댓글 sql2->"+sql2);
				System.out.println("BoardDAO insert 댓글 board.getRef()->"+board.getRef());
				System.out.println("BoardDAO insert 댓글 board.getRe_step()->"+board.getRe_step());
				
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, board.getRef());
				pstmt.setInt(2, board.getRe_step());
				pstmt.executeUpdate();
				pstmt.close();
				
				//댓글 관련 정보
				board.setRe_step(board.getRe_step()+1);
				board.setRe_level(board.getRe_level()+1);
			}
			
			System.out.println("BoardDAO insert num->"+num);
			System.out.println("BoardDAO insert number->"+number);
			
			//sql3
			if (num == 0) board.setRef(number);
			pstmt = conn.prepareStatement(sql3);
			pstmt.setInt(1, number);
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getSubject());
			pstmt.setString(4, board.getContent());
			pstmt.setString(5, board.getEmail());
			pstmt.setInt(6, board.getReadcount());
			pstmt.setString(7, board.getPasswd());
			pstmt.setInt(8, board.getRef());
			pstmt.setInt(9, board.getRe_step());
			pstmt.setInt(10, board.getRe_level());
			pstmt.setString(11, board.getIp());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)		rs.close();
			if (pstmt != null)	pstmt.close();
			if (conn != null)	conn.close();
		}
		return result;
	} 
	
	public int delete(int num, String passwd) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		ResultSet rs = null;
		String sql1 = "SELECT passwd FROM board WHERE num=?";
		String sql = "DELETE FROM board WHERE num=?";
		
		try {
			String dbPasswd = "";
			conn = getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dbPasswd = rs.getString(1);
				if(dbPasswd.equals(passwd)) {
					rs.close();
					pstmt.close();
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, num);
					result = pstmt.executeUpdate();
				} else result=0;
			} else result = -1;
		} catch (Exception e) {
			System.out.println("delete ->"+ e.getMessage());
		} finally {
			if ( pstmt != null) pstmt.close();
			if ( conn != null) conn.close();
		}
		return result;
	}
}
