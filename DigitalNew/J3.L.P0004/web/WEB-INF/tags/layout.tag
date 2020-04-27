<%-- 
    Document   : layout
    Created on : Mar 20, 2020, 11:04:50 PM
    Author     : Acer
--%>

<%@tag description="Layout Tag" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Digital News</title>
        <link rel="stylesheet" href="./css/layout.css">
        <script src="./js/input.js" type="text/javascript"></script>
    </head>
    
    <body>
        <div class="container">
            <div class="border">
                <div class="preheader bg-no-repeat">
                </div><!--end preheader-->
                <div class="header bg-no-repeat">
                    <div class="inside">
                        <p class="title ml-20">My Digital News</p>
                    </div>
                </div><!--end header-->
                <div class="navbar">
                    <div class="inside">
                        <ul>
                            <li class="list-style-none ml-20"><a href="./home" class="link">News</a></li>
                        </ul>
                    </div>
                </div><!--end navbar-->
                <div class="main">
                    <div class="inside">
                        <div class="content">
                            <jsp:doBody/>
                        </div><!--end content-->
                        <div class="aside">
                            <div class="tren"> 
                                <p class="text-color text-title">Digital News</p>
                                <a href="./detail?id=${news.id}" class="link black"><p class="short">${news.shortDes}</p></a>
                            </div>
                            <div class="giua">
                                <p class="text-color text-title">Search</p>
                                <form method="get" action="./search">
                                    <input type="text" name="keyword" value="${key}" id="text">
                                    <input type="submit" value="Go" class="button" onclick="return onClickGo()">
                                </form>
                            </div>
                            <div class="duoi">
                                <p class="text-color text-title">Last Article</p>
                                <div class="khoitin">
                                    <ul>
                                        <c:forEach items="${requestScope.articles}" var="p">
                                            <li class="list-style-none">
                                                <a href="./detail?id=${p.id}" class="text-color">${p.title}</a>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </div> <!--end aside-->
                    </div>
                </div><!--end main-->
                <div class="footer bg-no-repeat">
                </div> <!--end footer-->
            </div>
        </div>
    </body>

</html>