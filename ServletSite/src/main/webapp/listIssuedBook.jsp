<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 19.04.2017
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="navbar-header">
    <a class="navbar-brand" href="librarianMain.jsp">eLibrary</a>
</div>
<!--%String message = (String) request.getAttribute("value");%-->
<h1>
    Список выдач
    <!--%=message%-->
    <!--%=request.getAttribute("value")%-->
</h1>

<table>
<c:forEach items="${requestScope.list}" var="book">
    <tr>
        <td><c:out value="${book.callno}"></c:out></td>
        <td><c:out value="${book.studentid}"></c:out></td>
        <td><c:out value="${book.studentname}"></c:out></td>
        <td><c:out value="${book.studentmobile}"></c:out></td>
        <td><c:out value="${book.issueddate}"></c:out></td>
        <td><c:out value="${book.returnstatus}"></c:out></td>
        <td>
            <form method="post" href='ReturnBook'>
                <input type="submit" value="Вернуть"/>
                <input type="hidden" name="callno" value="${book.callno}"/>
                <input type="hidden" name="studentid" value="${book.studentid}"/>
            </form>
        </td>
    </tr>
</c:forEach>
</table>
</body>
</html>
