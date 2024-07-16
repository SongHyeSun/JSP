<%@page import="och10.Dept"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Expression 부서정보</h2>
	<%
		Dept dept = (Dept)request.getAttribute("dept");
	%>
	부서코드 : <%=dept.getDeptno() %><p>	<!-- dept.deptno X -->
	부서명 : <%=dept.getDname() %><p>
	근무지 : <%=dept.getLoc() %><p>
	
	<h2>EL 표기법 부서정보 (같은 의미)</h2>
	부서코드 : ${dept.getDeptno() }<p>		<!-- 가능!! -->
	부서명 : ${dept.dname }<p>	<!-- getDname하고 똑같은 것! 따라서 자바에서 get없애면 오류가 뜬다. -->
	근무지 : ${dept.loc }<p>
</body>
</html>