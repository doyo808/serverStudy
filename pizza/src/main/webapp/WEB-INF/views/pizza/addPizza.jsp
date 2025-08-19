<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h3>여기는 피자추가 페이지</h3>
	<form action="./add" method="POST">
		
		피자이름: <input type="text" name="pizza_name" value="피자"><br>
		만든날짜: <input type="date" name="made_date" value="2025-08-08"><br>
		
		<input type="submit" value="추가!" autofocus>
		<button onclick="location.href='./list'">목록으로</button>
	</form>
	
	
</body>
</html>