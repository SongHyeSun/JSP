<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>이름</h2>
	${param.name }
	
	<h2>이름1</h2>
	${empty param.name ? "손님" : param.name} 안녕하세요
</body>
</html>