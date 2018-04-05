<!DOCTYPE HTML>
<!--
	Phantom by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>Generic - Phantom by HTML5 UP</title>
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
							<h1>Inscription</h1>
							<form method="post" action="#">
								<div class="row uniform">
									<div class="6u 12u$(xsmall)">
										<input name="login" id="login" placeholder="Login" type="text">
									</div>
									<div class="6u$ 12u$(xsmall)">
										<input name="pwd" id="pwd" placeholder="Password" type="password">
									</div>
									<div class="12u$">
										<div class="select-wrapper">
											<select name="prefGame" id="prefGame">
												<option value="0">- Jeu préféré -</option>
												<option value="1">Tetris</option>
												<option value="2">Mario</option>
												<option value="3">Rayman</option>
												<option value="4">J'en ai pas</option>
											</select>
										</div>
									</div>
									<div class="6u$ 12u$(xsmall)">
										<input type="email" name="mail" id="mail" placeholder="Email" />
									</div>
									<div class="6u$ 12u$(xsmall)">
										<input type="date" name="birthDate" id="birthDate">
									</div>
									<div class="12u$">
										<ul class="actions">
											<li><input value="Send Message" class="special" type="submit"></li>
											<li><input value="Reset" type="reset"></li>
										</ul>
									</div>
								</div>
							</form>
						</div>
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