<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="resources"/>

<c:if test="${empty user}">
    <jsp:useBean id="user" class="dao.entity.user.User"/>
</c:if>
<c:choose>
    <c:when test="${not empty user.id}">
        <fmt:message var="title" key="user.edit.title.edit"/>
    </c:when>
    <c:otherwise>
        <fmt:message var="title" key="user.edit.title.add"/>
    </c:otherwise>
</c:choose>

<u:html title="${title}">
    <h2>${title}</h2>
    <c:url var="urlUserList" value="/user/list.html"/>
    <c:url var="urlUserSave" value="/user/save.html"/>
    <c:url var="urlUserDelete" value="/user/delete.html"/>
    <form action="${urlUserSave}" method="post">
        <c:if test="${not empty user.id}">
            <input name="id" value="${user.id}" type="hidden">
        </c:if>
        <label for="login"><fmt:message key="user.edit.form.login"/>:</label>
        <input id="login" name="login" value="${user.login}">

        <label for="first_name"><fmt:message key="user.edit.form.firstname"/>:</label>
        <input id="first_name" name="first_name" value="${user.firstName}">

        <label for="last_name"><fmt:message key="user.edit.form.lastname"/>:</label>
        <input id="last_name" name="last_name" value="${user.lastName}">

        <%--<label for="specialization">Специализация пользователя:</label>--%>
        <%--<select id="specialization" name="specialization">--%>
               <%--value="${user.specialization.title}">--%>
            <%--<c:forEach var="specialization" items="${specializations}">--%>
                <%--<c:choose>--%>
                    <%--<c:when test="${specialization.id == user.specialization.id}">--%>
                        <%--<c:set var="selected" value="selected"/>--%>
                    <%--</c:when>--%>
                    <%--<c:otherwise>--%>
                        <%--<c:remove var="selected"/>--%>
                    <%--</c:otherwise>--%>
                <%--</c:choose>--%>
                <%--<option value="${specialization.id}" ${selected}>${specialization.title}</option>--%>
            <%--</c:forEach>--%>

        <label for="role">
            <fmt:message key="user.edit.form.role"/>:
        </label>

        <select id="role" name="role">
            <c:forEach var="role" items="${roles}">
                <c:choose>
                    <c:when test="${role.id == user.role.id}">
                        <c:set var="selected" value="selected"/>
                    </c:when>
                    <c:otherwise>
                        <c:remove var="selected"/>
                    </c:otherwise>
                </c:choose>
                <option value="${role.id}" ${selected}>${role.name}</option>
            </c:forEach>
        </select>

            <%--TODO: ADD PASSWORD RESET BUTTON--%>
        <button class="save"><fmt:message key="user.edit.button.save"/></button>

        <c:if test="${not empty user.id}">
            <c:if test="${not userCanBeDeleted}">
                <c:set var="disabled" value="disabled"/>
            </c:if>

            <button class="delete" formaction="${urlUserDelete}" formmethod="post" ${disabled}>
                <fmt:message key="user.edit.button.delete"/>
            </button>
        </c:if>

        <button class="reset" type="reset">
            <fmt:message key="user.edit.button.reset"/>
        </button>

        <button class="back" formaction="${urlUserList}" formmethod="get">
            <fmt:message key="user.edit.button.cancel"/>
        </button>
    </form>
</u:html>