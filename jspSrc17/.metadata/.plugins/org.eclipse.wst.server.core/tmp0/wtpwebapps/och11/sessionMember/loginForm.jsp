<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table {background-color: skyblue;}
	tr:hover {background-color: lightgreen;}
</style>
</head>
<body>
	<form action="loginPro.jsp">
		<table border="1">
			<tr><td>아이디</td><td><input type="text" name="id"
				required="required"></td></tr>
			<tr><td>패스워드</td><td><input type="password" name="password"
				required="required"></td></tr>
			<tr><td><input type="submit" value="확인"></td>
				<td><input type="reset" value="취소"></td></tr>
		</table>
	</form>
	<input type="button" value="회원가입" onclick="location.href='../session/person.jsp'">
</body>
</html>