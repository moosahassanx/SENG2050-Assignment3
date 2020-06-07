<!--
    Assignment 3: Hub.jsp
    Josh R(c3324541), Moosa H (c3331532), Keeylan H ()
    -----------------------------------------------------
    Purpose: This is the main hub page that will be used to access the other
    various pages of the site. It will be the page you see after you have logged in. 
-->
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <title>University of Newcastle - Hub</title> <!--To inform the user what the page is, below links to the correct CSS for this page. -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/hubstyle.css">
        <script src="https://kit.fontawesome.com/a913ebebd3.js" crossorigin="anonymous"></script>
    </head>

    <body>
        <section class="landing-page-main">
            <!-- TOP BAR -->
            <div class="top-bar">
                <div id="bar-left">
                    <img src="https://www.newcastle.edu.au/__data/assets/image/0011/246881/uon-logo-square.png" alt="" class="box-img">
                </div>
                <div id="bar-middle">
                    <h1 id="newcastle-portal">Newcastle Portal</h1>
                    <h2 id="bar-text">Group management system.</h2>
                    <h3 id="bar-text">Hub</h3>
                </div>
                <div id="bar-right">
                    <h2 id="bar-text">${user.getName()}</h2> <!--This is shown on all pages after the index, prints the user name --> <!--and checks to see if the user has a group, if so it will be displayed. -->
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

            <!-- THE ACTUAL USER INTERACTION OF THE SITE (LEFT COLUMN) -->
            <div class="content-left">
                <!-- LOGOUT BUTTON -->
                <div class="back-div">
                    <button id="back-btn" onclick="location.href='login';">Logout</button>
                </div>

                <h1 id="content-heading">Menu</h1>
                <p><a href="LoadAppointments" address="true">Appointments</a></p>
                <p><a href="discussions" address="true">Discussions</a></p>
                <p><a href="groups" address="true">Join Group</a></p> <!--Links to all the different pages so the user can access them. -->
                <p><a href="files.jsp" address="true">Files</a></p>
                <p><a href="viewmilestones.jsp" address="true">View Milestones</a></p>
                <p><a href="${pageContext.request.contextPath}/responsibility?id=${user.getGroup()}" address="true">Planning</a></p>
            </div>

            <!-- THE ACTUAL USER INTERACTION OF THE SITE (RIGHT COLUMN) -->
            <div class="content-right"> <!--Displays the Milestones for the user, loops through each of the milestones that are featured within the DB-->
                <h1 id="content-heading">Milestone List</h1>
                <ul>
                <c:forEach begin = "1" end = "${milestoneDescriptions.size()}" var = "MD">
                    <li>${milestoneTitles.get(MD-1)} Due ${milestoneDates.get(MD-1)}: ${milestoneDescriptions.get(MD-1)}</li>
                </c:forEach>
                </ul>
            </div>
        </section>
    </body>
</html>