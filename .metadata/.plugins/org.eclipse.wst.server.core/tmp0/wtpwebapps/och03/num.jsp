<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	request.setCharacterEncoding("utf-8");
	int num = Integer.parseInt(request.getParameter("num"));
	int sum = 0;
	for (int i=1; i<=num ; i++) {
		sum += i;
	}
%>
<body>
	<h1>1부터 <%=num %>까지 합</h1>
	<p><%=sum%></p>
</body>
</html>