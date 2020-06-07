<!--
    Assignment 3: Hub.jsp
    Josh R(c3324541), Moosa H (c3331532), Keeylan H ()
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
        <link rel="stylesheet" type="text/css" href="css/overviewgroup.css">
        <script src="https://kit.fontawesome.com/a913ebebd3.js" crossorigin="anonymous"></script>
    </head>

    <body>
        <section class="landing-page-main">
            <!-- TOP BAR -->
            <div class="top-bar">
                <div id="bar-left">
                    <img src="https://www.newcastle.edu.au/__data/assets/image/0011/246881/uon-logo-square.png" alt="" class="box-img">
                </div>
                <div id="bar-middle">
                    <h1 id="newcastle-portal">Newcastle Portal</h1>
                    <h2 id="bar-text">Group management system.</h2>
                    <h3 id="bar-text">Group Overview</h3>
                </div>
                <div id="bar-right">
                    <h2 id="bar-text">${user.getName()}</h2> <!--This is shown on all pages after the index, prints the user name -->
                </div>
                <hr>
            </div>

            <!-- MAIN CONTENT -->
            <div class="main-content">
                <!-- BACK BUTTON -->
                <div class="back-div">
                    <button id="back-btn" onclick="location.href='teacherhub.jsp';">Back</button>
                </div>

                <!-- DISPLAY GROUP NAME AND DETAILS OF THE GROUP AS A TABLE -->
                <h1 id="content-heading">${groupName} Overview</h1>
                <p><button id="feedback-btn" onclick="location.href='feedback.jsp';">Give Feedback</button></p>
                <table>
                    <tr>
                      <th>Username</th>
                      <th>Name</th>
                      <th>Student ID</th>
                    </tr>
                    
                    <c:forEach begin = "1" end = "${groupMembers.size()}" var = "i">
                        <tr>
                            <td>${groupMembers.get(i-1)}</td>
                            <td>${firstNames.get(i-1)} ${lastNames.get(i-1)}</td>
                            <td>${studentIDs.get(i-1)}</td>
                        </tr>
                    </c:forEach>
                </table>

                    <!-- UPLOAD FILES -->
                    <div class="content-right">
                        <br>

                    </div>
                
                    
                    <table border ="1" width ="90%">

                        <tr>
                            <th>Submission ID</th>
                            <th>Milestone ID</th>
                            <th>Submission Desc</th>
                            <th>Give Feedback</th>
                        </tr>

                        <c:forEach begin = "1" end="${submissionIDs.size()}" var="NUM">
                            <tr>
                                <td>${submissionIDs.get(NUM-1)}</td>
                                <td>${milestoneIDs.get(NUM-1)}</td>
                                <td>${submissionDescs.get(NUM-1)}</td>
                                <td><button id="feedback-btn" onclick="location.href='GiveFeedback?SID=${submissionIDs.get(NUM-1)}::${milestoneIDs.get(NUM-1)}';">Give Feedback</button></td>
                            </tr>
                        </c:forEach> -->

                    
                    </table>
                    <div class = "files-list">
                    <h1 id="content-heading">View Files</h1>
                    <table border ="1" width ="90%">

                        <tr>
                            <th>File Name</th>
                            <th>File Description</th>
                            <th>Version Section</th>
                        </tr>

                        <c:forEach items="${list}" var="l">
                            <form action ="upload" method="post">
                                <tr>
                                    <td>${l.getFileName()}</td>
                                    <td>${l.getDescription()}</td>
                                    <td><input type="submit" name="list" value="Versions">
                                        <input type="hidden" name="fileName" value="${l.getFileName()}"/></td>
                                </tr>
                            </form>
                        </c:forEach>
                    </table>
                    </div>
            </div>
        </section>
        
    </body>
</html>