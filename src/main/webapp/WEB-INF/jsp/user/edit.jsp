<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<c:if test="${empty user}">
    <jsp:useBean id="user" class="domain.user.User"/>
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
    <h4>${title}</h4>
    <div class="columns">
        <div class="column col-12">
            <c:url var="urlUserList" value="/user/list.html"/>
            <c:url var="urlUserSave" value="/user/save.html"/>
            <c:url var="urlUserDelete" value="/user/delete.html"/>
            <form class="form-horizontal" action="${urlUserSave}" method="post">
                <c:if test="${not empty user.id}">
                    <input name="id" value="${user.id}" type="hidden">
                </c:if>

                <div class="form-group">
                    <div class="col-4">
                        <label class="form-label" for="login"><fmt:message key="user.edit.form.login"/>:</label>
                    </div>
                    <div class="col-5">
                        <input class="form-input" id="login" name="login" placeholder="<fmt:message key="user.edit.form.login"/>" value="${user.login}">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-4">
                        <label class="form-label" for="first_name"><fmt:message key="user.edit.form.firstname"/>:</label>
                    </div>
                    <div class="col-5">
                        <input class="form-input" id="first_name" name="first_name" placeholder="<fmt:message key="user.edit.form.firstname"/>" value="${user.firstName}">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-4">
                        <label class="form-label" for="last_name"><fmt:message key="user.edit.form.lastname"/>:</label>
                    </div>
                    <div class="col-5">
                        <input class="form-input" id="last_name" name="last_name" placeholder="<fmt:message key="user.edit.form.lastname"/>" value="${user.lastName}">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-4">
                        <label class="form-label">
                    <fmt:message key="user.edit.form.role"/>:
                        </label>
                    </div>
                    <div class="col-5">
                            <c:forEach var="role" items="${roles}">
                                <c:choose>
                                    <c:when test="${role.id == user.role.id}">
                                        <c:set var="checked" value="checked"/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:remove var="checked"/>
                                    </c:otherwise>
                                </c:choose>

                                <label class="form-radio">
                                    <input type="radio" value="${role.id}" name="role" ${checked}>
                                    <i class="form-icon"></i> <fmt:message key="${role.name}"/>
                                    <%--<option value="${role.id}" ${checked}>${role.name}</option>--%>
                                </label>
                            </c:forEach>
                    </div>
                </div>
                    <%--TODO: ADD PASSWORD RESET BUTTON--%>
                <button class="btn btn-primary btn-action btn-lg">
                    <%--<fmt:message key="user.edit.button.save"/>--%>
                    <i class="icon icon-check"></i>
                </button>

                <c:if test="${not empty user.id}">
                    <c:if test="${not userCanBeDeleted}">
                        <c:set var="disabled" value="disabled"/>
                    </c:if>

                    <button class="btn btn-primary btn-action btn-lg" formaction="${urlUserDelete}" formmethod="post" ${disabled}>
                        <%--<fmt:message key="user.edit.button.delete"/>--%>
                        <i class="icon icon-delete"></i>
                    </button>
                </c:if>

                <button class="btn btn-primary btn-action btn-lg" type="reset">
                    <i class="icon icon-refresh"></i>
                    <%--<fmt:message key="user.edit.button.reset"/>--%>
                </button>

                <button class="btn btn-primary btn-action btn-lg" formaction="${urlUserList}" formmethod="get">
                    <i class="icon icon-back"></i>
                    <%--<fmt:message key="user.edit.button.cancel"/>--%>
                </button>
            </form>
        </div>
    </div>
</u:html>