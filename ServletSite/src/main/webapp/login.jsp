<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 18.04.2017
  Time: 12:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div class="navbar-header">
    <a class="navbar-brand" href="index.jsp">eLibrary</a>
</div>
    <form method="post" action="/students/">
        <input type="text" name="login" />
        <input type="text" name="password"/>
        <input type="submit" value="login"/>
    </form>
</body>
</html>
