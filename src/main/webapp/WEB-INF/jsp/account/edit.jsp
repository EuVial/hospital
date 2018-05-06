<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:message key="account.edit.title" var="title"/>
<u:html title="${title}">
    <h5>${title}</h5>
    <div class="columns">
        <div class="column col-12">
            <c:url var="urlAccountSave" value="/account/save.html"/>
            <form class="form-horizontal" action="${urlAccountSave}" method="post">
                <div class="form-group">
                    <div class="col-4">
                        <label class="form-label" for="first_name"><fmt:message key="account.edit.form.firstname"/>:</label>
                    </div>
                    <div class="col-5">
                        <input class="form-input" id="first_name" name="first_name" placeholder="<fmt:message key="account.edit.form.firstname"/>" value="${currentUser.firstName}">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-4">
                        <label class="form-label" for="last_name"><fmt:message key="account.edit.form.lastname"/>:</label>
                    </div>
                    <div class="col-5">
                        <input class="form-input" id="last_name" name="last_name" placeholder="<fmt:message key="account.edit.form.lastname"/>" value="${currentUser.lastName}">
                    </div>
                </div>

                <button class="btn btn-primary btn-action btn-lg">
                    <i class="icon icon-check"></i>
                </button>

                <button class="btn btn-primary btn-action btn-lg" type="reset">
                    <i class="icon icon-refresh"></i>
                </button>

                <c:url var="urlIndex" value="/index.html"/>
                <button class="btn btn-primary btn-action btn-lg" formaction="${urlIndex}" formmethod="get">
                    <i class="icon icon-back"></i>
                </button>
            </form>

            <div class="form-group">
                <c:if test="${not empty param.message}">
                    <span class="text-error"><fmt:message key="${param.message}"/></span>
                </c:if>
            </div>

        </div>
    </div>
</u:html>