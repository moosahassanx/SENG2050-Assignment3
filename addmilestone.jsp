<!--
    Assignment 3: AddMilestones.jsp
    Josh R(c3324541), Moosa H (c3331532), Keeylan H ()
    -----------------------------------------------------
    Purpose: 
-->
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<title>University of Newcastle - Add Milestone</title>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/addmilestone.css">
    <script src="https://kit.fontawesome.com/a913ebebd3.js" crossorigin="anonymous"></script>
    <script>
        function validate() 
        {
            var mTitle = document.getElementById("mTitle"); //Grabs the user id from the form
            var description = document.getElementById("description");
            var date = document.getElementById("date");
            var returnStatus = true;
            var messageError = "Sorry but: \n ";
            if (mTitle === null || mTitle.value === "")  //Checks to see if there was actually a name in the input
            { 
                returnStatus = false; //Returns false so it doesn't submit
                messageError += "Please input a title\n"; //Adds to String that will get returned. 
            }
            if (description === null || description.value === "")  //Checks to see if there was actually a name in the input
            { 
                returnStatus = false; //Returns false so it doesn't submit
                messageError += "Please put in a description\n"; //Adds to String that will get returned. 
            }
            if (date === null || date.value === "")
            {
                returnStatus = false;
                messageError += "Please input a date";
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
    <section class="landing-page-main">
        <!--TOP BAR-->
        <div class="top-bar">
            <div id="bar-left">
                <img src="https://www.newcastle.edu.au/__data/assets/image/0011/246881/uon-logo-square.png" alt="" class="box-img">
            </div>
            <div id="bar-middle">
                <h1 id="newcastle-portal">Newcastle Portal</h1>
                <h2 id="bar-text">Group management system.</h2>
                <h3 id="bar-text">Add Milestone</h3>
            </div>
            <div id="bar-right">
            <h2 id="bar-text">${user.getName()}</h2> 
            <c:choose>
                <c:when test = "${user.hasGroup()}">
                    <h2 id="bar-text">${user.getGroup()}</h2>
                </c:when>
                <c:otherwise>
                    <h2 id="bar-text">N/A</h2>
                </c:otherwise>
            </c:choose>
            </div>
            <hr>
        </div>

        <!-- CREATOR FOR MILESTONES -->
        <div class="main-content">
            <!-- BACK BUTTON -->
            <div class="back-div">
                <button id="back-btn" onclick="location.href='hub.jsp';">Back</button>
            </div>

            <!-- MILESTONE FORM -->
            <form action="CreateMilestone" method = "POST" onsubmit = "return validate()">
            <p id="label">Milestone Title</p>
            <p><input id="input-title" type="text" id = "mTitle" name = "milestoneTitle"></p> <!--Milestones have a title, summary and a due date, this is what is featured below. -->

            <p id="label">Summary</p>
            <p><input id="input-message" type="text" id = "summary" name = "description"></p>

            <p id="label">Date</p>
            <p><input id="input-title" type="date" id = "date" name = "date"></p>

            <p><input id="submit" type="submit" VALUE="ADD MILESTONE"></p>
            </form>
        </div>
    </section>
</body>
</html>