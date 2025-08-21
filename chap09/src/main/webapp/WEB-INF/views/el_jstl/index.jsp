<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3># JSP EL (JavaServerPages Expression Language)</h3>
	
	<ul>
		<li>JSP의 기존 디렉티브들이 너무 불편하여 생긴 문법 (JSP 특정 버전 이후 추가됨)</li>
		<li>어트리뷰트와 파라미터에 실려있는 값들을 아주 편리하게 꺼내 쓸 수 있는 기능</li>
		<li>간단한 자바 메서드의 호출은 가능하지만 모든 메서드를 자바에서처럼 자유롭게 사용할 수 있는 것은 아니다</li>
	</ul>
	
	<div>
		<a href="./el">EL 연습하러 가기</a>
	</div>
	
	
	
	<h3># JSTL (JSP Standard Tag Library)</h3>
	<ul>
		<li>JSP에서 자바 문법들을 사용하기가 너무 지저분해서 등장한 커스텀 태그 라이브러리</li>
		<li>화면을 구성하기 위해 필요한 일부 자바 문법들을 태그 형식으로 활용할 수 있게 만들어놓은 라이브러리</li>
		<li>JSTL은 외부의 태그 라이브러리이므로 별도로 다운로드 하여 우리 프로젝트에 포함시키는 과정이 필요하다</li>
		<li>사용하고 싶은 JSP페이지에 태그 라이브러리를 포함시키는 문법도 필요하다(tablib)</li>
	</ul>
	<div>
		<a href="./jstl">jstl 연습하러 가기</a>
	</div>
	
	
</body>
</html>