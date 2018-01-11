<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:if test="${empty patient}">
    <jsp:useBean id="patient" class="domain.patient.Patient"/>
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
    <h4>${title}</h4>
    <div class="columns">
        <div class="column col-12">
            <c:url var="urlPatientList" value="/patient/list.html"/>
            <c:url var="urlPatientSave" value="/patient/save.html"/>
            <c:url var="urlPatientDelete" value="/patient/delete.html"/>
            <form class="form-horizontal" action="${urlPatientSave}" method="post">
                <c:if test="${not empty patient.id}">
                    <input name="id" value="${patient.id}" type="hidden">
                </c:if>

                <div class="form-group">
                    <div class="col-4">
                        <label class="form-label" for="first_name"><fmt:message key="patient.edit.form.firstname"/>:</label>
                    </div>
                    <div class="col-5">
                        <input class="form-input" id="first_name" name="first_name" placeholder="<fmt:message key="patient.edit.form.firstname"/>" value="${patient.firstName}">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-4">
                        <label class="form-label" for="last_name"><fmt:message key="patient.edit.form.lastname"/>:</label>
                    </div>
                    <div class="col-5">
                        <input class="form-input" id="last_name" name="last_name" placeholder="<fmt:message key="patient.edit.form.lastname"/>" value="${patient.lastName}">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-4">
                        <label class="form-label" for="ward"><fmt:message key="patient.edit.form.ward"/>:</label>
                    </div>
                    <div class="col-5">
                        <input class="form-input" id="ward" name="ward" type="number" placeholder="00" value="${patient.ward}">
                    </div>
                </div>

                <button class="btn btn-primary btn-action btn-lg">
                    <i class="icon icon-check"></i>
                </button>

                <c:if test="${not empty patient.id}">
                    <c:if test="${not patientCanBeDeleted}">
                        <c:set var="disabled" value="disabled"/>
                    </c:if>

                    <button class="btn btn-primary btn-action btn-lg" formaction="${urlPatientDelete}" formmethod="post" ${disabled}>
                        <i class="icon icon-delete"></i>
                    </button>
                </c:if>

                <button class="btn btn-primary btn-action btn-lg" type="reset">
                    <i class="icon icon-refresh"></i>
                </button>

                <button class="btn btn-primary btn-action btn-lg" formaction="${urlPatientList}" formmethod="get">
                    <i class="icon icon-back"></i>
                </button>
            </form>
        </div>
    </div>
</u:html>