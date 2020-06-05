<!--
    Assignment 3: Hub.jsp
    Josh R(c3324541), Moosa H (), Keeylan H ()
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

                <h1 id="content-heading">[group name] Overview</h1>
                
                <table>
                    <tr>
                      <th>Username</th>
                      <th>Name</th>
                      <th>E-mail</th>
                    </tr>
                    <tr>
                      <td>Humey</td>
                      <td>Keeylan</td>
                      <td>littledick@chef.com</td>
                    </tr>
                    <tr>
                      <td>c3331532</td>
                      <td>Moosa</td>
                      <td>chefconsumer@yahoo.com</td>
                    </tr>
                    <tr>
                      <td>c3324541</td>
                      <td>Josh</td>
                      <td>ratsmacker@rat.com</td>
                    </tr>
                  </table>
            </div>
        </section>
        
    </body>
</html>