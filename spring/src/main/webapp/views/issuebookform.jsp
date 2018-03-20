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
<h3>Issue Book Form</h3>
<spring:form method="post" modelAttribute="issueBookBean" action="/IssueBook">
    <spring:input path="callno"/>
    <spring:input path="studentid"/>
    <spring:input path="studentmobile"/>
    <spring:input type="date" path="issueddate"/>
    <input type="submit"/>
</spring:form>
</body>
</html>