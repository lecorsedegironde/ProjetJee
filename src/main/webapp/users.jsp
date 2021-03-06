<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<!--
Phantom by HTML5 UP
html5up.net | @ajlkn
Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
    <title>Liste des utilisateurs</title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <!--[if lte IE 8]>
    <script src="assets/js/ie/html5shiv.js"></script><![endif]-->
    <link rel="stylesheet" href="assets/css/main.css"/>
    <!--[if lte IE 9]>
    <link rel="stylesheet" href="assets/css/ie9.css"/><![endif]-->
    <!--[if lte IE 8]>
    <link rel="stylesheet" href="assets/css/ie8.css"/><![endif]-->
</head>
<body>
<!-- Wrapper -->
<div id="wrapper">

    <%@include file="header.jsp" %>

    <!-- Main -->
    <div id="main">
        <div class="inner">
            <h1>Liste des utilisateurs</h1>
            <div class="row uniform">
                <table>
                    <tr>
                        <th>Pseudo</th>
                        <th>Date d’inscription</th>
                        <th>Bannir/Débannir</th>
                    </tr>
                    <c:forEach var="user" items="${users}">
                        <tr>
                            <td><a href="./member?login=${user.login}">${user.login}</a></td>
                            <td>${user.birthDate.dayOfMonth}/${user.birthDate.monthValue}/${user.birthDate.year}</td>
                            <c:choose>
                                <c:when test="${user.role eq 'USER'}">
                                    <form action="./users" method="get">
                                        <c:if test="${user.ban == false}">
                                            <td>
                                                <button type="submit" name="ban" value="${user.id}">Bannir</button>
                                            </td>
                                        </c:if>
                                        <c:if test="${user.ban == true}">
                                            <td>
                                                <button type="submit" name="unban" value="${user.id}">Débannir</button>
                                            </td>
                                        </c:if>
                                    </form>
                                </c:when>
                                <c:when test="${user.role eq 'ADMIN'}">
                                    <td>Impossible</td>
                                </c:when>
                            </c:choose>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>

    <%@include file="footer.html" %>

</div>

<%@include file="scripts.html" %>

</body>
</html>