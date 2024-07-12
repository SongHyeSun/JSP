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
	String [] winner = {"송길동", "황길동", "황보길동"};
	request.setAttribute("winner", winner);
	RequestDispatcher rd = 
			request.getRequestDispatcher("winnerResult.jsp");
	rd.forward(request, response);
%>
</body>
</html>