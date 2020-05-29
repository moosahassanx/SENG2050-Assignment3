<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
    <title>University of Newcastle - Discussions</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/viewdiscussion.css">
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
                <h3 id="bar-text">${discussionTitle}</h3>
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

        <!-- MESSAGES -->
        <div class="discussions-list">
        <p id = "username">${discussionUsername}</p>
        <p id = "message">${discussionDesc}</p>
        <c:forEach begin = "1" end = "${threadIDs.size()}" var = "TID">
            <p id="username">${threadUsernames.get(TID-1)}</p>
            <p id="message">${threadDesc.get(TID-1)}</p>
        </c:forEach>
        </div>
        <!--<p id="username">[Humeey]</p>
        <p id="message">Have you tried boiling it?</p>

        <p id="username">[Moosa]</p>
        <p id="message">Just eat it raw bro.</p> -->
        <!-- REPLY -->
        <div class="create-discussion">
            <input type="text">
            <p><a href="viewdiscussion.jsp" address="true">Reply</a></p>
        </div>
    </section>
</body>

</html>