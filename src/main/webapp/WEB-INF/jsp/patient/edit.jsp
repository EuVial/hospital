<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:if test="${empty patient}">
    <jsp:useBean id="patient" class="domain.patient.Patient"/>
</c:if>


<c:choose>
    <c:when test="${not empty patient.id}">
        <u:patient_view>
            <nav class="panel-nav">
                <ul class="tab tab-block">
                    <li class="tab-item active">
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

                    <li class="tab-item">
                        <c:url var="urlPatientHistory" value="/patient/view/disease_history.html">
                            <c:param name="id" value="${patient.id}"/>
                        </c:url>
                        <a href="${urlPatientHistory}"><fmt:message key="patient.view.panel.history"/></a>
                    </li>
                </ul>
            </nav>

            <u:patient_edit_body/>

        </u:patient_view>
    </c:when>

    <c:otherwise>
        <u:html title="${title}">
            <u:patient_edit_body/>

        </u:html>
    </c:otherwise>
</c:choose>
