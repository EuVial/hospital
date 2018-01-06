<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:message var="title" key="patient.view.title"/>
<u:html title="${title}">
    <h2>${title}</h2>
    <table>
        <tr>
            <th><fmt:message key="patient.view.table.id"/></th>
            <td class="content">${patient.id}</td>
        </tr>
        <tr>
            <th><fmt:message key="patient.view.table.firstname"/></th>
            <td class="content">${patient.firstName}</td>
        </tr>
        <tr>
            <th><fmt:message key="patient.view.table.lastname"/></th>
            <td class="content">${patient.lastName}</td>
        </tr>
        <tr>
            <th><fmt:message key="patient.view.table.ward"/></th>
            <td class="content">${patient.ward}</td>
        </tr>
    </table>
    <c:url var="urlPatientEdit" value="/patient/edit.html">
        <c:param name="id" value="${patient.id}"/>
    </c:url>
    <a href="${urlPatientEdit}" class="edit-button"><fmt:message key="patient.view.button.edit"/></a>
    <c:url var="urlPatientList" value="/patient/list.html"/>
    <a href="${urlPatientList}" class="back"><fmt:message key="patient.view.button.cancel"/></a>
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