<%@page import="com.cbsbankui.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Dashboard</h1>
<% User user = (User)session.getAttribute("user"); %>
Welcome <%=user.getName() %>
</body>
</html>