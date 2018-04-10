<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.lang.*" %>
<!DOCTYPE HTML>
<!--
Phantom by HTML5 UP
html5up.net | @ajlkn
Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
    <title>Generic - Phantom by HTML5 UP</title>
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
            <h1>Liste des parties en cours</h1>
            <div class="row uniform">
                <table>
                    <tr>
                        <th>Bouton d'action</th>
                        <th>Jeu en cours</th>
                        <th>Pseudo du joueur</th>
                        <th>Date / Heure début</th>
                    </tr>
                    <c:forEach var="userGame" items="${usersGames}">
                        <tr>
                            <form action="./usersgames" method="get">
                                <td><button type="submit" name="stop" value="${userGame.id}"/>Terminer partie</button></td>
                            </form>
                            <td>${userGame.game.name}</td>
                            <td>${userGame.user.login}</td>
                            <td>${userGame.startDate.dayOfMonth}/${userGame.startDate.monthValue}/${userGame.startDate.year} : ${userGame.startDate.hour}:${userGame.startDate.minute}</td>
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