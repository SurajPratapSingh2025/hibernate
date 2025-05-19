<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.hiber.pagination.Employee, com.hiber.pagination.Pagination, java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- Records -->
	<%
		String getstart=request.getParameter("start");
	
		int start=0;
		if(getstart!=null){
			start=Integer.parseInt(getstart);
		}
		Pagination p = new Pagination();
    	List<Employee> list = p.readData(start);
		for(Employee e : list){
	%>	
		ID:<%=e.getEid() %>
		NAME:<%=e.getEname() %>
		ADDRESS:<%=e.getEaddress() %>
		SALARY:<%=e.getEsalary() %>
		<br><br>
		<%
			start++;
			}
		%>
		
		<form action="index.jsp" method="post">
			<input type="hidden" name="start" value="<%=start %>">
			<button>Next</button>
		</form><br>
		<form action="index.jsp" method="post">
			<input type="hidden" name="start" value="<%=start-8 %>">
			<button>Prev</button>
		</form>
	
</body>
</html>


