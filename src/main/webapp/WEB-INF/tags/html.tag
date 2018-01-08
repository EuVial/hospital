<%@tag language="java" pageEncoding="UTF-8"%>

<%@attribute name="title" required="true" rtexprvalue="true" type="java.lang.String"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <c:url var="urlCss" value="/spectre.css"/>
    <link href="${urlCss}" rel="stylesheet">
</head>
    <body>
        <div class="column col-4 col-xs-12">

        </div>
        <div class="columns">
            <h3><fmt:message key="application.title"/></h3>
            <c:if test="${not empty currentUser}">
                <c:url var="urlLogout" value="/logout.html"/>
                <div class="column col-2">
                    <fmt:message key="application.welcome"/> ${currentUser.login} (<fmt:message key="${currentUser.role.name}"/>).
                    <a href="${urlLogout}">
                        <fmt:message key="application.button.logout"/>
                    </a>
                </div>
            </c:if>
            <jsp:doBody/>
        </div>
    </body>
</html>