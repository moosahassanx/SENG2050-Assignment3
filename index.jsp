<!DOCTYPE html>
<html>
<head>
	<title>University of Newcastle - Portal</title>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/indexstyle.css">
    <script src="https://kit.fontawesome.com/a913ebebd3.js" crossorigin="anonymous"></script>
</head>

<body>
	<section class="landing-page-main">
		<div class="main-content">
            <img src="https://www.newcastle.edu.au/__data/assets/image/0011/246881/uon-logo-square.png" alt="" class="box-img">
            <h1>Newcastle Portal</h1>
            <p>Group management system.</p>
            <hr>
        </div>

        <div class = "teacher">
        <form action = "login" method="POST">
        <!--Make a form, so we can pass through username and passwords through, change to one button, pass data through. -->
            <input type="text" placeholder="Username" id="username" /> <br>
            <input type="password" placeholder="Password" id="password"> <!--Use those IDS in order to transfer across to the servlet, will we need to make it a form?-->
            <br><br>
        </div>
        <div class = "left">
            <input type="submit" value = "Login" />
        </div>
        <div class = "right">
            <input type = "reset" value ="Reset" />
        </div>
        </form>
		<footer id="main-footer">
			<p id="main-footer-text">2020 &copy; University of Newcastle</p>
		</footer>
	</section>
	
</body>
</html>