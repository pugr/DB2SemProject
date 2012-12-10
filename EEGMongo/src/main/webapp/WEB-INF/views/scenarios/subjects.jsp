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
    Subjects Information
</h3>

<ol>
    <c:forEach var="subject" items="${document.subjects}">
        <li>
            <ul>
                <li>First Name: ${subject.firstName}</li>
                <li>Last Name: ${subject.lastName}</li>
                <h4>Tests</h4>
                <ol>
                    <c:forEach var="test" items="${subject.tests}">
                        <li>
                            <ul>
                                <li>${test.name}</li>
                                <li>${test.result}</li>
                                <li>${test.test}</li>
                                <li>${test.date}</li>
                            </ul>
                        </li>
                    </c:forEach>
                </ol>
            </ul>
        </li>
    </c:forEach>
</ol>

<jsp:include page="../footer.jsp" flush="true"/>