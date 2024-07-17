<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- useBean을 자바로 표현하면, Person person = new Person(); -->
	<jsp:useBean id="person" class="och08.Person"></jsp:useBean>
<%-- 자바 코딩으로 하면, person.setName("<%= name%>") --%>
	<jsp:setProperty property="name" name="person" param="name"/>
	
	<jsp:setProperty property="gender" name="person" value="남자"/>
	
	<h2>인적 사항</h2>
	<!-- person.getName()  -->
	이름 : <jsp:getProperty property="name" 	 name="person"/><p>
	성별 : <jsp:getProperty property="gender" name="person"/><p>
	나이 : <jsp:getProperty property="age" 	 name="person"/><p>
	
</body>
</html>