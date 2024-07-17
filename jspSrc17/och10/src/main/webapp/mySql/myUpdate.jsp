<%@page import="java.sql.PreparedStatement"%>
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
	<h2>스크릿틀릿 + PreparedStatement HW02</h2>
	<%
	int dno = Integer.parseInt(request.getParameter("dno"));
	String dname = request.getParameter("dname");
	String phone = request.getParameter("phone");
	String position = request.getParameter("position");
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/scottdb?serverTimezone=UTC";
	String sql = "UPDATE division SET dname=?, phone=?, position=? WHERE dno=?";
	Class.forName(driver);
	Connection conn = DriverManager.getConnection(url, "root" , "mysql84");
	PreparedStatement pstmt = conn.prepareStatement(sql);
	
	pstmt.setString(1, dname);
	pstmt.setString(2, phone);
	pstmt.setString(3, position);
	pstmt.setInt(4, dno);
	
	int result = pstmt.executeUpdate();
	if (result > 0) out.println("mysql 수정 성공 ㅋㅋ");
	else 		    out.println("mysql 수정 실패 헉 ㅠㅠ");
	
	pstmt.close();
	conn.close();
	
	//1. dno, dname, phone, position Get
	//2. Update
	//		1) 성공 -> mysql 수정 성공 ㅋㅋ
	//		2) 실패 -> mysql 수정 실패 헉 ㅠㅠ
	%>
</body>
</html>