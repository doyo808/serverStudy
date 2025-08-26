<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시판</title>
	<link rel="stylesheet" href="/resources/board/css/list.css">
</head>
<body>

	<div id="board_list">
		<div class="row header">
			<div>ID</div>
			<div>글 제목</div>
			<div>작성자</div>
			<div>조회수</div>
			<div>추천</div>
		</div>
	
		<c:forEach items="${boards }" var="board">
			<div class="row">
				<div>${board.board_id }</div>
				<div>
					<a href="./detail?board_id=${board.board_id }">${board.board_title }</a>
				</div>
				<div>${board.board_writer }</div>
				<div>${board.board_view_count }</div>
				<div>${board.board_pos_count - board.board_neg_count }</div>
			</div>
		</c:forEach>
	</div>
	
	<button id="write_btn">글쓰기</button>
	
	<script src="/resources/board/js/list.js"></script>
</body>
</html>