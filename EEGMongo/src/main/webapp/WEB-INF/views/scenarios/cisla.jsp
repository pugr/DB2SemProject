<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages.properties"/>

<jsp:include page="../menu.jsp" flush="true"/>

<h2>
    Scenario info
</h2>

<ul>
    <li>
        Name: ${document.name}
    </li>
    <li>
        Type: ${document.scenarioType}
    </li>
    <li>
        Description: ${document.description}
    </li>
</ul>

<h3>
    Image List information
</h3>

<ul>
    <li>Count: ${document.count}</li>
    <li>Random order:
        <c:choose>
            <c:when test="${document.random == 'true'}">Yes</c:when>
            <c:otherwise>No</c:otherwise>
        </c:choose>
    </li>
    <li>Text file: ${document.txtFile}</li>
    <li>Delay: ${document.delay}</li>
    <li>Report: ${document.report}</li>
    <li>Pause: ${document.pause}</li>
</ul>
<h3>
    Images
</h3>
<ul>
    <c:forEach var="image" items="${document.imageList}">
        <li>Source: ${image.source}</li>
        <li>Count: ${image.count}</li>
        <li>Mark: ${image.mark}</li>
    </c:forEach>
</ul>

<jsp:include page="../footer.jsp" flush="true"/>