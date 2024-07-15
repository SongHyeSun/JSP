<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 
	// 현장Work01
	// Bean  : pt, class : Clothing
	// 모든 property 저장
	// 이동:  productInfo.jsp
-->
	<jsp:useBean id="pt" class="och08.Clothing" scope="request"></jsp:useBean>
	<jsp:setProperty property="*" name="pt"/>
	<jsp:forward page="productInfo.jsp"></jsp:forward>
</body>
</html>