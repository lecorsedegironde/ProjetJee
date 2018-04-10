<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.lang.*" %>
<%@ page import="fr.grp404.projetjee.persistence.domain.Game" %>
<%@ page import="java.util.List" %>
<%
    String email = (String) request.getAttribute("email");
    List<Game> games = (List<Game>) request.getAttribute("games");
    List<Game> prefGames = (List<Game>) request.getAttribute("prefGames");
    String birthdate = (String) request.getAttribute("birthdate");
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
            <h1>Informations</h1>
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
                        <input name="login" id="login" placeholder="Login" type="text" value="<%=login %>" required>
                    </div>
                    <div class="6u$ 12u$(xsmall)">
                        <input name="OldPwd" id="OldPwd" placeholder="Old password" type="password" required>
                    </div>
                    <div class="6u$ 12u$(xsmall)">
                        <input name="NewPwd" id="NewPwd" placeholder="New password" type="password" required>
                    </div>
                    <div class="6u 12u$(small)">
                        <label>Jeux préférés</label><br>
                        <%
                            int i = 0;
                            for (Game game:games) {
                        %>
                        <input id="prefGame<%=i %>" name="prefGame" value="<%=game.getName() %>" type="checkbox" <% if(prefGames.contains(game)){ %> <%="checked" %> <% } %>>
                        <label for="prefGame<%=i %>"><%=game.getName() %></label>
                        <%
                                i++;
                            }
                        %>
                    </div>
                    <div class="6u$ 12u$(xsmall)">
                        <input type="email" name="mail" id="mail" placeholder="Email" value="<%=email %>" required>
                    </div>
                    <div class="6u$ 12u$(xsmall)">
                        <input type="date" name="birthDate" id="birthDate" value="<%=birthdate %>" required>
                    </div>
                    <div class="12u$">
                        <ul class="actions">
                            <li><input class="special" value="Mettre à jour" type="submit"></li>
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