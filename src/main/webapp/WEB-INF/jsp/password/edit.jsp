<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:message var="title" key="password.change.title"/>
<u:html title="${title}">
    <h5 class="centered">${title}</h5>
    <c:url var="urlPasswordSave" value="/password/save.html"/>
    <div class="columns">
        <div class="column col-12">
            <form action="${urlPasswordSave}" method="post">
                <div class="form-group">
                    <div class="col-4">
                        <label for="old-password"><fmt:message key="password.change.form.old.password"/>:</label>
                    </div>
                    <div class="col-5">
                        <input id="old-password" name="old-password" type="password">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-4">
                        <label for="new-password"><fmt:message key="password.change.form.new.password"/>:</label>
                    </div>
                    <div class="col-5">
                        <input id="new-password" name="new-password" type="password">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-4">
                        <label for="new-password-repeat"><fmt:message key="password.change.form.new.password.repeat"/>:</label>
                    </div>
                    <div class="col-5">
                        <input id="new-password-repeat" name="new-password-repeat" type="password">
                    </div>
                </div>

                <div class="col-4">
                </div>
                <div class="col-5">
                    <button class="btn"><fmt:message key="password.change.button.save"/></button>

                    <c:url var="urlBack" value="/index.html"/>
                    <a href="${urlBack}">
                        <button class="btn btn-action">
                            <i class="icon icon-back"></i>
                        </button>
                    </a>
                </div>

                <div class="form-group">
                    <c:if test="${not empty param.message}">
                        <span class="text-error"><fmt:message key="${param.message}"/></span>
                    </c:if>
                </div>
            </form>
        </div>
    </div>
</u:html>
