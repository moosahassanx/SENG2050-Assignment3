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

        <div class="teacher">
        <!--Make a form, so we can pass through username and passwords through, change to one button, pass data through. -->
            <p><input type="text" placeholder="Username" id="username"><br> <!--Use those IDS in order to transfer across to the servlet, will we need to make it a form?-->
                <input type="password" placeholder="Password" id="password">
            </p><br><br>

            <div class="left">
                <p><a href="#" address="true">Login as Teacher</a></p>
            </div>
            <div class="right">
                <p><a href="hub.jsp" address="true">Login as Student</a></p>
            </div>
        </div>

		<footer id="main-footer">
			<p id="main-footer-text">2020 &copy; University of Newcastle</p>
		</footer>
	</section>
	
</body>
</html>