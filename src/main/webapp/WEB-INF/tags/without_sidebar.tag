<%@tag import="java.util.Locale" %>
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
        <c:url var="urlCss2" value="/spectre-icons.css"/>
        <link href="${urlCss2}" rel="stylesheet">
    </head>
    <body>
        <%--<c:if test="${not empty currentUser}">--%>
            <%--<c:url var="urlLogout" value="/logout.html"/>--%>
            <%--<div class="off-canvas-toggle">--%>
                <%--<div class="form-group">--%>
                    <%--<fmt:message key="application.logged"/> ${currentUser.login}--%>
                    <%--(<span class="text-success"><fmt:message key="${currentUser.role.name}"/></span>).--%>
                <%--</div>--%>
                <%--<div class="form-group">--%>
                    <%--<a href="${urlLogout}">--%>
                        <%--<fmt:message key="application.button.logout"/>--%>
                    <%--</a>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</c:if>--%>
        <div class="columns">
            <div class="column col-xs-12">
                <div class="empty">
                    <div class="form-group">
                        <h4><fmt:message key="application.title"/></h4>
                    </div>

                    <div class="empty-icon">
                        <i class="icon icon-3x icon-people">
                        </i>
                    </div>

                    <div class="column col-2 col-mx-auto">
                        <jsp:doBody/>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>