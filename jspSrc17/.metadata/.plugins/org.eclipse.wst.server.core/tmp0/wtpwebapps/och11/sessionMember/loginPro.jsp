<%@page import="och11.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="../error.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	//id , password get으로
	//memberdao를 md로 instance 만들어준다.
	
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	MemberDao md = new MemberDao();
	int result = md.check(id, password);
	//현장 work01
	//return Result 1 id, password 같을 때
	//				0 id는 같고 password X
	//			   -1 id 자체가 없다
	if (result==1) {
		session.setAttribute("id", id);
		response.sendRedirect("sessionMain.jsp");
	} else if (result == 0) {
%>
<script type="text/javascript">
	alert("암호가 틀려! 누구냐");
	history.go(-1);
</script>
<%	} else {	%>
<script type="text/javascript">
	alert("넌 누구냐! 20년간 만두만 주고...");
	history.back();
</script>
<%	}	 %>
</body>
</html>