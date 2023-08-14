<%@page import="ge.edu.freeuni.models.UserModel"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><%= ((UserModel) request.getAttribute("userdetails")).getUsername() %></title>
</head>
<body>
<%
    UserModel user = (UserModel) request.getAttribute("userdetails");

    if (user != null) {
%>
    <p>Username: <%= user.getUsername() %></p>
    <p>First name: <%= user.getFirstname() %></p>
    <p>Last name: <%= user.getLastname() %></p>
<%
    } else {
%>
    <p>No user found.</p>
<%
    }
%>
</body>
</html>

