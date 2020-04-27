<%-- 
    Document   : search
    Created on : Mar 22, 2020, 10:00:53 PM
    Author     : tranb
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Articles</title>
        <link href="css/seach.css" rel="stylesheet" />
    </head>
    <body>
        <t:layout>
            <c:choose>
                <c:when test="${error == null}">
                    <c:forEach items="${requestScope.list}" var="p">
                        <div class="_1news">
                            <a class="text-color link text-title" href="./detail?id=${p.id}">${p.title}</a>
                            <div class="description">
                                <img src="${p.imgUrl}" alt="">
                                <div class="text">
                                    <p class="p-size">${p.shortDes}</p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <div class="pager">
                        <c:forEach var="p" begin="1" end="${pages}">
                            <c:if test="${cur == p}">
                                <span class="black">${p}</span>
                            </c:if>
                            <c:if test="${cur != p}">
                                <a href="./search?page=${p}&keyword=${key}" class="link">${p}</a>
                            </c:if>
                        </c:forEach>
                    </div>
                </c:when>
                <c:otherwise>
                    <h1>${error}</h1><br>
                    <h2>Click <a href="./home">here</a> to go home</h2>
                </c:otherwise>
            </c:choose>
        </t:layout>
    </body>
</html>
