<!--
    Assignment 3: Groups.jsp
    Josh R(c3324541), Moosa H (c3331532), Keeylan H ()
    -----------------------------------------------------
    Purpose: This page will show all groups that are currently apart of the page. They will also allow
    users to join the group if they would like to. 
-->
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<title>University of Newcastle - Files</title>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/groups.css">
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
                <h3 id="bar-text">Feedback</h3>
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

        <!-- FEEDBACK CONTENT -->
        <div class="discussions-list">
            <div class="back-div">
                <button id="back-btn" onclick="location.href='back';">Back</button>
            </div>

            <form action="GiveFeedback" method="POST">
                <p id="label">Submission Title</p>
                <p><input id="input-title" type="text" id="groupName" name="groupNameInput" required></p>

                <p id="label">Feedback</p>
                <p><input id="input-message" type="text" id="groupName" name="groupNameFeedback" required></p>

                <p id="label">Mark</p>
                <p><input type="text" placeholder="Mark out of 100"></p>

                <input type="hidden" name="groupName" value="${groupName}">

                <!-- TODO: fix submissionID and milestoneID -->
                <input type="hidden" name="submissionID">
                <input type="hidden" name="milestoneID">

                <p><input id="submit" type="submit"></p>
            </form>
        </div>
    </section>
</body>
</html>