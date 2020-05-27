<!DOCTYPE html>
<html>
<head>
	<title>University of Newcastle - Discussions</title>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/discussions.css">
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
                <h3 id="bar-text">Discussions</h3>
            </div>
            <div id="bar-right">
                <h2 id="bar-text">[Student Name]</h2>
                <h2 id="bar-text">[Group Name]</h2>
            </div>
            <hr>
        </div>

        <!-- LIST OF DISCUSSIONS -->
        <div class="discussions-list"> <!--Another form that leads to a servlet that checks DB to find the right discussion thread. -->
            <p><a href="viewdiscussion.jsp" address="true">Discussion Title1</a></p>
            <p><a href="viewdiscussion.jsp" address="true">Discussion Title2</a></p>
            <p><a href="viewdiscussion.jsp" address="true">Discussion Title3</a></p>
            <p><a href="viewdiscussion.jsp" address="true">Discussion Title4</a></p>
        </div>
        
        <!-- CREATE DISCUSSION BUTTON -->
        <div class="create-discussion">
            <p><a href="creatediscussion.jsp" address="true">Create Discussion</a></p>
        </div>
    </section>
</body>
</html>