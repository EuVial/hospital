<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="resources"/>

<fmt:message key="patient.list.title" var="title"/>

<fmt:message key="patient.list.title" var="application.title"/>

<u:html title="${title}">
    <h2>${application.title}</h2>
    <table>
        <tr>
            <th><fmt:message key="patient.list.table.firstname"/></th>
            <th><fmt:message key="patient.list.table.lastname"/></th>
            <th><fmt:message key="patient.list.table.ward"/></th>
            <td>&nbsp;</td>
        </tr>
        <c:forEach var="patient" items="${patients}">
            <tr>
                <td class="content">${patient.firstName}</td>
                <td class="content">${patient.lastName}</td>
                <td class="content">${patient.ward}</td>
                <td class="empty">
                    <c:url var="urlPatientView" value="/patient/view.html">
                        <c:param name="id" value="${patient.id}"/>
                    </c:url>
                    <a href="${urlPatientView}" class="view"></a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <c:url var="urlPatientView" value="/patient/view.html"/>
    <a href="${urlPatientView}" class="add-button"><fmt:message key="patient.list.button.add"/></a>
</u:html>