<%--
  Created by IntelliJ IDEA.
  User: melni
  Date: 07.03.2018
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>]
<head>
    <title>Admin Section</title>
    <link rel='stylesheet' href='bootstrap.min.css'/>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href="/">eLibrary</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="/">Home</a></li>
                <li><a href="AddBook">Add Book</a></li>
                <li><a href="ViewBook">View Book</a></li>
                <li><a href="IssueBook">Issue Book</a></li>
                <li><a href="ViewIssuedBook">View Issued Book</a></li>
                <li><a href="ReturnBook">Return Book</a></li>
                <li><a href="LogoutLibrarian">Logout</a></li>
            </ul>

        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<script src="jquery.min.js"></script>
<script src="bootstrap.min.js"></script>
</body>
</html>
