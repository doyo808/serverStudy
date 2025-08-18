<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, chap08.dto.EmployeeDto"%>
<%
	List<EmployeeDto> employees = null;
	if (request.getAttribute("employees") != null) {
		employees = (List<EmployeeDto>)request.getAttribute("employees");
	}
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>사원 목록</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/emp/css/emp_list.css">
	
</head>
<body>

	<h3>모든 사원 목록</h3>
	
	<div class=table>
		<% if (employees == null) { %>
			<div>조회된 사원이 없습니다.</div>
		<% } else { %>
			<div class="row header">
				<div class="employee_id title"> id </div>
				<div class="first_name title"> first_name </div>
				<div class="last_name title"> last_name </div>
				<div class="salary title"> salary </div>
				<div class="commission_pct title"> pct </div>
				<div class="phone_number title"> phone </div>
			</div>
			<% for (EmployeeDto emp : employees) { %>
				<div class="row">
					<div class="employee_id"> <%=emp.getEmployee_id() %> </div>
					<div class="first_name"> <%=emp.getFirst_name() %> </div>
					<div class="last_name"> <%=emp.getLast_name() %> </div>
					<div class="salary"> <%=emp.getSalary() %> </div>
					<div class="commission_pct"> <%=emp.getCommission_pct() %> </div>
					<div class="phone_number"> <%=emp.getPhone_number() %> </div>
				</div>
			<% } %>
		<% } %>
	</div>
	
	
</body>
</html>