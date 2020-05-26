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
                <h3 id="bar-text">Create Discussion</h3>
            </div>
            <div id="bar-right">
                <h2 id="bar-text">[Student Name]</h2>
                <h2 id="bar-text">[Group Name]</h2>
            </div>
            <hr>
        </div>

        <!-- LIST OF FILES -->
        <div class="content-left">
            <h1 id="content-heading">Files</h1>
            <p><a href="files.jsp" address="true">File Name1</a></p>
            <p><a href="files.jsp" address="true">File Name2</a></p>
            <p><a href="files.jsp" address="true">File Name3</a></p>
            <p><a href="files.jsp" address="true">File Name4</a></p>
            <p><a href="files.jsp" address="true">File Name5</a></p>
        </div>

        <!-- UPLOAD FILES -->
        <div class="content-right">
            <h1 id="content-heading">Upload Files</h1>
            <form action="files.jsp">
                <p id="label">Select a file: </p>
                <p><input type="file" name="myfile" class="custom-file-input"></p>
                <p><input type="submit" id="submit"></p>
            </form>
        </div>
    </section>

    <!--
    <h1>Newcastle Student</h1>
    <h2>Group management system.</h2>
    <h3>Files</h3>

    <p>*google drive embedding (idk how ima do this so ima come back to this)*</p>

    <form action="hub.html">
        <input type="submit">
    </form>
    -->
</body>
</html>