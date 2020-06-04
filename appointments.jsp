<!--
    Assignment 3: Discussions.jsp
    Josh R(c3324541), Moosa H (), Keeylan H ()
    -----------------------------------------------------
    Purpose: 
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
    <script src="https://kit.fontawesome.com/a913ebebd3.js" crossorigin="anonymous"></script>
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
            <div class="back-div">
                <button id="back-btn" onclick="location.href='hub.jsp';">Back</button>
            </div>

            <h1 id="content-heading">Book Appointment</h1>
            <c:forEach begin = "1" end = "${teacherName.size()}" var = "TN">
                <p><button id="book-btn" onclick="location.href='bookappointment.jsp';">${teacherName.get(TN-1)}</button></p>
            </c:forEach>
            <!--<p><button id="book-btn" onclick="location.href='bookappointment.jsp';">Teacher2 (rat conductor)</button></p>
            <p><button id="book-btn" onclick="location.href='bookappointment.jsp';">Teacher3 (chef supporter)</button></p>
            <p><button id="book-btn" onclick="location.href='bookappointment.jsp';">Teacher4 (drain pipe)</button></p>
            <p><button id="book-btn" onclick="location.href='bookappointment.jsp';">Teacher5 (the feminist)</button></p> -->
        </div>

        <!-- UPCOMING APPOINTMENTS -->
        <div class="content-right">
            <h1 id="content-heading">Upcoming Appointments</h1>
            <ul>
            <c:forEach begin = "1" end = "${appointmentDesc}" var = "AD">
                <li>${appointmentTeacher.get(AD-1)} at mm:hh dd/mm/yyy: ${appointmentDesc.get(AD-1)}</li>
                <!--<li>Teacher2 at mm:hh dd/mm/yyy: discuss rat seasoning options</li>
                <li>Teacher1 at mm:hh dd/mm/yyy: show the chef you ate</li> -->
            </c:forEach>
            </ul>
        </div>
    </section>
</body>
</html>