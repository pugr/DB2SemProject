<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../menu.jsp" flush="true"/>

<form:form modelAttribute="userCommand" method="post">
    <fieldset>
        <legend>Upload Fields</legend>

        <p>
            <form:label for="name" path="name">Name</form:label><br/>
            <form:input path="name"/>
            <form:errors cssClass="error" path="name" element="div"/>
        </p>

        <p>
            <form:label for="surname" path="surname">Surname</form:label><br/>
            <form:input path="surname"/>
            <form:errors cssClass="error" path="surname" element="div"/>
        </p>

        <p>
            <form:label for="dateOfBirth" path="dateOfBirth">Date of Birth</form:label><br/>
            <form:input path="dateOfBirth"/>
            <form:errors cssClass="error" path="dateOfBirth" element="div"/>
        </p>

        <p>
            <form:label for="nationality" path="nationality">Nationality</form:label><br/>
            <form:input path="nationality"/>
        </p>

        <p>
            <form:label for="gender" path="gender">Gender</form:label><br/>
            <form:radiobutton path="gender" value="Male" label="Male"/>
            <form:radiobutton path="gender" value="Female" label="Female"/>
            <form:errors cssClass="error" path="gender" element="div"/>
        </p>

        <p>
            <form:label for="description" path="description">Description</form:label><br/>
            <form:textarea rows="10" path="description"/>
        </p>

        <p>
            <form:label for="height" path="height">Height</form:label><br/>
            <form:input path="height"/>
            <form:errors cssClass="error" path="height" element="div"/>
        </p>

        <p>
            <input type="submit" value="Submit"/>
        </p>

    </fieldset>
</form:form>

<jsp:include page="../footer.jsp" flush="true"/>