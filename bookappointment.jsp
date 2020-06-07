<!--
    Assignment 3: BookAppointments.jsp
    Josh R(c3324541), Moosa H (c3331532), Keeylan H ()
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
    <script type="text/javascript" src="js/javascript.js"></script>
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
        <div class="main-content">
            <!-- BACK BUTTON -->
            <div class="back-div">
                <button id="back-btn" onclick="location.href='back';">Back</button>
            </div>

            <form action="CreateAppointment" method = "POST" onsubmit = "return appointmentValidate()"></form>
            <!--Users are able to select a date, time and leave a message in regards to the appointment that they want. -->
            <p id="label">Date</p> 
            <p><input id="input-title" type="date" id = "date" name="date"></p>

            <p id="label">Time</p>
            <p><input id="input-title" type="time" id = "time" name="time"></p>

            <p id="label">Message</p>
            <p><input id="input-message" type="text" id = "description" name = "description"></p>

            <p><input id="submit" type="submit" value="BOOK APPOINTMENT"></p>
            </form>
        </div>
    </section>
</body>
</html>