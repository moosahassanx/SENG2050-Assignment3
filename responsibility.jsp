<!--
    Assignment 3: Discussions.jsp
    Josh R(c3324541), Moosa H (c3331532), Keeylan H ()
    -----------------------------------------------------
    Purpose: 
-->
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<title>University of Newcastle - Add Milestone</title>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/addmilestone.css">
    <script src="https://kit.fontawesome.com/a913ebebd3.js" crossorigin="anonymous"></script>
</head>

<body>
    <form action="hub.jsp">
        <section class="landing-page-main">
            <!--TOP BAR-->
            <div class="top-bar">
                <div id="bar-left">
                    <img src="https://www.newcastle.edu.au/__data/assets/image/0011/246881/uon-logo-square.png" alt="" class="box-img">
                </div>
                <div id="bar-middle">
                    <h1 id="newcastle-portal">Newcastle Portal</h1>
                    <h2 id="bar-text">Group management system.</h2>
                    <h3 id="bar-text">Add Milestone</h3>
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
        </form>
        <!--Back button on the top left of page-->
        <div class="main-content">
            <div class="back-div">
                <button id="back-btn" onclick="location.href='hub.jsp';">Back</button>
        </div>
        <div class = "formContainer">
            <div class="respons-list">
                <form action="responsibility" method="POST">
                    <table border ="1" width="90%">
                        <tr>
                            <th>User</th>
                            <th>Description</th>
                            <th>Proposed Completion Date</th>
                        </tr>
                        <tr>
                            <th>
                                <select id = "users" name="userName"> <!--Change the width of box-->
                                    <c:forEach items="${groupList}" var="user">
                                        <option value = ${user} id="userName">${user}</option>
                                    </c:forEach>
                                    
                                </select>
                            </th>
                            <th>
                                <input type="text" name="description" id="description">
                            </th>
                            <th>
                                <input id="date" type="date" name = "date">
                                
                            </th>
                        </tr>
                    </table>
            </div> 
            <div class="table2">
                    <table>
                        <tr>
                            <th>
                                <input type="hidden" name="userGroup" value="${user.getGroup()}"/>
                                <input type="submit" id="submitRespo" name="add"/>
                            </th>
                        </tr>
                    </table>
                </form>
            </div>
        </div>

        <div class = "responsList">

            <table border ="1" width="90%">
                <tr>
                    <th>Responsible</th>
                    <th>Description</th>
                    <th>Start date</th>
                    <th>Date to be completed</th>
                    <th>Completed</th>
                </tr>
                <c:forEach items="${responseList}" var="l">
                <tr>
                    <td>${l.getResponsible()}</td>
                    <td>${l.getDescription()}</td>
                    <td>${l.getDateStarted()}</td>
                    <td>${l.getDateComplete()}</td>
                    <td>Need to fix</td>
                </tr>
                </c:forEach>
            </table>
        </div>
    </section>
</body>
</html>