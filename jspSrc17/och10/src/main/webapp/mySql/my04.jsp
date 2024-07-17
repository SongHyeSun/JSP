<%@page import="och10.Division"%>
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
	String dno = request.getParameter("dno");
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/scottdb?serverTimezone=UTC";
	String sql = "SELECT * FROM scottdb.division WHERE dno="+dno;
	Class.forName(driver);
	Connection conn = DriverManager.getConnection(url, "root" , "mysql84");
	Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery(sql);
	
	 Division division = new Division();
	
	if (rs.next()) {
		String dname = rs.getString("dname");
		String phone = rs.getString("phone");
		String position = rs.getString("position");
		
		//1. division DTO선언한 후 DTO만 저장해서 my04Result로 이동!
		//직접 받아서 할 수도 있고, dname 이름을 지정해줄 수도 있다. 두 가지 방식 다 보여주기 위해서!
		division.setDno(rs.getInt(1));
		division.setDname(dname);
		division.setPhone(phone);
		division.setPosition(rs.getString(4));
		request.setAttribute("division", division);
		
	} else out.println("그게 무슨 부서야 없는데");
	rs.close();
	stmt.close();
	conn.close();
	//Page 이동 --> my03Result.jsp
	RequestDispatcher rd = request.getRequestDispatcher("my04Result.jsp");
	rd.forward(request, response);
	
%>
</body>
</html>