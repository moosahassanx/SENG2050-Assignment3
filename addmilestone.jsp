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
                    <h2 id="bar-text">[Student Name]</h2>
                    <h2 id="bar-text">[Group Name]</h2>
                </div>
                <hr>
            </div>

            <!-- LIST OF DISCUSSIONS -->
            <div class="main-content">
                <p id="label">Milestone Title</p>
                <p><input id="input-title" type="text"></p>

                <p id="label">Summary</p>
                <p><input id="input-message" type="text"></p>

                <p id="label">Date</p>
                <p><input id="input-title" type="date"></p>

                <p><a href="hub.jsp" address="true">Add Milestone</a></p>
            </div>
        </section>

        <!--
        <label for="">Milestone Title</label>
        <input type="text" placeholder="Enter Title Here">
        <br>

        <label for="">Milestone Summary</label>
        <input type="textarea">
        <br>

        <label for="">Date</label>
        <input type="date">
        <br>

        <label for="">Time</label>
        <input type="time">
        <br>

        <input type="submit">
        -->
    </form>
</body>
</html>