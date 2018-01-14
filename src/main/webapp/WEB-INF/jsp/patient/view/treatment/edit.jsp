<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>

<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${empty treatment}">
    <jsp:useBean id="treatment" class="domain.patient.Treatment"/>
</c:if>
<c:choose>
    <c:when test="${not empty treatment.id}">
        <fmt:message var="title" key="patient.view.treatment.edit.title.edit"/>
    </c:when>
    <c:otherwise>
        <fmt:message var="title" key="patient.view.treatment.edit.title.add"/>
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
                    <fmt:message key="patient.view.panel.profile"/>
                </a>
            </li>

            <li class="tab-item active">
                <c:url var="urlPatientTreatment"
                       value="/patient/view/treatment/list.html">
                    <c:param name="id" value="${patient.id}"/>
                </c:url>
                <a href="${urlPatientTreatment}">
                    <fmt:message key="patient.view.panel.treatment"/>
                </a>
            </li>

            <li class="tab-item">
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
                <c:url var="urlTreatmentSave" value="/patient/view/treatment/save.html"/>
                <c:url var="urlTreatmentDelete" value="/patient/view/treatment/delete.html"/>
                <form class="form-horizontal" action="${urlTreatmentSave}" method="post">
                    <c:if test="${not empty treatment.id}">
                        <input name="id" value="${treatment.id}" type="hidden"/>
                        <%--<input name="diagnosis_title" value="${treatment.diagnosisToPatient.diagnosis.title}" type="hidden"/>--%>
                    </c:if>

                    <div class="form-group">
                        <div class="col-4">
                            <label class="form-label" for="diagnosisTitle"><fmt:message key="patient.view.treatment.edit.form.treatment.title"/>:</label>
                        </div>
                        <div class="col-5">
                            <select class="form-select" id="diagnosisTitle" name="diagnosisTitle">
                                <option>${treatment.diagnosisToPatient.diagnosis.title}</option>
                                <c:forEach var="patientDiagnosis" items="${patient.history}">
                                    <c:if test="${patientDiagnosis.diagnosis.title != treatment.diagnosisToPatient.diagnosis.title}">
                                        <option>${patientDiagnosis.diagnosis.title}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                        <%--<div class="col-5">--%>
                            <%--<input class="form-label" id="diagnosisTitle" name="diagnosisTitle" value="${treatment.diagnosisToPatient.diagnosis.title}">--%>
                        <%--</div>--%>
                    </div>

                    <%--<div class="form-group">--%>
                        <%--<div class="col-4">--%>
                            <%--<label class="form-label" for="diagnosis.title"><fmt:message key="patient.view.diagnosis.edit.form.diagnosis.title"/>:</label>--%>
                        <%--</div>--%>
                        <%--<div class="col-5">--%>
                            <%--<select class="form-select" id="diagnosis.title" name="diagnosis.title">--%>
                                <%--<option>${previousDiagnosisTitle}</option>--%>
                                <%--<c:forEach var="diagnosis" items="${diagnoses}">--%>
                                    <%--<c:if test="${diagnosis.title != previousDiagnosisTitle}">--%>
                                        <%--<option>${diagnosis.title}</option>--%>
                                    <%--</c:if>--%>
                                <%--</c:forEach>--%>
                            <%--</select>--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <div class="form-group">
                        <div class="col-4">
                            <label class="form-label" for="title"><fmt:message key="patient.view.treatment.edit.form.treatment.title"/>:</label>
                        </div>
                        <div class="col-5">
                            <input class="form-input" id="title" name="title" placeholder="<fmt:message key="patient.view.treatment.edit.form.treatment.title"/>" value="${treatment.title}">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-4">
                            <label class="form-label">
                                <fmt:message key="patient.view.treatment.edit.form.treatment.type"/>:
                            </label>
                        </div>
                        <div class="col-5">
                            <c:forEach var="type" items="${types}">
                                <c:choose>
                                    <c:when test="${type.id == treatment.type.id}">
                                        <c:set var="checked" value="checked"/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:remove var="checked"/>
                                    </c:otherwise>
                                </c:choose>

                                <label class="form-radio">
                                    <input type="radio" value="${type.id}" name="type" ${checked}>
                                    <i class="form-icon"></i> <fmt:message key="${type.name}"/>
                                </label>
                            </c:forEach>
                        </div>
                    </div>

                    <button class="btn btn-primary btn-action btn-lg">
                        <i class="icon icon-check"></i>
                    </button>

                    <button class="btn btn-primary btn-action btn-lg" formaction="${urlTreatmentDelete}" formmethod="post">
                        <i class="icon icon-delete"></i>
                    </button>

                    <button class="btn btn-primary btn-action btn-lg" type="reset">
                        <i class="icon icon-refresh"></i>
                    </button>
                </form>
            </div>
        </div>
    </div>

</u:patient_view>
