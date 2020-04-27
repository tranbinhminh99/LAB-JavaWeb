<%-- 
    Document   : takeQuiz
    Created on : Mar 10, 2020, 10:02:32 PM
    Author     : tranb
--%>

<%@page import="Entity.Option"%>
<%@page import="Entity.Question"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Entity.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="./CSS/css1.css" rel="stylesheet">
        <link href="CSS/takeQuizCss.css" rel="stylesheet" type="text/css"/>

        <% Account acc = (Account) request.getSession().getAttribute("acc"); %>
        <% String notification = (String) request.getAttribute("notification"); %>


    </head>
    <body>
        <div class="tong">
            <jsp:include page="header.jsp"/>
            <div class="login">
                <%if (acc != null) {%>
                <h3 class="text-color">Welcome </h3> <a class="color-user"><%= acc.getUserName()%></a>
                <form method="post" action="takeQuiz">
                    <label class="text-color">Enter number of questions: </label><br>
                    <input class="num" name="numberQ" type="number" required="">
                    <button type="submit" class="button button-start">Start</button>
                </form>
                <div>
                    <%if (notification != null) { %>
                    <a>Not enough question for you</a>
                    <%}%>       
                </div>
                <%} else {%>
                <h3>Access Denied!</h3>
                <%}%>
            </div>
        </div>

    </body>
</html>
