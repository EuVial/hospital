<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>

<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
    <%--diagnosis.title, user.first_name, user.last_name, user.role_id,
    patient_diagnosis.consultation_date--%>
    <fmt:message var="title" key="patient.view.diagnosis.view.title"/>
    <h5 class="centered">${title}</h5>
    <div class="panel-body">
        <table class="table">
            <tr>
                <th><fmt:message key="patient.view.diagnosis.view.table.diagnosis.title"/></th>
                <td>${patientDiagnosis.diagnosis.title}</td>
            </tr>
            <tr>
                <th><fmt:message key="patient.view.diagnosis.view.table.doctor.firstname"/></th>
                <td>${patientDiagnosis.doctor.firstName}</td>
            </tr>
            <tr>
                <th><fmt:message key="patient.view.diagnosis.view.table.doctor.lastname"/></th>
                <td>${patientDiagnosis.doctor.lastName}</td>
            </tr>
            <tr>
                <th><fmt:message key="patient.view.diagnosis.view.table.doctor.role"/></th>
                <td><fmt:message key="${patientDiagnosis.doctor.role.name}"/></td>
                <%--Must be always "doctor"--%>
            </tr>
            <tr>
                <th><fmt:message key="patient.view.diagnosis.view.table.consultation.date"/></th>
                <td><fmt:formatDate pattern="dd.MM.yy, HH:mm" value="${patientDiagnosis.consultationDate}"/></td>
            </tr>
            <tr>
                <th>&nbsp</th>
                <td>
                    <c:if test="${currentUser.role eq 'DOCTOR'}">
                        <c:url var="urlPatientDiagnosisEdit" value="/patient/view/diagnosis/edit.html">
                            <c:param name="id" value="${patientDiagnosis.id}"/>
                        </c:url>
                        <a href="${urlPatientDiagnosisEdit}">
                            <button class="btn btn-primary btn-action btn-lg">
                                <i class="icon icon-edit"></i>
                            </button>
                        </a>
                    </c:if>
                    <c:url var="urlDiseaseHistory" value="/patient/view/disease_history.html">
                        <c:param name="id" value="${patient.id}"/>
                    </c:url>
                    <a href="${urlDiseaseHistory}">
                        <button class="btn btn-primary btn-action btn-lg">
                            <i class="icon icon-back"></i>
                        </button>
                    </a>
                </td>
            </tr>
        </table>
    </div>
</u:patient_view>