<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../menu.jsp" flush="true"/>

<form:form modelAttribute="scenarioItem" method="post" enctype="multipart/form-data">
    <fieldset>
        <legend>New Scenario</legend>

        <p>
            <form:label for="name" path="name">Name</form:label><br/>
            <form:input path="name"/>
        </p>

        <p>
            <form:label for="description" path="description">Description</form:label><br/>
            <form:textarea rows="15" path="description"/>
        </p>

        <p>
            <form:label for="fileData" path="fileData">File</form:label><br/>
            <form:input path="fileData" type="file"/>
        </p>

        <p>
            <form:label for="documentType" path="documentType">Document Type</form:label><br/>
            <form:select path="documentType" title="Type of document" >
                <c:forEach var="documentType" items="${documentTypesList}">
                    <form:option value="${documentType}"/>
                </c:forEach>
            </form:select>
        </p>

        <p>
            <input type="submit" value="Submit"/>
        </p>

    </fieldset>
</form:form>

<jsp:include page="../footer.jsp" flush="true"/>