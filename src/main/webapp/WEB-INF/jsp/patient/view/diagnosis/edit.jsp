<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>

<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${empty patientDiagnosis}">
    <jsp:useBean id="patientDiagnosis" class="domain.patient.DiagnosisToPatient"/>
</c:if>
<c:choose>
    <c:when test="${not empty patientDiagnosis.id}">
        <fmt:message var="title" key="patient.view.diagnosis.view.title.edit"/>
    </c:when>
    <c:otherwise>
        <fmt:message var="title" key="patient.view.diagnosis.view.title.add"/>
    </c:otherwise>
</c:choose>

<u:patient_view>
    <nav class="panel-nav">
        <ul class="tab tab-block">
            <li class="tab-item">
                <c:url var="urlPatientView" value="/patient/view.html">
                    <c:param name="id" value="${patient.id}"/>
                </c:url>
                <a href="${urlPatientView}">
                <fmt:message key="patient.view.panel.profile"/></a>
            </li>

            <li class="tab-item">
                <c:url var="urlPatientTreatment"
                       value="/patient/view/treatment/list.html">
                    <c:param name="id" value="${patient.id}"/>
                </c:url>
                <a href="${urlPatientTreatment}">
                <fmt:message key="patient.view.panel.treatment"/></a>
            </li>

           <li class="tab-item active">
                <c:url var="urlPatientHistory"
                       value="/patient/view/disease_history.html">
                    <c:param name="id" value="${patient.id}"/>
                </c:url>
                <a href="${urlPatientHistory}">
                    <fmt:message key="patient.view.panel.history"/>
                </a>
            </li>
        </ul>
    </nav>

    <h5 class="centered">${title}</h5>
    <div class="panel-body">
        <div class="columns">
            <div class="column col-12">
                <c:url var="urlPatientDiagnosisView" value="/patient/view/diagnosis/view.html">
                    <c:param name="id" value="${patientDiagnosis.id}"/>
                </c:url>
                <c:url var="urlPatientDiagnosisSave" value="/patient/view/diagnosis/save.html"/>
                <c:url var="urlPatientDiagnosisDelete" value="/patient/view/diagnosis/delete.html"/>
                <form class="form-horizontal" action="${urlPatientDiagnosisSave}" method="post">
                    <input name="patientId" value="${patient.id}" type="hidden"/>
                    <c:if test="${not empty patientDiagnosis.id}">
                        <input name="id" value="${patientDiagnosis.id}" type="hidden">
                    </c:if>
                    <div class="form-group">
                        <div class="col-4">
                            <label class="form-label" for="diagnosis.title"><fmt:message key="patient.view.diagnosis.edit.form.diagnosis.title"/>:</label>
                        </div>
                        <div class="col-5">
                            <select class="form-select" id="diagnosis.title" name="diagnosis.title">
                                <%--<c:forEach var="diagnosis" items="${diagnoses}">--%>
                                    <%--<c:if test="${diagnosis.id eq patientDiagnosis.diagnosis.id}">--%>
                                        <%--<c:set var="currentDiagnosisTitle" value="${diagnosis.title}"/>--%>
                                    <%--</c:if>--%>
                                <%--</c:forEach>--%>
                                <%--<option>${currentDiagnosisTitle}</option>--%>
                                <option>${previousDiagnosisTitle}</option>
                                <c:forEach var="diagnosis" items="${diagnoses}">
                                    <c:if test="${diagnosis.title != previousDiagnosisTitle}">
                                        <option>${diagnosis.title}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <%--<div class="form-group">--%>
                        <%--<div class="col-4">--%>
                            <%--<label class="form-label" for="consultation_date"><fmt:message key="patient.view.diagnosis.edit.form.consultation.date"/>:</label>--%>
                        <%--</div>--%>
                        <%--<div class="col-5">--%>
                            <%--<input class="form-input" id="consultation_date" name="consultation_date" type="date" placeholder="00" value=<fmt:formatDate pattern="dd.MM.yyyy, HH.mm" value="${patientDiagnosis.consultationDate}"/>/>--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <button class="btn btn-primary btn-action btn-lg">
                        <i class="icon icon-check"></i>
                    </button>

                    <c:if test="${not empty patientDiagnosis.id}">
                        <c:if test="${not patientDiagnosisCanBeDeleted}">
                            <c:set var="disabled" value="disabled"/>
                        </c:if>

                        <button class="btn btn-primary btn-action btn-lg" formaction="${urlPatientDiagnosisDelete}" formmethod="post" ${disabled}>
                            <i class="icon icon-delete"></i>
                        </button>
                    </c:if>

                    <button class="btn btn-primary btn-action btn-lg" type="reset">
                        <i class="icon icon-refresh"></i>
                    </button>

                    <button class="btn btn-primary btn-action btn-lg" formaction="${urlPatientDiagnosisView}" formmethod="get">
                        <i class="icon icon-back"></i>
                    </button>
                </form>
            </div>
        </div>
    </div>

</u:patient_view>
