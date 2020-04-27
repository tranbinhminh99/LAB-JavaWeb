<%-- 
    Document   : makeQuiz
    Created on : Feb 28, 2020, 8:27:37 AM
    Author     : tranb
--%>

<%@page import="Entity.Option"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Entity.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="./CSS/css1.css" rel="stylesheet">
        <link href="CSS/makeQuizCss.css" rel="stylesheet" type="text/css"/>
        <% Account acc = (Account) request.getSession().getAttribute("acc"); %>
        <% String message = (String) request.getAttribute("message"); %>
        <% String question = (String) request.getAttribute("question"); %>
        <% ArrayList<Option> listOption = (ArrayList<Option>) request.getAttribute("listOption");  %>
        <% Boolean flaq = (Boolean) request.getAttribute("flaqcbx"); %>
    </head>
    <body>
        <div class="tong" id="make">
            <jsp:include page="header.jsp"/>
            <div class="login">
                <%if (acc != null && acc.getA_Type().getType().equals("Teacher")) { %>
                <form action="makeQuiz" method="post">
                    <table>
                        <tr>
                            <td> <label class="text-color">Question: </label></td>
                            <td> <textarea rows="7" cols="70" name="question" required=""><%if(question!=null){ %><%=question%><%}%></textarea></td>
                        </tr>
                        <tr>
                            <td>  <label class="text-color" for="option1">Option1: </label></td>
                            <td> <textarea  rows="3" cols="70" class="form-option1" required="" name="option1"><%if(listOption!=null){ %><%=listOption.get(0)%><%}%></textarea></td>
                        </tr>
                        <tr>
                            <td> <label for="option2" class="text-color">Option2: </label></td>
                            <td> <textarea  rows="3" cols="70" class="form-option2" required="" name="option2"><%if(listOption!=null){ %><%=listOption.get(1)%><%}%></textarea></td>
                        </tr>
                        <tr>
                            <td> <label for="option3" class="text-color">Option3: </label></td>
                            <td> <textarea  rows="3" cols="70" class="form-option3" required="" name="option3"><%if(listOption!=null){ %><%=listOption.get(2)%><%}%></textarea></td>
                        </tr>
                        <tr>
                            <td> <label for="option4" class="text-color">Option4: </label></td>
                            <td> <textarea  rows="3" cols="70" class="form-option4" required="" name="option4"><%if(listOption!=null){ %><%=listOption.get(3)%><%}%></textarea></td>
                        </tr>
                    </table>
                    <div>
                        <label for="answer" class="text-color">Answer(s): </label>
                        <input type="checkbox" id="option1" name="cbxo1" <%if(flaq!=null){%>checked=""<%}%> value="true">
                        <label for="option1" class="text-color">Option 1</label>
                        <input type="checkbox" id="option2" name="cbxo2" <%if(flaq!=null){%>checked=""<%}%> value="true">
                        <label for="option2" class="text-color">Option 2</label>
                        <input type="checkbox" id="option3" name="cbxo3" <%if(flaq!=null){%>checked=""<%}%> value="true">
                        <label for="option3" class="text-color">Option 3</label>
                        <input type="checkbox" id="option4" name="cbxo4" <%if(flaq!=null){%>checked=""<%}%> value="true">
                        <label for="option4" class="text-color">Option 4</label>
                    </div>
                    <div>
                        <button type="submit" class="button">Save</button>
                    </div>
                    <% if (message != null) {%>
                    <p><%= message %></p>
                    <%   }
                    %>
                </form>
                <%} else {%>
                <h3>Access Denied !</h3>
                <%}%>
            </div>
        </div>
    </body>
</html>
