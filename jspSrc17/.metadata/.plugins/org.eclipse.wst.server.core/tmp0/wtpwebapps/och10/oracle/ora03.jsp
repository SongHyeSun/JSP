<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String deptno = request.getParameter("deptno");
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	String sql = "select * from dept where deptno="+deptno;
	Class.forName(driver);
	Connection conn = DriverManager.getConnection(url, "scott", "tiger");
	Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery(sql);
	System.out.println("sql->"+sql);
	
	if (rs.next()) {
		String dname = rs.getString("dname");	//rs.getString(2);
		String loc = rs.getString(3);			//숫자는 조회되는 컬럼 순서
		out.println("부서코드 : "+deptno+"<p>");
		out.println("부서명 : "+dname+"<p>");
		out.println("근무지 : "+loc+"<p>");
		//1. request에 저장 --> deptno, dname, loc
		request.setAttribute("deptno", deptno);
		request.setAttribute("dname", dname);
		request.setAttribute("loc", loc);
	} else out.println("그게 무슨 부서야 없는데");
	rs.close();
	stmt.close();
	conn.close();

	//2. RequestDispatcher --> ora03Result.jsp Page 이동
	RequestDispatcher rd = request.getRequestDispatcher("ora03Result.jsp");
	rd.forward(request, response);
	//3. ora03Result.jsp 안에서 결과 만들어내기
	// 부서정보
	// 부서코드 : 20
	// 부서명 : RESEARCH
	// 근무지 : DALLAS
%>
</body>
</html>