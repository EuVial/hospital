<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="resources"/>

<fmt:message key="user.list.title" var="title"/>

<fmt:message key="user.list.title" var="application.title"/>

<u:html title="${title}">
    <h2>${application.title}</h2>
    <table>
        <tr>
            <th><fmt:message key="user.list.table.login"/></th>
            <th><fmt:message key="user.list.table.firstname"/></th>
            <th><fmt:message key="user.list.table.lastname"/></th>
            <th><fmt:message key="user.list.table.role"/></th>
            <%--<th>Специализация</th>--%>
            <td>&nbsp;</td>
        </tr>
        <%--<jsp:useBean id="users" scope="request" type="java.util.List"/>--%>
        <c:forEach var="user" items="${users}">
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
        </c:forEach>
    </table>
    <c:url var="urlUserEdit" value="/user/edit.html"/>
    <a href="${urlUserEdit}" class="add-button"><fmt:message key="user.list.button.add"/></a>
</u:html>