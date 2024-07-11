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
	request.setCharacterEncoding("utf-8");
	int num = Integer.parseInt(request.getParameter("num"));
	int sum = 0;
	for (int i=1; i<=num ; i++) {
		sum += i;
		//자바의 sysout과 같지X println이라고 자동 줄바꿈을 해주는 것이 아니다.
		//out.println(i+"일 때 합계는 "+sum);
		out.println(i+"일 때 합계는 "+sum + "<br>");
	}
%>
</body>
</html>