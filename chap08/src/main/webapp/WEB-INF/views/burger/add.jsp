<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새 상품 등록하기</title>
</head>
<body>
	
	<form action="./add" method="POST">
		버거 이름:<input type="text" name="name" value="기본버거"><br>
		버거 가격:<input type="number" name="price" value=3500><br>
		버거 맛:<input type="text" name="taste" value="버거맛"><br>
		
		<!-- 
			날짜: <input type="date" name="release_date">라면
			yyyy-mm-dd로 넘어온다
			서블릿에서
			LocalDate ld = LocalDate.parse(request.getParameter("release_date")); 
			Date d = Date.valueOf(ld); 식으로 DB에 넣을 수 있다
		 -->
		<input type="submit" value="추가!"><br>
	</form>
</body>
</html>