<!--
    Assignment 3: Appointments.jsp
    Josh R(c3324541), Moosa H (c3331532), Keeylan H ()
    -----------------------------------------------------
    Purpose: Shows all the current teachers that are available for appointments and shows
    all the current appointments that have been made. 
-->
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<title>University of Newcastle - Appointments</title>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/appointments.css">
    <script type="text/javascript" src="js/javascript.js"></script>
</head>

<body>
    <!--TOP BAR -->
    <section class="landing-page-main">
        <div class="top-bar">
            <div id="bar-left">
                <img src="https://www.newcastle.edu.au/__data/assets/image/0011/246881/uon-logo-square.png" alt="" class="box-img">
            </div>
            <div id="bar-middle">
                <h1 id="newcastle-portal">Newcastle Portal</h1>
                <h2 id="bar-text">Group management system.</h2>
                <h3 id="bar-text">Appointments</h3>
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

        <!-- BOOK APPOINTMENTS -->
        <div class="content-left">
            <!-- BACK BUTTON -->
            <div class="back-div">
                <button id="back-btn" onclick="location.href='back';">Back</button>
            </div>

            <!-- DISPLAYS APPOINTMENTS AS BUTTONS FOR USER TO CLICK-->
            <h1 id="content-heading">Book Appointment</h1>
            <c:forEach begin = "1" end = "${teacherNames.size()}" var = "TN"> <!--Displays all of the teachers you can book appointments with. -->
                <p><button id="book-btn" onclick="location.href='CreateAppointment?teachID=${TN}';">${teacherNames.get(TN-1)}</button></p>
            </c:forEach>
        </div>

        <!-- UPCOMING APPOINTMENTS -->
        <div class="content-right">
            <h1 id="content-heading">Upcoming Appointments</h1> 
            <ul>
            <c:forEach begin = "1" end = "${appointmentDesc.size()}" var = "AD"> <!--Displays all the current appointments that are within the DB for that user-->
                <li>${appointmentTeacher.get(AD-1)} at ${appointmentTime.get(AD-1)} ${appointmentDate.get(AD-1)}: ${appointmentDesc.get(AD-1)}</li>
                <!--<li>Teacher2 at mm:hh dd/mm/yyy: discuss rat seasoning options</li>
                <li>Teacher1 at mm:hh dd/mm/yyy: show the chef you ate</li> -->
            </c:forEach>
            </ul>
        </div>
    </section>
</body>
</html>