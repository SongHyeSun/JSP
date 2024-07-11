<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>연산 결과 Cal3</h2>
<%
	try {
		
		//1. num1, num2 parameter 받기
		int num1 = Integer.parseInt(request.getParameter("num1"));
		int num2 = Integer.parseInt(request.getParameter("num2"));
		//2. +,-,*,/ 출력하기
		out.println("덧셈 : " +num1+" + "+num2+" = "+(num1+num2) +"<p>");
		out.println("뺄셈 : " +num1+" - "+num2+" = "+(num1-num2) +"<p>");
		out.println("곱셈 : " +num1+" * "+num2+" = "+(num1*num2) +"<p>");
		out.println("나눗셈 : "+num1+" / "+num2+" = "+(num1/num2) +"<p>");
		//같은 처리를 Java Script로 수행해주세요
		//3. NumberFormatException -> 그게 숫자냐
		//   ArithmeticException   -> 헐 0으로 나누다니!
		//	 Exception			   -> 하여튼 에러야
		//   모두 전 페이지로 이동
	} catch (NumberFormatException e) { %>
<script type="text/javascript">
	alert("그게 숫자냐!");
	/* -1은 전 페이지로 돌아간다는 의미 */
	history.go(-1);
</script>
<%	} catch (ArithmeticException e) {	%>
<script type="text/javascript">
	alert("0으로 못나눠");
	history.back();
</script>
<% 	} catch (Exception e) {	
	out.println(e.getMessage());
%>
<script type="text/javascript">
	alert("하여튼 에러야");
	location.href="num2.html";
</script>		
<%	} %>

</body>
</html>