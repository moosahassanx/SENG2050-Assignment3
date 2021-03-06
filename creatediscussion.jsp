<!--
    Assignment 3: CreateDiscussions.jsp
    Josh R(c3324541), Moosa H (c3331532), Keeylan H ()
    -----------------------------------------------------
    Purpose: This is the page that relates to creating the new discussions on the server. 
    This is to ensure users can create new discussions when they are required to. 
-->
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<title>University of Newcastle - Create Discussion</title>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/creatediscussion.css">
    <script type = "text/javascript" src="js/javascript.js"></script>
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
                <h3 id="bar-text">Create Discussion</h3> <!--Indicates what the page is-->
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

        <!-- CREATE DISCUSSION FORM -->
        <div class="main-content">
            <div class="back-div">
                <button id="back-btn" onclick="location.href='hub.jsp';">Back</button>
            </div>
            
            <!--The discussion post will go to this servlet-->
            <form action="createDiscussion" method = "POST" onsubmit ="return discussionsValidate()">
                <!--Discussions consist of a title and a message-->
                <p id="label">Title</p>
                <p><input type="text" id = "title" name = "title"></p>

                <p id="label">Message</p>
                <p><input type="text" id = "message" name = "description"></p>

                <p><input id="submit" type="submit" value="Create Discussion"></p>
            </form>
        </div>
    </section>    
</body>
</html>