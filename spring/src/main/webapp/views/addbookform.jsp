<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>Add Librarian Form</title>
    <link rel='stylesheet' href='bootstrap.min.css'/>
</head>
<body>
<div class="navbar-header">
    <a class="navbar-brand" href="/">eLibrary</a>
</div>
<h3>Add Book Form</h3>
<spring:form method="post" modelAttribute="book" action="/AddBook">
    <spring:input path="callno"/>
    <spring:input path="name"/>
    <spring:input path="author"/>
    <spring:input path="publisher"/>
    <spring:input path="quantity"/>
    <input type="submit"/>
</spring:form>
</body>
</html>