<!--
    Assignment 3: Hub.jsp
    Josh R(c3324541), Moosa H (), Keeylan H ()
    -----------------------------------------------------
    Purpose: This is the main hub page that will be used to access the other
    various pages of the site. It will be the page you see after you have logged in. 
-->
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <title>University of Newcastle - Teacher Hub</title> <!--To inform the user what the page is, below links to the correct CSS for this page. -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/hubstyle.css">
        <script src="https://kit.fontawesome.com/a913ebebd3.js" crossorigin="anonymous"></script>
    </head>

    <body>
        <section class="landing-page-main">
            <div class="top-bar">
                <div id="bar-left">
                    <img src="https://www.newcastle.edu.au/__data/assets/image/0011/246881/uon-logo-square.png" alt="" class="box-img">
                </div>
                <div id="bar-middle">
                    <h1 id="newcastle-portal">Newcastle Portal</h1>
                    <h2 id="bar-text">Group management system.</h2>
                    <h3 id="bar-text">Teacher Hub</h3>
                </div>
                <div id="bar-right">
                    <h2 id="bar-text">${user.getName()}</h2> <!--This is shown on all pages after the index, prints the user name -->                    
                </div>
                <hr>            
            </div>

            <!-- MENU COLUMN -->
            <div class="content-left">
                <h1 id="content-heading">Menu</h1>
                <p><a href="groups" address="true">Groups</a></p> <!--Links to all the different pages so the user can access them. -->
                <p><a href="LoadAppointments" address="true">Appointments</a></p>
            </div>

            <!-- APPOINTMENTS COLUMN -->
            <div class="content-right"> <!--Displays the Milestones for the user, loops through each of the milestones that are featured within the DB-->
                <h1 id="content-heading">Appointments</h1>
                <ul>
                    <c:forEach begin = "1" end = "${appointmentTime.size()}" var = "AT">
                        <li>[${appointmentTime.get(AT-1)} ${appointmentDate.get(AT-1)}]: ${appointmentStudent.get(AT-1)} - ${appointmentDesc.get(AT-1)}</li>
                    </c:forEach>
                </ul>
            </div>
        </section>
        
    </body>
</html>