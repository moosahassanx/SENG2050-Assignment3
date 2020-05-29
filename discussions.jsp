<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<title>University of Newcastle - Discussions</title>
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
        <form action="viewdiscussion.jsp"></form>
        <div class="discussions-list"> <!--Another form that leads to a servlet that checks DB to find the right discussion thread. -->
            <!-- BACK BUTTON -->
            <div class="back-div">
                <button id="back-btn" onclick="location.href='hub.jsp';">Back</button>
            </div>

            <c:forEach begin = "1" end = "${discussionTitles.size()}" var = "DT">
                <p><button id="submit" onclick="location.href='LoadDiscussions?DIT=${discussionID.get(DT-1)}';">${discussionTitles.get(DT-1)}</button></p>
            </c:forEach>
            
            <!-- REMINDER: MAKE A SERVLET FOR THE THREAD PAGE OKAY THANK YOU ANY ONE WHO READS THIS
            <p><input id="submit" type="submit" value="DISCUSSION TITLE1"></p>
            <p><input id="submit" type="submit" value="DISCUSSION TITLE2"></p>
            <p><input id="submit" type="submit" value="DISCUSSION TITLE3"></p>
            <p><input id="submit" type="submit" value="DISCUSSION TITLE4"></p>

            <p><button id="submit" onclick="location.href='viewdiscussion.jsp';">Discussion Title2</button></p>
            <p><button id="submit" onclick="location.href='viewdiscussion.jsp';">Discussion Title3</button></p>
            <p><button id="submit" onclick="location.href='viewdiscussion.jsp';">Discussion Title4</button></p>

            -->
        </div>
        
        <!-- CREATE DISCUSSION BUTTON -->
        <div class="create-discussion">
            <p><button id="create-btn" onclick="location.href='creatediscussion.jsp';">Create Discussion</button></p>
        </div>
    </section>
</body>
</html>