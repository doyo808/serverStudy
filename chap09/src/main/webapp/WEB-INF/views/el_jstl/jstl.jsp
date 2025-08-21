<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<div>
		<a href="./home">홈으로 가기</a><br>
		my_value = ${my_value }<br>
	</div>

	<h3> 여기는 jstl 연습소</h3>
	<ul>
		<li><a href="https://mvnrepository.com/artifact/javax.servlet/jstl/1.2">다운로드링크</a></li>
		<li>c:set - 현재 페이지 어트리뷰트에 원하는 값을 추가한다</li>
		<li>c:if - if문을 편리하게 사용할 수 있다 (esle if, else 불가능)</li>
		<li>c:choose, c:when, c:ohterwise - if, else if, else문을 사용할 수 있다</li>
		<li>c:forEach - forEach 반복문을 쉽게 사용할 수 있다</li>
		<li>c:forTokens - 전달한 문자열을 split하여 쉽게 반복할 수 있다</li>
		<li>c:url - 원하는 url을 좀 더 편리하게 생성할 수 있다 </li>
	</ul>
	
	<h3># c:set (c는 우리가 설정한 prefix값이다)</h3>
	<c:set var="fruit" value="복숭아" scope="page"/>
	<c:set var="fruit" value="사과" scope="request"/>
	<c:set var="fruit" value="오렌지" scope="session"/>
	<%
		pageContext.setAttribute("fruit", "황도복숭아");
		application.setAttribute("fruit", "파인애플");
	%>
	
	<ul>
		<li>fruit=${pageScope.fruit }</li>
		<li>fruit=${requestScope.fruit }</li>
		<li>fruit=${sessionScope.fruit }</li>
		<li>fruit=${applicationScope.fruit }</li>
	</ul>
	
	<h3># c:if</h3>
	<!-- 원래는 이렇게 해야함
		if (Integer.parseInt(pageContext.getAttribute("age")) >= 19) { -->
	<c:set var="age" value="15"/>
	<c:if test="${age >= 19}">
		<div>성인입니다</div>
	</c:if>
	<c:if test="${age < 19}">
		<div>성인이 아닙니다</div>
	</c:if>
	
	
	<h3>c:choose, c:when, c:otherwise</h3>
	<div>
		<c:choose>
			<c:when test="${age >= 19 }">
				성인입니다.
			</c:when >
			<c:when test="${age >= 14 }">
				청소년입니다.
			</c:when>
			<c:when test="${age >= 5 }">
				유아입니다.
			</c:when>
			<c:otherwise></c:otherwise>
		</c:choose>
	</div>
	
	
	
	<h3># c:forEach</h3>
	<ul>
		<li>items와 var을 사용하는 방법과 begin, end를 사용해 원하는 숫자를 직접 생성하는 방법이 있다</li>
		<li>items에는 iterable 객체를 사용해야 한다 (List, Set, Array)</li>
	</ul>
	
	<h3># 스테디셀러 목록</h3>
	<table border="1" cellpadding="10" cellspacing="0">
	  <thead>
	    <tr>
	      <th>isbn</th>
	      <th>도서명</th>
	      <th>저자</th>
	      <th>가격</th>
	      <th>페이지</th>
	      <th>출간일</th>
	    </tr>
	  </thead>
	  <tbody>
	    <c:forEach var="book" items="${steadies}" varStatus="status">
	      <tr>
	        <td>${book.isbn}</td>
	        <td>${book.book_name}</td>
	        <td>${book.author}</td>
	        <td>${book.price}</td>
	        <td>${book.max_page}</td>
	        <td>${book.pub_date}</td>
	      </tr>
	    </c:forEach>
	  </tbody>
	</table>
		
	
	
	<c:forEach begin="2" end="9" var="dan">
		<div style="display: flex">
			<c:forEach begin="0" end="9" var="gop">
				<c:if test="${gop == 0 }">
					<div style="width: 80px;">${dan}단</div>
				</c:if>
				<c:if test="${gop != 0 }">
					<div style="width: 150px;">${dan} x ${gop } = ${dan * gop }</div>
				</c:if>
			</c:forEach>
		</div>
	</c:forEach>
	
	
	<h3># 반복문의 정보를 볼 수 있는 varStatus</h3>
	<ul>
		<li>status.index: 현재 인덱스를 볼 수 있다 (0부터 시작)</li>
		<li>status.count: 현재 카운트를 볼 수 있다 (1부터 시작)</li>
		<li>status.first: 첫 번째 반복이라면 true가 나온다</li>
		<li>status.last : 마지막 반복이라면 true가 나온다</li>
		<li>status.current: 현재 값을 볼 수 있다</li>
	</ul>
	
	<c:forEach begin="50" end="80" step="3" var="i" varStatus="status">
		<div <c:if test="${status.first}">style="color: blue;"</c:if>
			 <c:if test="${status.last}">style="color: red;"</c:if>
		
			 >${i }는 몇 번째 값일까?:${status.count }
	 	</div>
	</c:forEach>
	
	
<h3># c:forTokens</h3>
	<c:set var="snacks" value="오감자, 브이콘, 엄마손파이/ 포카칩, 스윙칩, 포스틱, 양파링, 새우깡 벌집핏자"></c:set>
	<c:forTokens delims=",/ " items="${snacks}" var="snack" varStatus="status">
		<div>${status.count}번째 과자는 ${snack}입니다.</div>
	</c:forTokens>

<h3># c:url</h3>
	<!-- page 영역에 원하는 url을 저장해놓고 사용하는 기능을 제공한다 -->
	<c:url value="/el_jstl/el" var="el_study" scope="page"/>
	<c:url value="/el_jstl/el" var="el_study2">
		<c:param name="name">alex</c:param>
		<c:param name="age">31</c:param>
	</c:url>
	
	<ul>
		<li><c:url value="/jstl/el">: 알아서 컨텍스트 경로를 추가해준다</c:url></li>
		<li> <a href="<c:url value="/el_jstl/el"/>">EL 공부하러 가기</a> </li>
		<li> <a href="${el_study }">EL 공부하러 가기</a> </li>
		<li> 파라미터가 있는 url 생성하기: ${el_study2 } </li>
		<li> <a href="${el_study2 }">파라미터가 들고 EL 공부하러 가기</a></li>
	</ul>

</body>
</html>