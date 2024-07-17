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
<!-- 
1. deptno 를 가지고 dept 조회
2. deptno ,dname , loc : request.setAttribute
3. oraDeptIn.jsp Page이동
    EL : deptno ,dname , loc 
 -->
 
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
		String dname = rs.getString("dname");
		String loc = rs.getString("loc");
		
		request.setAttribute("deptno", deptno);
		request.setAttribute("dname", dname);
		request.setAttribute("loc", loc);
		
		rs.close();
		stmt.close();
		conn.close();
		
		RequestDispatcher rd = request.getRequestDispatcher("oraDeptIn.jsp");
		rd.forward(request, response);
	}
	stmt.close();
	conn.close();
 %>
 <script type="text/javascript">
 	alert("헐~ 없는 부서야");
 	location.href = "oraDeptUpdate.html";
 </script>
</body>
</html>