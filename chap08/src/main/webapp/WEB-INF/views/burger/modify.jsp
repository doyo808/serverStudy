<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="chap08.dto.BurgerDTO"%>
<%
	String contextPath = request.getContextPath();
	
	String[] BURGER_PRICES = (String[])application.getAttribute("BURGER_PRICES");

	BurgerDTO burger = null;
	if (request.getAttribute("burger") == null) {
		//response.sendRedirect(contextPath + "/burgers/list");
		response.sendRedirect("./list");
	} else {
		burger = (BurgerDTO)request.getAttribute("burger");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>여기는 수정하기</h3>
	
	<form id="f" action="./modify" method="POST">
	<div id="detail">
		<div>이름</div>
		<div>
			<input type="text" name="name" value="<%=burger.getName() %>" readonly>
		</div><br>
		<div>가격</div>
		<div>
			<select name="price">
				<% for (String price : BURGER_PRICES) { %>
					<option value="<%=price %>"><%=price %></option>
				<% } %>
			</select>
		</div><br>
		<div>맛</div>
		<div>
			<input type="text" name="taste" value="<%=burger.getTaste() %>">
		</div><br>
	</div>
	</form>
	
	<button type="submit" form="f">결정!</button>
	<button>목록</button>
	
	
</body>
</html>