<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.lang.*" %>
<%
    String login = (String) session.getAttribute("login");
    Boolean admin = (Boolean) session.getAttribute("admin");
%>
<!-- Header -->
<header id="header">
    <div class="inner">

        <!-- Logo -->
        <a href="./" class="logo">
            <span class="symbol"><img src="images/logo.svg" alt=""/></span><span class="title">Groupe 404</span>
        </a>

        <!-- Nav -->
        <nav>
            <ul>
                <li><a href="#menu">Menu</a></li>
            </ul>
        </nav>

    </div>
</header>

<!-- Menu -->
<nav id="menu">
    <h2>Menu</h2>
    <ul>
        <li><a href="./">Home</a></li>
        <% if (login == null) { %>
            <li><a href="./signin">Connexion</a></li>
            <li><a href="./signup">Inscription</a></li>
        <% }else{ %>
            <li><a href="./logout">Deconnexion</a></li>
        <% } %>
    </ul>
</nav>