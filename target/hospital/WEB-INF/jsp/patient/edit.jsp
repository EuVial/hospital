<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="resources"/>

<c:if test="${empty patient}">
    <jsp:useBean id="patient" class="dao.entity.patient.Patient"/>
</c:if>
<c:choose>
    <c:when test="${not empty patient.id}">
        <fmt:message var="title" key="patient.edit.title.edit"/>
    </c:when>
    <c:otherwise>
        <fmt:message var="title" key="patient.edit.title.add"/>
    </c:otherwise>
</c:choose>

<u:html title="${title}">
    <h2>${title}</h2>
    <c:url var="urlPatientList" value="/patient/list.html"/>
    <c:url var="urlPatientSave" value="/patient/save.html"/>
    <c:url var="urlPatientDelete" value="/patient/delete.html"/>
    <form action="${urlPatientSave}" method="post">
        <c:if test="${not empty patient.id}">
            <input name="id" value="${patient.id}" type="hidden">
        </c:if>

        <label for="first_name"><fmt:message key="patient.edit.form.firstname"/>:</label>
        <input id="first_name" name="first_name" value="${patient.firstName}">

        <label for="last_name"><fmt:message key="patient.edit.form.lastname"/>:</label>
        <input id="last_name" name="last_name" value="${patient.lastName}">

        <label for="ward"><fmt:message key="patient.edit.form.ward"/>:</label>
        <input id="ward" name="ward" value="${patient.ward}">

        <button class="save"><fmt:message key="patient.edit.button.save"/></button>

        <c:if test="${not empty patient.id}">
            <c:if test="${not patientCanBeDeleted}">
                <c:set var="disabled" value="disabled"/>
            </c:if>

            <button class="delete" formaction="${urlPatientDelete}" formmethod="post" ${disabled}>
                <fmt:message key="patient.edit.button.delete"/>
            </button>
        </c:if>

        <button class="reset" type="reset">
            <fmt:message key="patient.edit.button.reset"/>
        </button>

        <button class="back" formaction="${urlPatientList}" formmethod="get">
            <fmt:message key="patient.edit.button.cancel"/>
        </button>
    </form>
</u:html>