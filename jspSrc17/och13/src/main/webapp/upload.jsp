<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
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
	//5M
	int maxSize = 5 * 1024 * 1024;
	String fileSave = "/fileSave";
	//Meta Data
	String realPath = getServletContext().getRealPath(fileSave);
	System.out.println("realPath->"+realPath);
	MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
%>
</body>
</html>