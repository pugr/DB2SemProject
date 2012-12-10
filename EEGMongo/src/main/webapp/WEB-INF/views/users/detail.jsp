<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../menu.jsp" flush="true"/>

<h2>
    <c:out value="${user.name}  ${user.surname}"/>
</h2>

<ul id="userInfo">
    <li>
        <c:out value="${user.userInfo.gender}"/>
    </li>
    <li>
        <c:if test="${not empty user.userInfo.dateOfBirth}">
            <c:out value="${user.userInfo.dateOfBirth}"/>
        </c:if>
    </li>
    <li class="userDescription">
        <c:choose>
            <c:when test="${empty user.userInfo.description}">
                <span class="noInfo">
                    <fmt:message key="userDetails.noUserInfo"/>
                </span>
            </c:when>
            <c:otherwise>
                <%-- ochrana proti XSS a zachování odřádkování--%>
                <c:set var="newLine" value="<%= \"\n\" %>"/>
                <c:forEach items="${fn:split(user.userInfo.description, newLine)}" var="item">
                    <c:out value="${item}"/><br/>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </li>
    <li>
        <c:out value="${user.userInfo.nationality}"/>
    </li>
    <li>
        <c:choose>
            <c:when test="${user.userInfo.height gt 0}">
                <c:out value="${user.userInfo.height}"/> cm
            </c:when>
            <c:otherwise>
                <fmt:message key="userDetails.noHeight"/>
            </c:otherwise>
        </c:choose>
    </li>
</ul>

<%-- scenarios --%>
<a href="<c:url value='/scenarios/${user.id}/newScenario'/> ">Add scenario</a>

<h3>Scenarios</h3>

<c:choose>
    <c:when test="${empty documents}">
        <fmt:message key="userDetails.noScenarios"/>
    </c:when>
    <c:otherwise>
        <ul>
            <c:forEach var="document" items="${documents}">
                <li>
                    <a href="<c:url value='/scenarios/${document.scenarioType}/${document.id}'/> ">
                        <c:out value="${document.name}"/>
                    </a>
                </li>
            </c:forEach>
        </ul>
    </c:otherwise>
</c:choose>


<jsp:include page="../footer.jsp" flush="true"/>