<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리스너란 무엇인가</title>
</head>
<body>

	<h3># JSP Listener</h3>
	
	<ul>
		<li>웹 서버에서 특정 이벤트가 발생하기를 기다리다가 실행되는 객체</li>
		<li>ServletContextListener :<br>
			서블릿 컨텍스트(서버)가 로딩(시작 종료)될때의 이벤트 리스너</li>
		<li>ServletContextAttributeListener :<br>
			서블릿 컨텍스트(애플리케이션 객체)에 어트리뷰트를 추가, 제거 할 때의 이벤트 리스너</li>
		<li>HttpSessionListener :<br>
			HTTP 세션이 시작, 종료 될 때의 이벤트 리스너(세션발급, 만료 등등)</li>
		<li>HttpSessionAttributeListener:<br>
			세션 객체에 어트리뷰트를 추가, 제거 할 때의 이벤트 리스너</li>
		<li>ServletRequestListener:<br>
			요청이 발생했을 때와 응답을 수행하여 요청객체가 파괴될 때의 이벤트 리스너</li>
		<li>ServletRequestAttributeListener:<br>
			요청 객체에 어트리뷰트를 추가, 제거 할 때의 이벤트 리스너</li>
	</ul>
	
	<h3># 아이스크림 목록</h3>
	<ol>
		<li>application: ${applicationScope.iceCream }</li>
		<li>session: ${sessionScope.iceCream }</li>
		<li>request: ${requestScope.iceCream }</li>
	</ol>
	
	
</body>
</html>