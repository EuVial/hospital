<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%--<fmt:setBundle basename="messages"/>--%>

<fmt:message key="user.list.title" var="title"/>

<fmt:message key="user.list.title" var="application.title"/>

<u:html title="${title}">

    <c:if test="${not empty user.id}">
        <input name="id" value="${user.id}" type="hidden">
    </c:if>
    <div class="container">
        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th><fmt:message key="user.list.table.login"/></th>
                    <th><fmt:message key="user.list.table.firstname"/></th>
                    <th><fmt:message key="user.list.table.lastname"/></th>
                    <th><fmt:message key="user.list.table.role"/></th>
                    <th>&nbsp;</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${users}">
                    <c:if test="${user.id != currentUser.id}">
                        <tr>
                            <td>${user.login}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <td><fmt:message key="${user.role.name}"/></td>
                            <td>
                                <c:url var="urlUserEdit" value="/user/edit.html">
                                    <c:param name="id" value="${user.id}"/>
                                </c:url>
                                <a href="${urlUserEdit}">
                                    <button class="btn btn-primary btn-action btn-lg">
                                        <i class="icon icon-edit"></i>
                                    </button>
                                </a>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
                <tr>
                    <td></td><td></td><td></td><td></td>
                    <td>
                        <c:url var="urlUserEdit" value="/user/edit.html"/>
                        <a href="${urlUserEdit}">
                            <button class="btn btn-primary btn-action btn-lg">
                                <i class="icon icon-plus"></i>
                            </button>
                        </a>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</u:html>