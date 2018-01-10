<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<u:patient_view>
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
</u:patient_view>