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

    <fmt:message var="title" key="patient.view.disease_history.title"/>
    <h5 class="centered">${title}</h5>
    <div class="panel-body">
        <div class="timeline">
            <c:forEach var="patientDiagnosis" items="${patientDiagnoses}" varStatus="status">
                <div class="timeline-item">
                    <div class="timeline-left">
                        <c:choose>
                            <c:when test="${!status.last}">
                                <a class="timeline-icon icon-lg tooltip">
                                    <i class="icon icon-check"></i>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a class="timeline-icon tooltip"></a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="timeline-content">
                        <div class="tile">
                            <div class="tile-content">
                                <div class="tile-subtitle text-primary">
                                    ${patientDiagnosis.diagnosis.title}<br/>
                                    <fmt:formatDate pattern="dd.MM.yy, HH:mm" value="${patientDiagnosis.consultationDate}"/><br/>
                                    <fmt:message key="patient.view.disease_history.tile.treatment"/>
                                    <%--Diagnosis name--%>
                                </div>
                                <c:forEach var="treatment" items="${patientDiagnosis.history}">
                                <div class="tile-title">
                                    <c:url var="urlTreatmentView" value="/patient/view/treatment/view.html">
                                        <c:param name="id" value="${treatment.id}"/>
                                        <c:param name="patientId" value="${patient.id}"/>
                                    </c:url>
                                    <a href="${urlTreatmentView}">
                                        <button class="btn btn-action btn-sm">
                                            <i class="icon icon-more-horiz"></i>
                                        </button>
                                    </a>
                                    <c:choose>
                                        <c:when test="${treatment.type.name eq 'treatment.procedure'}">
                                            <img src="/img/treatments/procedure.png" height="25"/>
                                        </c:when>
                                        <c:when test="${treatment.type.name eq 'treatment.medicament'}">
                                            <img src="/img/treatments/medicament.png" height="25"/>
                                        </c:when>
                                        <c:when test="${treatment.type.name eq 'treatment.surgery'}">
                                            <img src="/img/treatments/surgery.png" height="25"/>
                                        </c:when>
                                        <c:otherwise>
                                            &nbsp;
                                        </c:otherwise>
                                    </c:choose>
                                        <%--Treatments name--%>
                                    ${treatment.title}
                                </div>
                                </c:forEach>
                            </div>
                            <div class="tile-action">
                                <c:url var="urlPatientEdit" value="/patient/view/diagnosis/view.html"/>
                                    <%--<c:param name="id" value="${patientDiagnosis.id}"/>--%>
                                    <%--<c:param name="patientId" value="${patient.id}"/>--%>
                                <%--</c:url>--%>

                                <form action="${urlPatientEdit}" method="get">
                                    <input name="id" value="${patientDiagnosis.id}" type="hidden"/>
                                    <input name="patientId" value="${patient.id}" type="hidden"/>
                                    <button class="btn" type="submit">
                                        <fmt:message key="patient.view.disease_history.button.view"/>
                                    </button>
                                </form>

                                <%--<a href="${urlPatientEdit}">--%>
                                    <%--<button class="btn">--%>
                                        <%--<fmt:message key="patient.view.disease_history.button.view"/>--%>
                                    <%--</button>--%>
                                <%--</a>--%>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</u:patient_view>