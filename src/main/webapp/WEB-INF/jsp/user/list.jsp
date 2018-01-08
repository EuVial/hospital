<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="resources"/>

<fmt:message key="user.list.title" var="title"/>

<fmt:message key="user.list.title" var="application.title"/>

<u:html title="${title}">
    <h4>${application.title}</h4>
    <table class="table table-striped">
        <thead>
            <tr>
                <td><fmt:message key="user.list.table.login"/></td>
                <td><fmt:message key="user.list.table.firstname"/></td>
                <td><fmt:message key="user.list.table.lastname"/></td>
                <td><fmt:message key="user.list.table.role"/></td>
                <%--<th>Специализация</th>--%>
                <td>&nbsp;</td>
            </tr>
        </thead>
        <%--<jsp:useBean id="users" scope="request" type="java.util.List"/>--%>
        <%--TODO: delete current logged user from list--%>
        <c:forEach var="user" items="${users}">
            <tbody>
                <tr>
                    <td class="content">${user.login}</td>
                    <td class="content">${user.firstName}</td>
                    <td class="content">${user.lastName}</td>
                    <td class="content"><fmt:message key="${user.role.name}"/> </td>
                    <%--<td class="content">${user.specialization.title}</td>--%>
                    <td class="empty">
                        <c:url var="urlUserEdit" value="/user/edit.html">
                            <c:param name="id" value="${user.id}"/>
                        </c:url>
                        <a href="${urlUserEdit}" class="edit"></a>
                    </td>
                </tr>
            </tbody>
        </c:forEach>
    </table>
    <c:url var="urlUserEdit" value="/user/edit.html"/>
    <a href="${urlUserEdit}" class="add-button"><fmt:message key="user.list.button.add"/></a>
</u:html>