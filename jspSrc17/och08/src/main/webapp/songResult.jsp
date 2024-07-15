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
	String name = request.getParameter("name");
	String [] song = request.getParameterValues("song");
	String songs ="";
	if (song != null) {
		for (int i=0 ; i<song.length; i++) {
			if(i==(song.length-1)) songs += song[i];
			else				   songs += song[i] + ", ";
		}
	}
%>
<%=name %>님이 좋아하는 음악은 <%=songs %> 이군요
</body>
</html>