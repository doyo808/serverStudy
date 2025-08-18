<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, chap08.dto.DepartmentDTO"%>
<%
	List<DepartmentDTO> deps = null;
	if (request.getAttribute("departments") != null) {
		deps = (List<DepartmentDTO>)request.getAttribute("departments");
	}
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/dep/css/dep_list.css">
</head>
<body>

	<h3>모든 부서 목록</h3>
	
	<div class=table>
		<% if (deps == null) { %>
			<div>조회된 부서가 없습니다.</div>
		<% } else { %> 
			<div class="row header">
				<div>department_id</div>
				<div>department_name</div>
				<div>manager_id</div>
				<div>location_id</div>
			</div>
			
			<% for (DepartmentDTO dep : deps) { %>
				
				<div class="row">
					<div> <%=dep.getDepartment_id() %> </div>
					<div> <%=dep.getDepartment_name() %> </div>
					<div> <%=dep.getManager_id() %> </div>
					<div> <%=dep.getLocation_id() %> </div>
					
				</div>
				
			<% } %>
			
		<% } %>
	
	</div>
	
</body>
</html>