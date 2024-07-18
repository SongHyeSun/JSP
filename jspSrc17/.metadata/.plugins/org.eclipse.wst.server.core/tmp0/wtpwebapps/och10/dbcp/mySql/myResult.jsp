<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>professor 정부</h2>
	사번 : ${professor.profno }<p>
	이름 : ${professor.name }<p>
	급여 : <fmt:formatNumber value="${professor.sal }" groupingUsed="true"/><p>
	입사일 : <fmt:formatDate type="date" value="${professor.hiredate }" pattern="YYYY/MM/dd"/>
</body>
</html>