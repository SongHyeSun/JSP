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
	String [] movie = request.getParameterValues("movie");
	String movies ="";
	if (movie != null) {
		for (int i=0; i<movie.length; i++) {
			if (i==(movie.length-1)) movies += movie[i];
			else 					 movies += movie[i] + ", ";
		}
	}
%>
	<%=name %>님은 <%=movies %> 영화를 좋아하시는군요~
</body>
</html>