<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
요기는 연산만 하고 결과는 다음 페이지
<%
	int num1 = Integer.parseInt(request.getParameter("num1"));
	int num2 = Integer.parseInt(request.getParameter("num2"));
	int add = num1 + num2;
	int min = num1 - num2;
	int mul = num1 * num2;
	int div = num1 / num2;
	request.setAttribute("add", add);
	request.setAttribute("min", min);
	request.setAttribute("mul", mul);
	request.setAttribute("div", div);
	//요기는 연산만 하고 결과는 다음 페이지
	//선언 -> calResult.jsp로 이동하겠다!
	RequestDispatcher rd = request.getRequestDispatcher("calResult.jsp");
	//진짜 이동
	rd.forward(request, response);
%>

</body>
</html>