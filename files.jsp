<!--
    Assignment 3: Files.jsp
    Josh R(c3324541), Moosa H (c3331532), Keeylan H ()
    -----------------------------------------------------
    Purpose: This is where all the files will be stored. This is where students can
    upload various files and also download them. 
-->
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>

<%@ page import="userpackage.File"%>
<!DOCTYPE html>
<html>
<head>
	<title>University of Newcastle - Files</title>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/files.css">
    <script src="https://kit.fontawesome.com/a913ebebd3.js" crossorigin="anonymous"></script>
    <script type="text/javascript" src="js/javascript.js"></script>
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
                <h3 id="bar-text">Files</h3>
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

        <!-- UPLOAD FILES -->
        <div class="content-right">
            <div class="back-div">
                <p><button id="back-btn" onclick="location.href='hub.jsp';">Back</button></p>
            </div>

            <h1 id="content-heading">Upload Files</h1>
            <form action="upload" method="post" enctype="multipart/form-data">
                <p id="label">Select a file: </p>
                <p><input type="file" name="myfile" id="file" class="custom-file-input"></p>
                <br>
                <p id="label">Description: </p>
                <p><input type="text" id="description" name="description"/></p>

                <input type="hidden" name="userUploaded" value="${user.getName()}"/>
                <input type="hidden" name="userGroup" value="${user.getGroup()}"/>

                <p><input type="submit" id="submit" name="list" value="Upload" onclick="return fileValidation()"></p>
                <br><hr>
                <p><input type="submit" id="submit" name="list" value="list"></p>
        </div>
    
        <div class = "files-list">
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
    
    </section>
</body>
</html>