<%@page import="och11.MemberDto"%>
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
	String chk = "";
	String agree = request.getParameter("agree");
	
	if (agree.equals("y")) {
		//agree에서 setAttribute한 값을 받아준다.
		String id 		= (String)session.getAttribute("id");
		String password = (String)session.getAttribute("password");
		String name 	= (String)session.getAttribute("name");
		
		//이렇게 부르면 자바에서 코딩한 것을 불러올 수 있다.
		MemberDao md = new MemberDao();
		MemberDto member = new MemberDto();
		
		member.setId(id);
		member.setName(name);
		member.setPassword(password);
		
		int result = md.insert(member); 
		if (result > 0) chk ="success";
		else chk = "fail";
	} else chk = "fail";
	session.invalidate();
	out.print("invalidate() 적용 후에도" +session.getId() + "<br>");
	response.sendRedirect("result.jsp?chk=" +chk);
%>
</body>
</html>