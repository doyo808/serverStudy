<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="chap08.dto.BurgerDTO"%>
<%
	String contextPath = request.getContextPath();

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
	<title><%=burger.getName() %>의 상세정보</title>
	<link rel="stylesheet" href="<%=contextPath%>/resources/burger/css/detail.css">
</head>
<body>
	
	<h3>여기는 버거 디테일</h3>
	<div id="detail">
		<div>이름</div>
		<div><%=burger.getName() %></div><br>
		<div>가격</div>
		<div><%=burger.getPrice() %></div><br>
		<div>맛</div>
		<div><%=burger.getTaste() %></div><br>
	</div>
	
	<button id="modify_btn" 
		onclick="addLinkToButton(this.id, './modify?burger_name=<%=burger.getName()%>')">
		수정</button>
	<button id="delete_btn"
		onclick="confirmDelete('./delete?burger_name=<%=burger.getName() %>')">
		삭제</button>
	<button id="list_btn">목록</button>
	
	<script src="<%=contextPath%>/resources/burger/js/detail.js"></script>
	
</body>
</html>