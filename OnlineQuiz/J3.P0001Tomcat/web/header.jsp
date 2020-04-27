<%-- 
    Document   : header
    Created on : Feb 17, 2020, 8:42:20 PM
    Author     : tranb
--%>

<%@page import="Entity.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="./CSS/headerCss.css" rel="stylesheet">
        <% Account acc = (Account) request.getSession().getAttribute("acc"); %>
    </head>
    <body class="bheader">
        <div class="header">
            <div class="inside">
                <%if (acc != null) { %>
                <a href="home.jsp">Home</a>
                <%}else {%>
                <a href="login">Home</a>
                <%}%>
                
                <a href="takeQuiz">Take Quiz</a>
                <a href="makeQuiz.jsp">Make Quiz</a>
                <a href="manager">Manage Quiz</a>
                <%if (acc != null) {%>
                <a href="logout">Log Out</a>
                <%}%>
            </div>
        </div>
    </body>
</html>
