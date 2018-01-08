<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:message var="title" key="login.title"/>

<u:without_sidebar title="${title}">
    <c:url var="urlLogin" value="/login.html"/>
    <form action="${urlLogin}" method="post">
        <div class="form-group">
            <h4>${title}</h4>
        </div>
        <div class="form-group">
            <c:if test="${not empty param.message}">
                <span class="text-error"><fmt:message key="${param.message}"/></span>
            </c:if>
        </div>
        <div class="form-group">
            <label class="form-label" for="login"><fmt:message key="login.form.login"/>:</label>
            <input class="form-input" id="login" name="login">
        </div>
        <div class="form-group">
            <label class="form-label" for="password"><fmt:message key="login.form.password"/>:</label>
            <input class="form-input" type="password" id="password" name="password">
        </div>
        <div class="form-group">
            <button class="btn"><fmt:message key="login.button.login"/></button>
        </div>
    </form>
</u:without_sidebar>
