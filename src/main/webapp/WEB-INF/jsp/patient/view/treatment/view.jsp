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

            <li class="tab-item">
                <c:url var="urlPatientTreatment" value="/patient/view/treatment/list.html">
                    <c:param name="id" value="${patient.id}"/>
                </c:url>
                <a href="${urlPatientTreatment}"><fmt:message key="patient.view.panel.treatment"/></a>
            </li>

            <li class="tab-item active">
                <c:url var="urlPatientHistory" value="/patient/view/disease_history.html">
                    <c:param name="id" value="${patient.id}"/>
                </c:url>
                <a href="${urlPatientHistory}"><fmt:message key="patient.view.panel.history"/></a>
            </li>
        </ul>
    </nav>

    <div class="panel-body">
        <table class="table">
            <tr>
                <th><fmt:message key="patient.view.treatment.view.table.title"/></th>
                <td>${treatment.title}</td>
            </tr>

            <tr>
                <th><fmt:message key="patient.view.treatment.view.table.type"/></th>
                <td><fmt:message key="${treatment.type.name}"/></td>
            </tr>

            <tr>
                <th><fmt:message key="patient.view.treatment.view.table.diagnosis"/></th>
                <td>${treatment.diagnosisToPatient.diagnosis.title}</td>
            </tr>

            <tr>
                <th><fmt:message key="patient.view.treatment.view.table.performer.name"/></th>
                <td>
                    <c:if test="${not empty treatment.performer.firstName}">
                        <fmt:message key="${treatment.performer.role.name}"/> ${treatment.performer.firstName} ${treatment.performer.lastName}
                    </c:if>
                </td>
            </tr>

            <tr>
                <th><fmt:message key="patient.view.treatment.view.table.done"/></th>
                <td>
                    <%--<c:if test="${treatment.isDone}">--%>
                        <%--<fmt:message key="patient.view.treatment.view.table.done.true"/>--%>
                    <%--</c:if>--%>

                    <%--<c:if test="${not treatment.isDone}">--%>
                        <%--<fmt:message key="patient.view.treatment.view.table.done.false"/>--%>
                    <%--</c:if>--%>
                    <c:choose>
                        <c:when test="${treatment.isDone}">
                            <fmt:message key="patient.view.treatment.view.table.done.true"/>
                        </c:when>
                        <c:otherwise>
                            <fmt:message key="patient.view.treatment.view.table.done.false"/>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <th>&nbsp</th>
                <td>
                    <c:if test="${not treatment.isDone}">
                        <c:if test="${treatment.diagnosisToPatient.doctor.id == currentUser.id}">
                            <c:url var="urlTreatmentEdit" value="/patient/view/treatment/edit.html">
                                <c:param name="id" value="${treatment.id}"/>
                                <c:param name="patientId" value="${patient.id}"/>
                            </c:url>
                            <a href="${urlTreatmentEdit}">
                                <button class="btn btn-primary btn-action btn-lg">
                                    <i class="icon icon-edit"></i>
                                </button>
                            </a>
                            <%--<form action="${urlTreatmentEdit}" method="get">--%>
                                <%--<input name="id" value="${treatment.id}" type="hidden"/>--%>
                                <%--<input name="patientId" value="${patient.id}" type="hidden"/>--%>
                                <%--<button class="btn btn-primary btn-action btn-lg">--%>
                                    <%--<i class="icon icon-edit"></i>--%>
                                <%--</button>--%>
                            <%--</form>--%>
                        </c:if>

                        <c:if test="${(currentUser.role eq 'DOCTOR') || !(treatment.type eq 'SURGERY')}">
                            <c:url var="urlTreatmentDone" value="/patient/view/treatment/done.html">
                                <c:param name="id" value="${treatment.id}"/>
                            </c:url>
                            <a href="${urlTreatmentDone}">
                                <button class="btn btn-primary btn-action btn-lg">
                                    <i class="icon icon-check"></i>
                                </button>
                            </a>
                            <%--<form action="${urlTreatmentDone}" method="post">--%>
                                <%--<input name="id" value="${treatment.id}" type="hidden"/>--%>
                                <%--<button class="btn btn-primary btn-action btn-lg" type="submit">--%>
                                    <%--<i class="icon icon-check"></i>--%>
                                <%--</button>--%>
                            <%--</form>--%>
                        </c:if>

                        <c:if test="${treatment.diagnosisToPatient.doctor.id == currentUser.id}">
                            <c:url var="urlTreatmentDelete" value="/patient/view/treatment/delete.html">
                                <c:param name="id" value="${treatment.id}"/>
                            </c:url>
                            <a href="${urlTreatmentDelete}">
                                <button class="btn btn-primary btn-action btn-lg">
                                    <i class="icon icon-delete"></i>
                                </button>
                            </a>
                        </c:if>
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
