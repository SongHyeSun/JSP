<%@page import="och10.Professor"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
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
	Context ctx = new InitialContext();
	DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/MySql");
	Connection conn = ds.getConnection();
	
	String profno = request.getParameter("pno");
	String sql = "SELECT profno, name, sal, hiredate FROM professor WHERE profno="+profno;
	
	Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery(sql);
	System.out.println("Professor list->"+sql);
	
	Professor p = new Professor();
	if (rs.next()) {
		p.setProfno(rs.getInt(1));
		p.setName(rs.getString(2));
		p.setSal(rs.getInt(3));
		p.setHiredate(rs.getDate(4));
		request.setAttribute("professor", p);
	}
	
	rs.close();
	stmt.close();
	conn.close();
	
	RequestDispatcher rd = request.getRequestDispatcher("myResult.jsp");
	rd.forward(request, response);
    // 1. pno Get
    // 2. profno, name, sal, hiredate (pno에 맞는)
    // 3.  Professor DTO --> p
    // 4. setAttribute("professor", p);
    // 5. myResult.jsp 이동
%>
</body>
</html>