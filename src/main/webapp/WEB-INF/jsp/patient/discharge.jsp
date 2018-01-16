<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>

<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${empty patientDiagnosis}">
    <jsp:useBean id="patientDiagnosis" class="domain.patient.DiagnosisToPatient"/>
</c:if>

<fmt:message var="title" key="patient.discharge.title"/>
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
                <c:url var="urlPatientDiagnosisSave" value="/patient/discharge/done.html"/>
                <form class="form-horizontal" action="${urlPatientDiagnosisSave}" method="post">
                    <input name="patientId" value="${patient.id}" type="hidden"/>
                    <div class="form-group">
                        <div class="col-4">
                            <label class="form-label" for="diagnosis.title"><fmt:message key="patient.discharge.diagnosis.title"/>:</label>
                        </div>
                        <div class="col-5">
                            <select class="form-select" id="diagnosis.title" name="diagnosis.title">
                                <option>${previousDiagnosisTitle}</option>
                                <c:forEach var="diagnosis" items="${diagnoses}">
                                    <c:if test="${diagnosis.title != previousDiagnosisTitle}">
                                        <option>${diagnosis.title}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <button class="btn btn-primary btn-action btn-lg">
                        <i class="icon icon-check"></i>
                    </button>

                    <button class="btn btn-primary btn-action btn-lg" type="reset">
                        <i class="icon icon-refresh"></i>
                    </button>
                </form>
            </div>
        </div>
    </div>
</u:patient_view>