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
    <title>Gestion des jeux</title>
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
            <h1>Liste des jeux</h1>
            <div class="row uniform">
                <table>
                    <tr>
                        <th>Jeu</th>
                        <th>Nombre de joueurs connectés</th>
                        <th>Supprimer le jeu</th>
                    </tr>
                    <c:forEach var="game" items="${listGames}">
                        <tr>
                            <td>${game.name}</td>
                            <td>${gamePlayers[game.name]}</td>
                            <form action="./removeGame" method="get">
                                <td>
                                    <button type="submit" name="gameName" value="${game.name}">Supprimer</button>
                                </td>
                            </form>
                        </tr>
                    </c:forEach>
                </table>
            </div>

            <form action="./game" method="post">
                <div class="6u 12u$(xsmall)">
                    <input type="text" name="gameName" class="champConnexion" placeholder="Nom du jeu">
                </div>
                <br/>
                <ul class="actions">
                    <li><input type="submit" value="Ajouter" class="special"></li>
                </ul>
            </form>
        </div>
    </div>

    <%@include file="footer.html" %>

</div>

<%@include file="scripts.html" %>

</body>
</html>