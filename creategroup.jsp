<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<title>University of Newcastle - Files</title>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/creategroup.css">
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
                <h3 id="bar-text">Create Group</h3>
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

        <!-- CREATE GROUP FORM -->
        <div class="main-content">
            <p id="label">Group Name</p>
            <p><input id="input-title" type="text"></p>

            <p id="label">Subject</p>
            <p><input id="input-title" type="text"></p>

            <p><button id="newgroup-btn" onclick="location.href='groups.jsp';">Create Group</button></p>
        </div>
    </section>
</body>
</html>