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
                <h3 id="bar-text">Groups</h3>
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

        <!-- LIST OF GROUPS -->
        <div class="discussions-list">
            <div class="back-div">
                <button id="back-btn" onclick="location.href='back';">Back</button>
            </div>

            <!-- DISPLAY A LIST OF GROUPS AS BUTTONS FOR USER TO CLICK -->
            <h1 id="content-heading">List of Groups</h1>
            <form action="joinGroup">
            <c:forEach begin = "1" end = "${GroupNames.size()}" var = "i">
                <p><button id="group-btn" onclick="location.href='joingroup';" value="${GroupNames.get(i-1)}" name="groupName">${GroupNames.get(i-1)}</button></p>
            </c:forEach>
            </form>
        </div>
        
        <!-- CREATE GROUP BUTTON -->
        <div class="create-discussion">
            <p><button id="newgroup-btn" onclick="location.href='creategroup.jsp';">Create Group</button></p>
        </div>
    </section>
</body>
</html>