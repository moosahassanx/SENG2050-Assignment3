<!--
    Assignment 3: Index.jsp
    Josh R(c3324541), Moosa H (), Keeylan H ()
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

    <script>
        function myFunction(){ //Javascript function to display registering a user
            document.getElementById("popUp").style.display="block";
        }
        function validate() 
        {
                var name = document.getElementById("username"); //Grabs the user id from the form
                var pass = document.getElementById("password");
                var returnStatus = true;
                var messageError = "Sorry but: \n ";
                if (name === null || name.value === "")  
                { 
                    returnStatus = false; //Returns false so it doesn't submit
                    messageError += "Your UserID had no input, please input something for the UserID. \n"; //Adds to String that will get returned. 
                }
                if (pass === null || pass.value === "")  
                { 
                    returnStatus = false; //Returns false so it doesn't submit
                    messageError += "Please put in a password\n"; //Adds to String that will get returned. 
                }
                if (!returnStatus) 
                {
                    alert(messageError); //Returns the string in the alert
                }
                return returnStatus; //Returns the result Status. 
            }
    </script>
</head>

<body>
	<section class="landing-page-main"> <!--Split into a section to have everything dedicated to the index in one block-->
		<div class="main-content">
            <img src="https://www.newcastle.edu.au/__data/assets/image/0011/246881/uon-logo-square.png" alt="" class="box-img">
            <h1>Newcastle Portal</h1> 
            <p>Group management system.</p>
            <hr>
            <p><button id="btn" value="register" onclick="myFunction()">Register</button></p> <!--This allows users to register. -->
        </div>

        <div class = "teacher">
            <form action = "login" method="POST" onsubmit = "return validate()">
            <!--These will be passed into the Servlet so it can be queried in the DB-->
            <p><input type="text" placeholder="Username" id="username" name = "username" /></p>
            <p><input type="password" placeholder="Password" id="password" name = "password"></p>

            <div class="left">
                <p><input id="btn" type="submit" value = "Login" name="button"/></p>
            </div>
    
            <div class="right">
                <p><input id="btn" type="reset" value ="Reset"/></p>
            </div>
            </form>
            <!--This popup is used in order to allow new users to register an account on the website. -->
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