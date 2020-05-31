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
        <form action="hub.jsp">
        <div class="discussions-list">
            <div class="back-div">
                <button id="back-btn" onclick="location.href='hub.jsp';">Back</button>
            </div>

            <!-- DISPLAYING GROUPS PSEUDOCODE
            // case: no groups made
            if(groupItems == 0){
                <p>No groups made</p>
            }
            
            // output groups using for loop
            else{
                for(int i = 0; i < groupItems; i++){
                    <button id="group-btn" onclick="location.href='<c:out value="${group[i].getTitle()}"></c:out>.jsp';">
                        <c:out value="${group[i].getTitle()}"></c:out>
                    </button>
                }
            }
            -->

            <!-- FAKE OUTPUT-->
            <p><button id="group-btn" onclick="location.href='joingroup';">Group1: [Student Name], [Student Name], [Student Name]</button></p>
            <p><button id="group-btn" onclick="location.href='joingroup';">Group2: [Student Name], [Student Name], [Student Name]</button></p>
            <p><button id="group-btn" onclick="location.href='joingroup';">Group3: [Student Name], [Student Name], [Student Name]</button></p>
            <p><button id="group-btn" onclick="location.href='joingroup';">Group4: [Student Name], [Student Name], [Student Name]</button></p>
            
        </div>
        </form>
        
        <!-- CREATE GROUP BUTTON -->
        <div class="create-discussion">
            <p><button id="newgroup-btn" onclick="location.href='creategroup.jsp';">Create Group</button></p>
        </div>
    </section>
</body>
</html>