<!--
    Assignment 3: CreateGroup.jsp
    Josh R(c3324541), Moosa H (), Keeylan H ()
    -----------------------------------------------------
    Purpose: This will be the page where a user will be able to create a new group. 
    They are able to create the group and also make it apart of a specific subject. 
-->
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<title>University of Newcastle - Create Group</title>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/creategroup.css">
    <script src="https://kit.fontawesome.com/a913ebebd3.js" crossorigin="anonymous"></script>
    <script>
        function validate() 
        {
            var groupName = document.getElementById("groupName"); //Grabs the user id from the form
            var subject = document.getElementById("subject");
            var returnStatus = true;
            var messageError = "Sorry but: \n ";
            if (groupName === null || groupName.value === "")  //Checks to see if there was actually a name in the input
            { 
                returnStatus = false; //Returns false so it doesn't submit
                messageError += "Please input a group name\n"; //Adds to String that will get returned. 
            }
            if (subject === null || subject.value === "")  
            { 
                returnStatus = false; //Returns false so it doesn't submit
                messageError += "Please put in a subject\n"; //Adds to String that will get returned. 
            }
            if (!returnStatus) 
            {
                alert(messageError); //Returns the string in the alert
            }
            return returnStatus; //Returns the result Status. 
        }
    </script>
</head>

<body>
    <section class="landing-page-main">
         <!--TOP BAR-->
         <div class="top-bar">
            <div id="bar-left">
                <img src="https://www.newcastle.edu.au/__data/assets/image/0011/246881/uon-logo-square.png" alt="" class="box-img">
            </div>
            <div id="bar-middle">
                <h1 id="newcastle-portal">Newcastle Portal</h1> <!--Showing information about the server that is being used-->
                <h2 id="bar-text">Group management system.</h2>
                <h3 id="bar-text">Create Group</h3> <!--Indicates the page that is currently featured. -->
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
        <!--BACK BUTTON-->
        <div class="main-content">
            <div class="back-div">
                <button id="back-btn" onclick="location.href='groups.jsp';">Back</button>
            </div>

            <form action="createGroup" method="POST" onsubmit = "return validate()"> <!--Allows a group to consist of a name and a subject that it is apart of. -->
            <p id="label">Group Name</p>
            <p><input id="input-title" type="text" id = "groupName" name="groupNameInput" required></p>

            <p id="label">Subject</p>
            <p><input id="input-title" type="text" id = "subject" name="subjectInput"></p>

            <p><input id="submit" type="submit" value="Create Group"></p>
            </form>
        </div>
    </section>
</body>
</html>