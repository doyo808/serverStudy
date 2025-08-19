<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="dto.PizzaDTO"%>
<%
	String contextPath = request.getContextPath();
	
	PizzaDTO pizza = null;
	if (request.getAttribute("pizza") == null) {
		response.sendRedirect("./list");
	} else {
		pizza = (PizzaDTO)request.getAttribute("pizza");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h3>이곳은 피자 디테일입니다.</h3>
	
	<form id="pizzaForm" action="./modify" method="POST">
	<div>
		<div>ID</div>
		<input type="text" name="pizza_id" value="<%=pizza.getPizza_id()%>" readonly>
		<div>Name</div>
		<input type="text" name="pizza_name" value="<%=pizza.getPizza_name()%>">
		<div>Made_date</div>
		<input type="date" name="made_date" value="<%=pizza.getMade_date()%>">
	</div>
	</form> <hr>
	
	<button type="submit" form="pizzaForm" onclick="return(window.confirm('진짜?'))">수정</button>
	<button onclick="confirmUrl('./delete?pizza_id=<%=pizza.getPizza_id()%>')">삭제</button>
	<button onclick="location.href='./list'">목록</button>
	
	<script src="<%=contextPath%>/resources/pizza/detail.js"></script>
	
</body>
</html>