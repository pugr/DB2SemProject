<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages.properties"/>
<html>
<head>
    <title>Semestrální práce z KIV/DB2</title>
    <link rel="stylesheet" type="text/css" href="./style/style.css" media="screen"/>
</head>
<body>

<div id = "menu">
    <a href="<c:url value='/'/>" class="link"><span>Home page</span></a>
    <a href="<c:url value='/users'/>" class="link"><span>Users</span></a>
    <a href="<c:url value='/users/newUser'/>" class="link"><span>New User</span></a>
</div>

<div id = "content">