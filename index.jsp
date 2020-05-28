<!DOCTYPE html>
<html>
<head>
	<title>University of Newcastle - Portal</title>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/indexstyle.css">

    <script>
        function myFunction(){
            document.getElementById("popUp").style.display="block";
        }
    </script>
</head>

<body>
	<section class="landing-page-main">
		<div class="main-content">
            <img src="https://www.newcastle.edu.au/__data/assets/image/0011/246881/uon-logo-square.png" alt="" class="box-img">
            <h1>Newcastle Portal</h1>
            <p>Group management system.</p>
            <hr>
            <p><button id="btn" value="register" onclick="myFunction()">Register</button></p>
        </div>

        <div class = "teacher">
            <form action = "login" method="POST">
            <!--Use those IDS in order to transfer across to the servlet, will we need to make it a form?-->
            <p><input type="text" placeholder="Username" id="username" name = "username" /></p>
            <p><input type="password" placeholder="Password" id="password" name = "password"></p>

            <div class="left">
                <p><input id="btn" type="submit" value = "Login" name="button"/></p>
            </div>
    
            <div class="right">
                <p><input id="btn" type="reset" value ="Reset"/></p>
            </div>
            </form>

            <div class="loginPopup">
                <div style="display:none;"class="registrationForm" id="popUp">
                    <form action ="login" method="POST" class="formContainer">
                        <p><input type="text" placeholder="Username" id="username" value="username" name="username2"/><br></p>
                        <p><input type="password" placeholder="Password" id="password" value="password" name="password2"><br></p>
                        <p><input type="text" placeholder="First Name" id="FirstName"/><br></p>
                        <p><input type="text" placeholder="Last Name" id="LastName"/><br></p>
                        <p><input type="text" placeholder="Phone Number" id="PhoneNo"/><br></p>
                        <p><input id="submit" type="submit" value="register" name="button"></p>
                    </form>
                </div>
            </div>
        </div>
        
        

		<footer id="main-footer">
			<p id="main-footer-text">2020 &copy; University of Newcastle</p>
		</footer>
	</section>
	
</body>
</html>