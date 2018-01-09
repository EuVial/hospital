<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:message var="title" key="patient.view.title"/>
<u:html title="${title}">

    <div id="panels" class="container">
        <div class="columns">
            <div class="column col-10 col-xs-12">
                <div class="panel">
                    <div class="panel-header text-center">
                        <h4 class="s-title text-center">${title}</h4>
                        <figure class="avatar avatar-xl">
                            <img src="../../../img/avatars/patient.png" alt="Patient avatar">
                        </figure>
                            <div class="panel-title h5 mt-10">${patient.firstName} ${patient.lastName}</div>
                            <div class="panel-subtitle"><fmt:message key="patient.view.table.ward"/>${patient.ward}</div>
                    </div>

                    <nav class="panel-nav">
                        <ul class="tab tab-block">
                            <li class="tab-item active">
                                <c:url var="urlPatientView" value="/patient/view.html">
                                    <c:param name="id" value="${patient.id}"/>
                                </c:url>
                                <a href="${urlPatientView}"><fmt:message key="patient.view.panel.profile"/></a>
                            </li>

                            <li class="tab-item active">
                                <c:url var="urlPatientTreatment" value="/patient/treatment.html">
                                    <c:param name="id" value="${patient.id}"/>
                                </c:url>
                                <a href="${urlPatientView}"><fmt:message key="patient.view.panel.treatment"/></a>
                            </li>

                            <li class="tab-item active">
                                <c:url var="urlPatientHistory" value="/patient/history.html">
                                    <c:param name="id" value="${patient.id}"/>
                                </c:url>
                                <a href="${urlPatientView}"><fmt:message key="patient.view.panel.history"/></a>
                            </li>
                        </ul>
                    </nav>

                    <div class="panel-body">
                        <table class="table">
                            <tr>
                                <th><fmt:message key="patient.view.table.id"/></th>
                                <td>${patient.id}</td>
                            </tr>
                            <tr>
                                <th><fmt:message key="patient.view.table.firstname"/></th>
                                <td>${patient.firstName}</td>
                            </tr>
                            <tr>
                                <th><fmt:message key="patient.view.table.lastname"/></th>
                                <td>${patient.lastName}</td>
                            </tr>
                            <tr>
                                <th><fmt:message key="patient.view.table.ward"/></th>
                                <td>${patient.ward}</td>
                            </tr>
                            <tr>
                                <th>&nbsp</th>
                                <td>
                                    <c:if test="${currentUser.role eq 'DOCTOR'}">
                                        <c:url var="urlPatientEdit" value="/patient/edit.html">
                                            <c:param name="id" value="${patient.id}"/>
                                        </c:url>
                                        <a href="${urlPatientEdit}">
                                            <button class="btn btn-primary btn-action btn-lg">
                                                <i class="icon icon-edit"></i>
                                            </button>
                                        </a>
                                    </c:if>
                                    <c:url var="urlPatientList" value="/patient/list.html"/>
                                    <a href="${urlPatientList}">
                                        <button class="btn btn-primary btn-action btn-lg">
                                            <i class="icon icon-back"></i>
                                        </button>
                                    </a>
                                </td>
                            </tr>
                        </table>
                    </div>



                    <%--<c:if test="${currentUser.role eq 'DOCTOR'}">--%>
                        <%--<c:url var="urlPatientEdit" value="/patient/edit.html">--%>
                            <%--<c:param name="id" value="${patient.id}"/>--%>
                        <%--</c:url>--%>
                        <%--<a href="${urlPatientEdit}" class="edit-button"><fmt:message key="patient.view.button.edit"/></a>--%>
                    <%--</c:if>--%>

                    <%--<c:url var="urlPatientList" value="/patient/list.html"/>--%>
                    <%--<a href="${urlPatientList}" class="back"><fmt:message key="patient.view.button.cancel"/></a>--%>
                </div>
            </div>
        </div>
    </div>
    <%--<h3><fmt:message key="patient.view.subtitle"/></h3>--%>
    <%--<table>--%>
        <%--<tr>--%>
            <%--<th colspan="2"><fmt:message key="patient.view.history.table.source"/></th>--%>
            <%--<th colspan="2"><fmt:message key="patient.view.history.table.destination"/></th>--%>
            <%--<th rowspan="2"><fmt:message key="patient.view.history.table.amount"/></th>--%>
            <%--<th rowspan="2"><fmt:message key="patient.view.history.table.date"/></th>--%>
            <%--<th rowspan="2"><fmt:message key="patient.view.history.table.operator"/></th>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<th><fmt:message key="patient.view.history.table.patient.id"/></th>--%>
            <%--<th><fmt:message key="patient.view.history.table.patient.client"/></th>--%>
            <%--<th><fmt:message key="patient.view.history.table.patient.id"/></th>--%>
            <%--<th><fmt:message key="patient.view.history.table.patient.client"/></th>--%>
        <%--</tr>--%>
        <%--<c:forEach var="transfer" items="${patient.history}">--%>
            <%--<tr>--%>
                <%--<c:choose>--%>
                    <%--<c:when test="${not empty transfer.source}">--%>
                        <%--<td class="content">${transfer.source.id}</td>--%>
                        <%--<td class="content">${transfer.source.client}</td>--%>
                    <%--</c:when>--%>
                    <%--<c:otherwise>--%>
                        <%--<td colspan="2" class="content"><fmt:message key="patient.view.history.table.source.empty"/></td>--%>
                    <%--</c:otherwise>--%>
                <%--</c:choose>--%>
                <%--<c:choose>--%>
                    <%--<c:when test="${not empty transfer.destination}">--%>
                        <%--<td class="content">${transfer.destination.id}</td>--%>
                        <%--<td class="content">${transfer.destination.client}</td>--%>
                    <%--</c:when>--%>
                    <%--<c:otherwise>--%>
                        <%--<td colspan="2" class="content"><fmt:message key="patient.view.history.table.destination.empty"/></td>--%>
                    <%--</c:otherwise>--%>
                <%--</c:choose>--%>
                <%--<td class="content">${transfer.amount}</td>--%>
                <%--<td class="content"><fmt:formatDate pattern="dd.MM.yyyy, HH:mm" value="${transfer.date}"/></td>--%>
                <%--<td class="content">${transfer.operator.login}</td>--%>
            <%--</tr>--%>
        <%--</c:forEach>--%>
    <%--</table>--%>
</u:html>