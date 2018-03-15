<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 20.04.2017
  Time: 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%String idToEdit = (String) request.getAttribute("idToEdit");
    String header = "Редактирование студента";
    if (idToEdit == null) {
        header = "Добавление студента";
    }
%>
<html>
<head>
    <title><%=header%></title>
</head>
<body>
<div class="navbar-header">
    <a class="navbar-brand" href="index.jsp">eLibrary</a>
</div>
<form method="post">
    Имя: <input type="text" name="name" value="${requestScope.name}"/>
    <br>Возраст: <input type="number" name="age" value="${requestScope.age}"/>
    <br>Группа: <input type="number" name="groupId" value="${requestScope.groupId}"/>
    <br><input type="submit" value="Сохранить"/>
</form>
</body>
</html>
