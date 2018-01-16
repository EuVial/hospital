<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<fmt:message key="patient.list.title" var="title"/>

<u:html title="${title}">
    <c:if test="${not empty user.id}">
        <input name="id" value="${user.id}" type="hidden">
    </c:if>
    <div class="container">
        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th><fmt:message key="patient.list.table.firstname"/></th>
                    <th><fmt:message key="patient.list.table.lastname"/></th>
                    <th><fmt:message key="patient.list.table.ward"/></th>
                    <th>&nbsp;</th>
                </tr>
            </thead>
            <%--TODO: delete current logged user from list--%>
            <tbody>
                <c:forEach var="patient" items="${patients}">
                    <tr>
                        <td class="content">${patient.firstName}</td>
                        <td class="content">${patient.lastName}</td>
                        <td class="content">${patient.ward}</td>
                        <td>
                            <c:url var="urlPatientView" value="/patient/view.html">
                                <c:param name="id" value="${patient.id}"/>
                            </c:url>
                            <a href="${urlPatientView}">
                                <button class="btn btn-primary btn-action btn-lg">
                                    <i class="icon icon-more-horiz"></i>
                                </button>
                            </a>
                        </td>
                    </tr>
                </c:forEach>

                <c:if test="${currentUser.role eq 'DOCTOR'}">
                    <c:url var="urlPatientEdit" value="/patient/edit.html"/>
                    <tr>
                        <td></td><td></td><td></td>
                        <td>
                        <a href="${urlPatientEdit}">
                            <button class="btn btn-primary btn-action btn-lg">
                                <i class="icon icon-plus"></i>
                            </button>
                        </a>
                    </c:if>
                </td>
                </tr>
            </tbody>
        </table>
    </div>
</u:html>