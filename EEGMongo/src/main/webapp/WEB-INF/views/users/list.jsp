<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<fmt:setBundle basename="messages.properties"/>--%>

<jsp:include page="../menu.jsp" flush="true"/>

<c:if test="${not empty status}">
    <fmt:message key="users.newScenarioAdded"/>
</c:if>

<c:choose>
    <c:when test="${empty users}">
        <fmt:message key="users.noUsers"/>
    </c:when>
    <c:otherwise>
        <ol class="usersInfo">
            <c:forEach var="user" items="${users}">
                <li>
                    <h3>
                        <c:out value="${user.name}"/> <c:out value="${user.surname}"/>
                    </h3>
                    <ul>
                        <li>
                    <span>
                        <a href="<c:url value='/users/detail/${user.id}'/>">
                            <fmt:message key="users.info"/>
                        </a>
                    </span>
                        </li>
                        <li>
                    <span class="editOptions">
                        <a href="<c:url value='/users/edit/${user.id}'/>">
                            <fmt:message key="users.edit"/>
                        </a>
                    </span>
                        </li>
                        <li>
                    <span class="editOptions">
                        <a href="/users/delete/${user.id}">
                            <fmt:message key="users.delete"/>
                        </a>
                    </span>
                        </li>
                    </ul>
                </li>
            </c:forEach>
        </ol>
    </c:otherwise>
</c:choose>

<jsp:include page="../footer.jsp" flush="true"/>