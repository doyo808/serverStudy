<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${board.board_title }</title>
	<link rel="stylesheet" href="/resources/board/css/detail.css">
</head>
<body>
	 <div class="container">
        <h2 class="title">${board.board_title}</h2>

        <div class="meta">
            <span>글쓴이: ${board.board_writer}</span>
            <span>작성일: ${board.board_write_date}</span>
            <span>조회수: ${board.board_view_count}</span>
        </div>

        <div class="content">
            ${board.board_content}
        </div>

        <div class="reaction">
            <button onclick="location.href='./pos_count?board_id=${board.board_id}&ip=${board.board_writer_ip_addr }'">
            	👍 ${board.board_pos_count}</button>
            <button>
            	👎 ${board.board_neg_count}</button>
        </div>

        <div class="buttons">
            <button >수정</button>
            <button >삭제</button>
            <button onclick="location.href='/board/'">목록</button>
        </div>
    </div>
</body>
</html>