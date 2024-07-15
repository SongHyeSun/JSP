<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:forward page="forwardResult.jsp">
	<jsp:param value="누구세요" name="name"/>
</jsp:forward>
</body>
</html>