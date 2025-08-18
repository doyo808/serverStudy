<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, dto.PizzaDTO"%>
<%
	List<PizzaDTO> pizzas = null;
	if (request.getAttribute("pizzas") != null) {
		pizzas = (List<PizzaDTO>)request.getAttribute("pizzas");
	}

%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>피자리스트</title>
	
	<style>
		.rows {
			display: flex;
		}
		.rows > div {
			min-width: 150px;
			margin: 10px;
			border: 1px solid black;
		}
	</style>
</head>
<body>
	
	<h3>이곳은 피자 리스트 입니다.</h3>
	
	<div class="table">
		<% if (pizzas == null) { %>
			<h3>피자 목록 없음!!</h3>
		<% } else { %>
			<div class="rows header">
				<div>피자ID</div>
				<div>피자이름</div>
				<div>만든날짜</div>
			</div>
			
			<% for (PizzaDTO p : pizzas) { %>
				<div class="rows">
					<div><%= p.getPizza_id() %></div>
					<div><%= p.getPizza_name() %></div>
					<div><%= p.getMade_date() %></div>
				</div>
			<% } %>
		
		<% } %>
		
	</div>
	<button>새 피자 만들기</button>
	
	<script src="Pizza/resources/pizza/pizza_list.js"></script>
</body>
</html>