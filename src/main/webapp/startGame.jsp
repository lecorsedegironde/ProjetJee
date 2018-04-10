<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%Integer erreur = (Integer) request.getAttribute("erreur");%>

<!DOCTYPE HTML>
<!--
Phantom by HTML5 UP
html5up.net | @ajlkn
Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
    <title>Choix du jeu</title>
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
            <h1>Choix du jeu</h1>
            <% if (erreur != null) {%>
            <p>Veuilliez selectionnez un jeu</p>
            <%}%>
            <form action="./playgame" method="post">
                <div class="12  u 12u$(xsmall)">
                    <p>
                        <c:forEach var="game" items="${listGames}">
                        <input id="game${game.name}" name="game" value="${game.name}" type="radio" checked>
                        <label for="game${game.name}">${game.name} : ${gamePlayers[game.name]} joueurs connectés</label>
                        <br>
                        </c:forEach>
                </div>
                <br/>
                <ul class="actions">
                    <li><input type="submit" value="Start" title="Valider pour aller au jeu sélectionné"/></li>
                </ul>
                </p>
            </form>
        </div>

        <%@include file="footer.html" %>

    </div>

    <%@include file="scripts.html" %>

</body>
</html>