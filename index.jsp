<!DOCTYPE html>
<html>
<head>
	<title>University of Newcastle - Portal</title>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/indexstyle.css">

    <script type="text/javascript" src="js/javascript.js"></script>
</head>

<body>
	<section class="landing-page-main">
		<div class="main-content">
            <img src="https://www.newcastle.edu.au/__data/assets/image/0011/246881/uon-logo-square.png" alt="" class="box-img">
            <h1>Newcastle Portal</h1>
            <p>Group management system.</p>
            <hr>
        </div>

        <form action = "login" method="POST">
        <div class = "teacher">
            <!--Use those IDS in order to transfer across to the servlet, will we need to make it a form?-->
            <p><input type="text" placeholder="Username" id="username" /></p>
            <p><input type="password" placeholder="Password" id="password"></p>

            <div class="left">
                <p><input id="btn" type="submit" value = "Login"/></p>
            </div>
    
            <div class="right">
                <p><input id="btn" type = "reset" value ="Reset"/></p>
            </div>
        </div>
        </form>

        <div class ="RegisterDiv">
            <div class="bottom">
                <p><button id = "btn" value="register" onclick="registerForm()">Register</button></p>
            </div>
        </div>

        <div style="display:none;"class="registrationForm" id="popUp">
            <form action ="register" method="POST" class="formContainer">
                <label>Username</label><br>
                <input type="text" placeholder="Username" id="username"/><br>
                <label>Password</label><br>
                <input type="password" placeholder="Password" id="password"><br>
                <label>First Name</label><br>
                <input type="text" placeholder="FirstName" id="FirstName"/><br>
                <label>Last Name</label><br>
                <input type="text" placeholder="LastName" id="LastName"/><br>
                <label>Phone No</label>
                <input type="text" placeholder="PhoneNo" id="PhoneNo"/><br>
            </form>
        </div>
        

		<footer id="main-footer">
			<p id="main-footer-text">2020 &copy; University of Newcastle</p>
		</footer>
	</section>
	
</body>
</html>