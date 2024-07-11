<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%!
	//three(int x) -> X*X*X
	int three (int x) {
		return x*x*x;
	}
%>
	2^3 = <%=three(2) %><p>
	3^3 = <%=three(3) %><p>
	4^3 = <%=three(4) %><p>
	5^3 = <%=three(5) %><p>
	6^3 = <%=three(6) %><p>
</body>
</html>