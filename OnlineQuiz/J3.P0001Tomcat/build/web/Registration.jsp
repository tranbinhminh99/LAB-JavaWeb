<%-- 
    Document   : Resgistration
    Created on : Feb 26, 2020, 8:13:20 AM
    Author     : tranb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="./CSS/css1.css" rel="stylesheet">
        <link href="./CSS/registerCss.css" rel="stylesheet">
        <%
            String user = (String) request.getAttribute("user");
            String email = (String) request.getAttribute("email");
            String type = (String) request.getAttribute("type");
            String notification = (String) request.getAttribute("notification");
        %>
    </head>
    <body>

        <div class="tong">
            <jsp:include page="header.jsp"/>
            <div class="login" >
                <h3 class="header font-wight">Registration Form</h3>
                <form action="register" method="post">
                    <div>
                        <label for="user" class="text-color">User Name: </label>
                        <input type="text" id="user" required="" name="user" <%if (user != null) {%> value="<%= user%>" <%}%> class="form-user color-textbox" >   
                    </div>

                    <div>
                        <label for="pass" class="text-color">Password: </label>
                        <input type="password" id="pass" required="" name="pass" class="form-pass color-textbox" >   
                    </div>

                    <div>
                        <label for="type" class="text-color">User Type:</label>
                        <select id="type" class="cbxtype" name="type">
                            <option value="2">Teacher</option>
                            <option value="1" <%if (type != null && type.equals("1")) {%> selected="" <%}%> >Student</option>
                        </select>
                    </div>

                    <div>
                        <label class="text-color" for="email">Email: </label>
                        <input type="email" id="email" required="" name="email" <%if (email != null) {%> value="<%=email%>" <%}%>  class="form-email color-textbox">
                    </div>

                    <div>
                        <button type="submit" class="button">Register</button>
                    </div>
                </form>
                <%if (notification != null) {%>
                <a><%= notification%></a>
                <%}%>
            </div>
    </body>
</html>
