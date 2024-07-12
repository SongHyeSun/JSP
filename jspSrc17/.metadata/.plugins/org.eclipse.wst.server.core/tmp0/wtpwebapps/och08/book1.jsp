<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="pt" class="och08.Book" scope="request"/>
	<jsp:setProperty property="*" name="pt"/>
	<jsp:forward page="productInfo.jsp"></jsp:forward>
</body>
</html>