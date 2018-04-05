<%String login= (String) session.getAttribute("login");%>
<%Boolean admin = (Boolean) session.getAttribute("admin");%>
<%Integer erreur = (Integer) request.getAttribute("erreur");%>
<!DOCTYPE HTML>
<!--
	Phantom by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>Phantom by HTML5 UP</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
		<link rel="stylesheet" href="assets/css/main.css" />
		<!--[if lte IE 9]><link rel="stylesheet" href="assets/css/ie9.css" /><![endif]-->
		<!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->
	</head>
	<body>
		<!-- Wrapper -->
			<div id="wrapper">

				<%@include file="header.html" %>

				<!-- Main -->
					<div id="main">
						<div class="inner">
							<section class="connexion">
                                <% if(login!=null){%>
                                    <%if(admin!=null){%>
                                        <p>Vous etes l'admministrateur <%= login%></p>
                                    <%} else { %>
                                        <p>Vous etes <%= login%></p>
                                    <%}%>
                                <%}%>
                                <% if(erreur!=null){%>
                                    <p>Le login et le mot de passe saisie sont incorrect</p>
                                <%}%>
                                <form action="./signin" method="post">
                                    <div class="6u 12u$(xsmall)">
                                        <input type="text" name="login" class = "champConnexion" placeholder="Login">
                                    </div>
                                    <div class="6u 12u$(xsmall)">
                                        <input type="password" name="password" class = "champConnexion" placeholder="Password">
                                    </div>
                                    <br/>
                                    <ul class="actions">
                                        <li><input type="submit" value="Connexion" class = "special"></li>
                                    </ul>
                                </form>
							<!--</section>
						</div>-->
					</div>

				<%@include file="footer.html" %>

			</div>

		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/skel.min.js"></script>
			<script src="assets/js/util.js"></script>
			<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
			<script src="assets/js/main.js"></script>

	</body>
</html>