<!--
    Assignment 3: BookAppointments.jsp
    Josh R(c3324541), Moosa H (), Keeylan H ()
    -----------------------------------------------------
    Purpose: This JSP allows users to input information relating to Appointments.
    Users are able to select the date, time and reason as to why they want this appointment. 
-->
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<title>University of Newcastle - Book Appointment</title>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/bookappointment.css">
    <script src="https://kit.fontawesome.com/a913ebebd3.js" crossorigin="anonymous"></script>
    <script>
        function validate() 
        {
            var date = document.getElementById("date"); //Grabs the user id from the form
            var time = document.getElementById("time");
            var message = document.getElementById("description")
            var returnStatus = true;
            var messageError = "Sorry but: \n ";
            if (date === null || date.value === "") 
            { 
                returnStatus = false; //Returns false so it doesn't submit
                messageError += "Please input a date\n"; //Adds to String that will get returned. 
            }
            if (time === null || time.value === "") 
            { 
                returnStatus = false; //Returns false so it doesn't submit
                messageError += "Please put in a time\n"; //Adds to String that will get returned. 
            }
            if (message === null || message.value === "") 
            { 
                returnStatus = false; //Returns false so it doesn't submit
                messageError += "Please put in a description\n"; //Adds to String that will get returned. 
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
                <h3 id="bar-text">Send Appointment to ${teachName}</h3> 
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

        <!-- CREATE Appointment FORM -->
        <form action="CreateAppointment" method = "POST" onsubmit = "return validate()">
        <div class="main-content">
            <div class="back-div">
                <button id="back-btn" onclick="location.href='hub.jsp';">Back</button>
            </div>
            
            <p id="label">Date</p> <!--Users are able to select a date, time and leave a message in regards to the appointment that they want. -->
            <p><input id="input-title" type="date" id = "date" name = "date"></p>

            <p id="label">Time</p>
            <p><input id="input-title" type="time" id = "time" name = "time"></p>

            <p id="label">Message</p>
            <p><input id="input-message" type="text" id = "description" name = "description"></p>

            <p><input id="submit" type="submit" value="BOOK APPOINTMENT"></p>
        </div>
        </form>
    </section>


    <!--
    <h1>Newcastle Student</h1>
    <h2>Group management system.</h2>
    <h3>Appointments</h3>

    <h4>Book Appointment with [Teacher Name]</h4>
    <form action="hub.html">
        <label for="">Date</label>
        <input type="date">
        <br>

        <label for="">Time</label>
        <input type="time">
        <br>

        <label for="">Message</label>
        <input type="text">
        <br>

        <input type="submit">
    </form>
    -->
</body>
</html>