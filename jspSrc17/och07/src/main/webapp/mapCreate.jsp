<%@page import="java.util.HashMap"%>
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
	HashMap<String, String> addrMap = new HashMap<String,String>();
	addrMap.put("Park","목동");
	addrMap.put("박","까치산");
	addrMap.put("Jessica","크라이스 처치");
	addrMap.put("Susan","시드니");
	request.setAttribute("Address", addrMap);
	RequestDispatcher dispatcher = request.getRequestDispatcher("mapView.jsp?NAME=Park");
	dispatcher.forward(request, response);
%>
</body>
</html>