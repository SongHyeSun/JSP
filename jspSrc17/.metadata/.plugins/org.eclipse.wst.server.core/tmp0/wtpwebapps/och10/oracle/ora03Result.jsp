<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- // 부서정보
	// 부서코드 : 20
	// 부서명 : RESEARCH
	// 근무지 : DALLAS -->
<%
	String deptno = request.getAttribute("deptno").toString();
	String dname = request.getAttribute("dname").toString();
	String loc = request.getAttribute("loc").toString();
%>
	<h2>부서정보</h2>
	부서코드 : <%=deptno %><p>
	부서명 : <%=dname %><p>
	근무지 : <%=loc %> 
<%--부서코드 : ${deptno }<p>
	부서명 : ${dname }<p>
	근무지 : ${loc } --%>
</body>
</html>