<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, chap08.dto.BurgerDTO"%>
<%
	List<BurgerDTO> burgers = null;
	if (request.getAttribute("burgers") != null) {
		burgers = (List<BurgerDTO>)request.getAttribute("burgers");
	}
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style>
		.header {
			font-size : 18px;
			font-weight: 600;
		}
		.rows > div {
			width: 150px;
		}
		
	</style>
</head>
<body>
	<h3>버거목록</h3>
	
	<div class="table">
		<% if (burgers == null || burgers.isEmpty()) { %>
			<div>조회된 버거가 없습니다.</div>
			
		<% } else { %>
			<div class="rows header" style="display:flex;">
				<div>이름</div>
				<div>가격</div>
				<div>맛</div>
			</div>
			
			<% for (BurgerDTO b : burgers) { %>
				<div class ="rows" style="display:flex;">
					<div>
						<a href="./detail?burger_name=<%=b.getName()%>">	
						<%= b.getName() %></a>
					</div>
					<div><%= b.getPrice() %></div>
					<div><%= b.getTaste() %></div>
				
				</div>	
			
			<% } %>
		
		<% } %>
	
	</div>
	
	<br>
	<button id="add_burger_btn">새 상품 추가하기</button>
	
	<script src="<%=request.getContextPath() %>/resources/burger/js/burger_list.js"></script>
</body>
</html>