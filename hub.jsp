<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<title>University of Newcastle - Hub</title>
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
				<!--Store in a bean from login? -->
                <h1 id="newcastle-portal">Newcastle Portal</h1>
                <h2 id="bar-text">Group management system.</h2>
                <h3 id="bar-text">Hub</h3>
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

        <div class="content-left">
            <h1 id="content-heading">Menu</h1>
            <p><a href="groups" address="true">Groups</a></p>
            <p><a href="appointments.jsp" address="true">Appointments</a></p>
            <p><a href="addmilestone.jsp" address="true">Milestones</a></p>
            <p><a href="files.jsp" address="true">Files</a></p>
            <p><a href="discussions" address="true">Discussions</a></p>
        </div>

        <div class="content-right">
            <h1 id="content-heading">Milestone List</h1>
            <ul>
                <li>[Student Name]: Eat a chef</li>
                <li>[Student Name]: Sell fathers day cards to the orphanage</li>
                <li>[Student Name]: Smash that bitch off lazy town</li>
            </ul>
        </div>
    </section>
    
</body>
</html>