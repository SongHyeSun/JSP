<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>1부터 100까지의 합</h2>
	<h5>1. Expression --> sum1</h5>
<%
	String sum1 = request.getAttribute("sum1").toString();
	/* int sum1 = Integer.parseInt(request.getAttribute("sum1")); */
%>
	<!-- 1. Expression sum1 -->
	sum1 : <%=sum1 %><p>
	<!-- 2. EL  sum2 -->
	<h5>2. EL  -->  sum2</h5>
	sum2 : ${sum2 }
</body>
</html>