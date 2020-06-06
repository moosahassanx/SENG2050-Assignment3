<!--
    Assignment 3: Files.jsp
    Josh R(c3324541), Moosa H (), Keeylan H ()
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

            <h1 id="content-heading">Version Control for ${fileName}</h1>
            
    
        <div class = "files-list">
        <table border ="1" width ="90%">

            <tr>   
                <th>Version</th>
                <th>File Name</th>
                <th>File Description</th>
                <th>Last Uploaded</th>
                <th>Download</th>
            </tr>

            <c:forEach items="${versionList}" var="l">
                <tr>
                    <td>${l.getVersion()}</td>
                    <td>${l.getFileName()}</td>
                    <td>${l.getDescription()}</td>
                    <td>${l.getUserUploaded()}</td> <!-- Also need to add the option to delete their files and record who deleted e.x.t.-->
                    <td><a href="${pageContext.request.contextPath}/upload?id=${l.getFileName()}">Download me</a></td> <!-- Probs need to change this method to look in version control table-->
                </tr>
            </c:forEach>
        </table>
        </div>
    
    </section>
</body>
</html>