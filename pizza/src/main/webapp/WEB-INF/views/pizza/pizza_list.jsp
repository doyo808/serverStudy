<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, dto.PizzaDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	List<PizzaDTO> pizzas = null;
	if (request.getAttribute("pizzas") != null) {
		pizzas = (List<PizzaDTO>)request.getAttribute("pizzas");
	}
	
	int affectedRows = 0;
	if (request.getAttribute("rows") != null) {	
		affectedRows = (int)request.getAttribute("rows"); 
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
		.zero {
			display: none;
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
			
			<c:forEach items="${pizzas }" var="pizza">
				<div class="rows">
					<div>
						<a href="./detail?pizza_id=${pizza.pizza_id }">
						${pizza.pizza_id }</a>
					</div>
					<div>${pizza.pizza_name }</div>
					<div>
						<a href="./deleteAll?made_date=${pizza.made_date }"
						   onclick="return(window.confirm('${pizza.made_date }에 만든 피자들을 모두 삭제하시겠습니까?'))">
						${pizza.made_date }</a>
					</div>
				</div>
			</c:forEach>
		
		<% } %>
		
	</div>
	<button onclick="moveTo('/Pizza/add')" autofocus>새 피자 만들기</button>
	<div id="affectedRowsDiv">삭제된 행의 수 : <c:out value="${rows }"/> </div>
	
	<script> var affectedRows = Number('<c:out value="${rows }"/>')</script>
	<script src="/Pizza/resources/pizza/pizza_list3.js"></script>
</body>
</html>