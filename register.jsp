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
        </div>

        <div class = "teacher">
            <form action = "login" method="POST" onsubmit = "return registerValidation()">
                <p><input type="text" id="username" value="username" name="username2"/><br></p>
                <p><input type="password" id="password" value="password" name="password2"><br></p>
                <p><input type="text" placeholder="First Name" id="FirstName"/><br></p>
                <p><input type="text" placeholder="Last Name" id="LastName"/><br></p>
                <p><input type="text" placeholder="Phone Number" id="PhoneNo"/><br></p>
                <p><input id="submit" type="submit" value="register" name="button"></p><p><input id="btn" type="reset" value ="Reset"/></p>
            </form>
        </div>
		<footer id="main-footer">
			<p id="main-footer-text">2020 &copy; University of Newcastle</p>
		</footer>
	</section>
	
</body>
</html>