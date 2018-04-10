<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.lang.*" %>
<%@ page import="fr.grp404.projetjee.persistence.domain.Game" %>
<%@ page import="java.util.List" %>
<%
    List<Game> games = (List<Game>) request.getAttribute("games");
    String error = (String) request.getAttribute("error");
    String success = (String) request.getAttribute("success");
%>
<!DOCTYPE HTML>
<!--
Phantom by HTML5 UP
html5up.net | @ajlkn
Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
    <title>Inscription</title>
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
            <h1>Inscription</h1>
            <p>
                <% if (error != null) { %>
                <%=error%>
                <% }
                    if (success != null) { %>
                <%=success%>
                <% } %>
            </p>
            <form method="post" action="">
                <div class="row uniform">
                    <div class="6u 12u$(xsmall)">
                        <input name="login" id="login" placeholder="Login" type="text" required>
                    </div>
                    <div class="6u$ 12u$(xsmall)">
                        <input name="pwd" id="pwd" placeholder="Mot de passe" type="password" required>
                    </div>
                    <div class="6u 12u$(small)">
                        <label>Jeux préférés</label><br>
                        <%
                            int i = 0;
                            for (Game game:games) {
                        %>
                        <input id="prefGame<%=i %>" name="prefGame" value="<%=game.getName() %>" type="checkbox">
                        <label for="prefGame<%=i %>"><%=game.getName() %></label>
                        <%
                                i++;
                            }
                        %>
                    </div>
                    <div class="6u$ 12u$(xsmall)">
                        <input type="email" name="mail" id="mail" placeholder="Email" required>
                    </div>
                    <div class="6u$ 12u$(xsmall)">
                        <input type="date" name="birthDate" id="birthDate" placeholder="yyyy-mm-dd" required>
                    </div>
                    <div class="12u$">
                        <ul class="actions">
                            <li><input class="special" value="Inscription" type="submit"></li>
                        </ul>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <%@include file="footer.html" %>

</div>

<%@include file="scripts.html" %>
</body>
</html>