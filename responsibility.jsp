<!--
    Assignment 3: Discussions.jsp
    Josh R(c3324541), Moosa H (), Keeylan H ()
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

            <!-- LIST OF DISCUSSIONS -->
       <!--     <form action="addResponsibility" method = "POST">
                <div class="main-content">
                    <div class="back-div">
                        <button id="back-btn" onclick="location.href='hub.jsp';">Back</button>
                    </div>

                    <p id="label">Milestone Title</p>
                    <p><input id="input-title" type="text" name = "milestoneTitle"></p>

                    <p id="label">Summary</p>
                    <p><input id="input-message" type="text" name = "description"></p>

                    <p id="label">Date</p>
                    <p><input id="input-title" type="date" name = "date"></p>

                    <p><input id="submit" type="submit" VALUE="ADD MILESTONE"></p>
                </div>
            </form>
        -->

        <!--Back button on the top left of page-->
        <div class="main-content">
            <div class="back-div">
                <button id="back-btn" onclick="location.href='hub.jsp';">Back</button>
        </div>
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
                                <select id = "users"> <!--Change the width of box-->
                                    <c:forEach items="${groupList}" var="user">
                                        <option value = ${user}>${user}</option>
                                    </c:forEach>
                                </select>
                            </th>
                            <th>
                                <input type="text" name="description">
                            </th>
                            <th>
                                <input id="input-title" type="date" name = "date">
                            </th>
                        </tr>

                    </table>
                </form>
            
            </div>









        </section>
</body>
</html>