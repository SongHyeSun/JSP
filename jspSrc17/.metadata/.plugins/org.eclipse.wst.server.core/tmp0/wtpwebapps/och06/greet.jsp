<%@page import="java.io.FileWriter"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.GregorianCalendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%!
	//Method, Member변수 (!:선언) --> init()
	//GregorianCalendar 반드시 자동완성 시키기
	
	//전역변수
	private PrintWriter pw;
	String date;
	
	public void jspInit() {
		GregorianCalendar gc = new GregorianCalendar();
		date = String.format("%TF %TT", gc, gc);
		System.out.println("jspInit date->"+date);
		String fileName = "c:/log/"+date.replaceAll(":", "") + ".txt";
		try {
			pw = new PrintWriter(new FileWriter(fileName, true));
		} catch (Exception e) {
			System.out.println("대박! 으이그");
		}
	}
%>
<%
	//doGet/doPost
	String name = request.getParameter("name");
	System.out.println(name+"사회활동");
	String msg = name + "님 방가워";
	//BROWSER 화면 출력
	out.println(msg + "<p> 현재시간 : "+date);
	//File 출력
	pw.println(msg + "\r\n 현재시간 : "+date+"\r\n");
%>
<%!
	//Method, Member변수 (!:선언) --> destroy()
	public void jspDestroy() {
		System.out.println("greet의 유언활동....");
		pw.flush();
		if(pw !=null)	pw.close();
	}
%>
</body>
</html>