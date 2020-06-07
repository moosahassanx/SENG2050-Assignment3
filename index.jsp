<!--
    Assignment 3: Index.jsp
    Josh R(c3324541), Moosa H (c3331532), Keeylan H ()
    -----------------------------------------------------
    Purpose: This will be the main portal page that any user will use to login
    or register an account with. 
-->
<!DOCTYPE html>
<html>
<head>
	<title>University of Newcastle - Portal</title> <!--Title of the page and includes the Stylesheet that will be used. -->
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/indexstyle.css">
    <script type="text/javascript" src="js/javascript.js"></script>
</head>

<body>
	<section class="landing-page-main"> <!--Split into a section to have everything dedicated to the index in one block-->
		<div class="main-content">
            <img src="https://www.newcastle.edu.au/__data/assets/image/0011/246881/uon-logo-square.png" alt="" class="box-img">
            <h1>Newcastle Portal</h1> 
            <p>Group management system.</p>
            <hr>
            <p><button id="btn" value="register" onclick="location.href='register.jsp'">Register</button></p> <!--This allows users to register. -->
        </div>

        <!-- CENTRE PLACED DIV FOR USERS TO  -->
        <div class = "teacher">
            <form action = "login" method="POST" onsubmit = "return loginValidation()">
            <!--These will be passed into the Servlet so it can be queried in the DB-->
            <p><input type="text" placeholder="Username" id="username" name = "username" /></p>
            <p><input type="password" placeholder="Password" id="password" name = "password"></p>

            <!-- LOGIN IS ON THE LEFT -->
            <div class="left">
                <p><input id="btn" type="submit" value = "Login" name="button"/></p>
            </div>
    
            <!-- RESET FORM IS ON THE RIGHT -->
            <div class="right">
                <p><input id="btn" type="reset" value ="Reset"/></p>
            </div>
            </form>
        </div>
        
		<footer id="main-footer">
			<p id="main-footer-text">2020 &copy; Group Portal</p>
		</footer>
	</section>
	
</body>
</html>