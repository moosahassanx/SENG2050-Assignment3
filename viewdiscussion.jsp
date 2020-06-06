<!--
    Assignment 3: viewdiscussions.jsp
    Josh R(c3324541), Moosa H (c3331532), Keeylan H ()
    -----------------------------------------------------
    Purpose: To allow users of the page to view the various discussions that have been 
    made on the page. 
-->
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
    <title>University of Newcastle - Discussions</title> 
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/viewdiscussion.css"> <!--Links to an external stylesheet to control the looks of the page-->
    <script src="https://kit.fontawesome.com/a913ebebd3.js" crossorigin="anonymous"></script>
</head>

<body>
    <section class="landing-page-main"> <!--Split into a section to allow everything on page to be organised. -->
        <!--TOP BAR-->
        <div class="top-bar">
            <div id="bar-left">
                <img src="https://www.newcastle.edu.au/__data/assets/image/0011/246881/uon-logo-square.png" alt="" class="box-img">
            </div>
            <div id="bar-middle">
                <h1 id="newcastle-portal">Newcastle Portal</h1>
                <h2 id="bar-text">Group management system.</h2>
                <h3 id="bar-text">${discussionTitle}</h3> <!--Displays the discussion title that the user has decided-->
            </div>
            <div id="bar-right">
                <h2 id="bar-text">${user.getName()}</h2> <!--Grabs the user's name from login-->
                <c:choose>
                    <c:when test = "${user.hasGroup()}"> <!--Checks to see if the user has a group, if they do it will display, if not, N/A will display-->
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
        <p id = "username">${discussionUsername}</p> <!--Shows the original discussion's username and their description-->
        <p id = "message">${discussionDesc}</p>
        <c:forEach begin = "1" end = "${threadIDs.size()}" var = "TID"><!--Will loop through showing the various users and their replies to the thread.-->
            <p id="username">${threadUsernames.get(TID-1)}</p> 
            <p id="message">${threadDesc.get(TID-1)}</p>
        </c:forEach>
        </div>

        <!-- REPLY -->
        <div class="create-discussion">
            <form action = "createDiscussionThread" method = "POST"> <!--Will go to its own servlet to pass data into the DB-->

                <p id="label">Reply</p>
                <p><input id="input-message" type="text" name = "description"></p> <!--The user writes their own reply to the discussions-->

                <br>
                
                <p><input id="submit" type="submit" value="Create Discussion"></p> 
            </form>

            </form>
        </div>
    </section>
</body>

</html>