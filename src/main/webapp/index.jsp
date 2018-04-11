<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="random" class="java.util.Random" scope="application"/>
<!DOCTYPE HTML>
<!--
Phantom by HTML5 UP
html5up.net | @ajlkn
Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
    <title>Jouez à des jeux !</title>
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
            <header>
                <h1>Bienvenue sur le site du groupe 404 !</h1>
                <p>Nous mettons de super jeux à dispositions pour tous types d'écrans ! Le groupe 404 vous offre le
                    meilleur des jeux en ligne et les catégories les plus populaires telles que les
                    jeux de cuisine, les jeux de foot et les jeux d'habillage, ainsi que les jeux inspirés de vos
                    films.</p>
                <p>Connectez-vous en cliquant sur l'un des jeux</p>
            </header>
            <section class="tiles">
                <c:forEach var="game" items="${games}">
                    <c:set var="number" scope="page" value="${random.nextInt(9)+1}"/>
                    <article class="style${number}">
									<span class="image">
										<img src="images/pic0${number}.jpg" alt="jeu"/>
									</span>
                        <a href="./connection.jsp">
                            <h2>${game.name}</h2>
                            <div class="content">
                                <p>${game.name} avec ${gamePlayers[game.name]} utilisateur(s) en ligne&nbsp;!</p>
                            </div>
                        </a>
                    </article>
                </c:forEach>
            </section>
        </div>
    </div>

    <%@include file="footer.html" %>

</div>

<%@include file="scripts.html" %>

</body>
</html>
