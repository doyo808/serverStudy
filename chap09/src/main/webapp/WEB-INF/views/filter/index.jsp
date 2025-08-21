<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>필터란 무엇인가</title>
</head>
<body>
	
	<h3># JSP Filter</h3>
	<ul>
		<li>필터는 요청이 서블릿에 도착하기 전에 먼저 거쳐가게끔 설정해둘 수 있는 객체다</li>
		<li>특정 주소에 대하여 특정 필터를 지나가도록 web.xml에 설정해 둘 수 있다</li>
		<li>필터는 하나의 URL에 여러겹으로 설정해 둘 수 있다</li>
		<li>필터에 대한 내용을 모두 작성한 후에는 반드시 chain 객체를 통해 다음 필터로 보내야 한다</li>
	</ul>
	
	<c:if test="${not empty param.hanguel }">
		<div>hanguel = ${param.hanguel }</div>	
	</c:if>
	
		<form action="." method="POST">
			<input type="hidden" name="hanguel" value="한글 되나용?">
			<button type="submit">필터에 한글 보내보기</button>
		</form>
	
	
</body>
</html>