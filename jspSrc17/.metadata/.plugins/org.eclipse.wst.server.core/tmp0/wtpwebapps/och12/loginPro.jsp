<%@page import="och12.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String id = request.getParameter("id");
	String passwd = request.getParameter("passwd");
	MemberDao md = MemberDao.getInstance();
	
	//HW01 check 만들기 (MemberDao에!!)
	int result = md.check(id, passwd);
	//존재하는 User  	--> PreparedStatement 사용
	if (result == 1) {
		session.setAttribute("id", id);
		System.out.println("Login Success... ");
		
		response.sendRedirect("main.jsp");
	// Password X
	} else if (result == 0) {
%>
	<script type="text/javascript">
		alert("암호 몰라 !!?");
		history.go(-1);
	</script>
<%	} else {	%>
	<script type="text/javascript">
		alert("User 미존재(-1) 넌 누구냐 수상한데!!");
		history.go(-1);
	</script>
<%	}	%>
</body>
</html>