<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	pageContext.setAttribute("book", "리니지로 재태크하기");

	pageContext.setAttribute("a", 20);
	pageContext.setAttribute("b", 13);
	pageContext.setAttribute("c", 20.1);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL 연습 페이지</title>
</head>
<body>
	
	<h3># EL을 활용해 어트리뷰트 값 출력하기</h3>
	<ul>
		<li>${score}</li>
		<li>${book}</li>
		<li>${CLOTHES_SIZES}</li>
		<li>${chair}</li>
	</ul>
	
	
	<h3># 영역별 어트리뷰트 값 출력하기</h3>
	<p>모든 영역에 같은 어트리뷰트 이름을 가진 값이 있은 경우 원하는 것을 정확하게 꺼낼 수 있다</p>
	<ul>
		<li>기본: ${book} <br>(가장 가까운 영역의 값이 나옴, application &gt; session &gt; request &gt; page)</li>
		<li>페이지(pageScope): ${pageScope.book }</li>
		<li>리퀘스트(requestScope): ${requestScope.book }</li>
		<li>세션(sessionScope): ${sessionScope.book }</li>
		<li>어플리케이션(applicationScope): ${applicationScope.book }</li>
	</ul>
	
	<h3># EL로 자바빈 스타일 객체 활용하기</h3>
	<p>자바콩 스타일 객체: 필드의 접근제어는 private, 필드에는 Getter/Setter를 사용해 접근 
	<br>※반드시 기본 생성자도 함께 존재</p>
	<ul>
		<li>그냥 사용: ${obj_book } (해당 객체의 toString 사용)</li>
		<li>필드값 사용: ${obj_book.author } (해당 객체의 getter를 알아서 사용)</li>
		<li>책이름: ${obj_book.book_name }</li>
		<li>가격: ${obj_book.price }</li>
		<li>Getter가 만들어져 있지 않은 필드를 사용하면 오류가 발생하게 된다</li>
	</ul>
	
	<h3># EL로 파라미터 값 활용하기</h3>
	<ul>
		<li>주소창에 ?key=value&key=value 적으면 가져쓸 수 있다</li>
		<li>name: ${param.name }</li>
		<li>age: ${param.age }</li>
	</ul>
	
	<h3># EL의 리터럴</h3>
	<ul>
		<li>정수: ${10 }, ${10 + 20 }</li>
		<li>실수: ${123.123 }</li>
		<li>boolean: ${true }, ${false }</li>
		<li>문자열: ${'작은 따옴표 가능' }, ${"큰 따옴표 가능" }</li>
	</ul>
	
	<h3># EL의 연산자</h3>
	<ul>
		<li>산술 연산: +, -, *, /, %(mod)</li>
		<li>비교 연산: ==(eq), !=(ne), lt, gt, le, ge</li>
		<li>empty 연산: 해당 값이 null 또는 ""일 때 true </li>
		<li>논리 연산: &&(and), ||(or), !(not)</li>
		<li>삼항 연산: ? :</li>
	</ul>
	
	<c:set var="animal" value=""/>
	<div>
		<div>${a + b }, ${a mod b }</div>
		<div>${a == b }, ${a ne b }, ${a lt b }, ${a ge b }</div>
		<div>empty: ${empty animal }, not empty: ${not empty animal }</div>
		<div>${a % 2 == 0 and a > 10 }, ${!(b > 10) }</div>
		<div>${a % 10 == 0 ? a : a + 1 }</div>
	</div>
	
	
	
</body>
</html>