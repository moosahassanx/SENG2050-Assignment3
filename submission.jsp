<!--
    Assignment 3: Discussions.jsp
    Josh R(c3324541), Moosa H (c3331532), Keeylan H ()
    -----------------------------------------------------
    Purpose: The Discussions page is where everything relating to discussions will be held. Users will
    be able to view discussions, create new discussions and comment on certain discussions too. 
-->
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<title>University of Newcastle - Submission</title>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/discussions.css">
    <script src="https://kit.fontawesome.com/a913ebebd3.js" crossorigin="anonymous"></script>
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
                <h3 id="bar-text">Discussions</h3>
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

        <!-- LIST OF DISCUSSIONS -->
        <!--<form action="viewdiscussion.jsp"></form>-->
        <div class="discussions-list"> <!--Another form that leads to a servlet that checks DB to find the right discussion thread. -->
            <!-- BACK BUTTON -->
            <div class="back-div">
                <button id="back-btn" onclick="location.href='hub.jsp';">Back</button>
            </div>
            <form action="createSubmission" method = "POST" onsubmit ="discussionsValidate()"> <!--The discussion post will go to this servlet-->
                <p id="label">Submission Title</p> <!--Discussions consist of a title and a message-->
                <p><input type="text" id = "SubmissionTitle" name = "title"></p>

                <p id="label">Submission Work</p>
                <p><input type="text" id = "subComment" name = "description"></p>

                <p id="label">Submission File</p>
                <p><input type="text" id = "subComment" name = "description"></p>

                <p><input id="submit" type="submit" value="Create Discussion"></p>
            </form>
        </div>
        
        <!-- CREATE DISCUSSION BUTTON -->
        <div class="create-discussion">
            <p><button id="create-btn" onclick="location.href='creatediscussion.jsp';">Create Discussion</button></p>
        </div>
    </section>
</body>
</html>