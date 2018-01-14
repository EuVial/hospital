<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<u:patient_view>
    <nav class="panel-nav">
        <ul class="tab tab-block">
            <li class="tab-item">
                <c:url var="urlPatientView" value="/patient/view.html">
                    <c:param name="id" value="${patient.id}"/>
                </c:url>
                <a href="${urlPatientView}"><fmt:message key="patient.view.panel.profile"/></a>
            </li>

            <li class="tab-item active">
                <c:url var="urlPatientTreatment" value="/patient/view/treatment/list.html">
                    <c:param name="id" value="${patient.id}"/>
                </c:url>
                <a href="${urlPatientTreatment}"><fmt:message key="patient.view.panel.treatment"/></a>
            </li>

            <li class="tab-item">
                <c:url var="urlPatientHistory" value="/patient/view/disease_history.html">
                    <c:param name="id" value="${patient.id}"/>
                </c:url>
                <a href="${urlPatientHistory}"><fmt:message key="patient.view.panel.history"/></a>
            </li>
        </ul>
    </nav>

    <fmt:message var="tableTitle" key="patient.view.treatment.list.title"/>
    <h5 class="centered">${tableTitle}</h5>
    <div class="panel-body">
        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th><fmt:message key="patient.view.treatment.list.table.treatmentTitle"/></th>
                    <th><fmt:message key="patient.view.treatment.list.table.treatmentType"/></th>
                    <th><fmt:message key="patient.view.treatment.list.table.patientDiagnosis"/></th>
                    <%--<th><fmt:message key="patient.view.treatment.list.table.performerRole"/></th>--%>
                    <th><fmt:message key="patient.view.treatment.list.table.performerName"/></th>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                </tr>
            </thead>
                <%--TODO: delete current logged user from list--%>
            <tbody>
                <jsp:useBean id="treatments" scope="request" type="java.util.List"/>
                <c:forEach var="treatment" items="${treatments}">
                    <tr>
                        <td>${treatment.title}</td>
                        <td><fmt:message key="${treatment.type.name}"/></td>
                        <td>${treatment.diagnosisToPatient.diagnosis.title}</td>
                        <%--<td><fmt:message key="${treatment.diagnosisToPatient.doctor.role.name}"/></td>--%>
                        <td>${treatment.diagnosisToPatient.doctor.firstName} ${treatment.diagnosisToPatient.doctor.lastName}</td>

                        <td>
                            <c:if test="${treatment.diagnosisToPatient.doctor.id == currentUser.id}">
                                <c:url var="urlTreatmentEdit" value="/patient/view/treatment/edit.html">
                                    <c:param name="id" value="${treatment.id}"/>
                                </c:url>
                                <a href="${urlTreatmentEdit}">
                                    <button class="btn btn-primary btn-action btn-lg">
                                        <i class="icon icon-edit"></i>
                                    </button>
                                </a>
                            </c:if>
                        </td>


                        <td>
                            <c:if test="${(currentUser.role eq 'DOCTOR') || !(treatment.type eq 'SURGERY')}">
                                <c:url var="urlTreatmentDone" value="/patient/view/treatment/done.html"/>
                                    <%--<c:param name="id" value="${treatment.id}"/>--%>
                                <%--</c:url>--%>
                                <form action="${urlTreatmentDone}" method="post">
                                    <input name="id" value="${treatment.id}" type="hidden"/>
                                    <button class="btn btn-primary btn-action btn-lg" type="submit">
                                        <i class="icon icon-check"></i>
                                    </button>
                                </form>
                                <%--<a href="${urlTreatmentDone}">--%>
                                    <%--<button class="btn btn-primary btn-action btn-lg">--%>
                                        <%--<i class="icon icon-check"></i>--%>
                                    <%--</button>--%>
                                <%--</a>--%>
                            </c:if>


                        </td>

                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</u:patient_view>