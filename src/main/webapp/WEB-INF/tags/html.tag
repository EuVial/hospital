<%@tag language="java" pageEncoding="UTF-8"%>

<%@attribute name="title" required="true" rtexprvalue="true" type="java.lang.String"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%--<c:set var="language" value="${pageContext.request.locale.language}"/>--%>

<%--<c:out value="${pageContext.request.locale.language}"/>--%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>${title}</title>
        <c:url var="urlCss" value="/spectre.css"/>
        <link href="${urlCss}" rel="stylesheet">
        <c:url var="urlCss2" value="/spectre-exp.css"/>
        <link href="${urlCss2}" rel="stylesheet">
        <c:url var="urlCss3" value="/docs.css"/>
        <link href="${urlCss3}" rel="stylesheet">
        <c:url var="urlCss4" value="/spectre-icons.css"/>
        <link href="${urlCss4}" rel="stylesheet">
    </head>
    <body>
        <%--<div class="docs-navbar">--%>
            <%--<c:choose>--%>
                <%--<c:when test="${pageContext.request.locale.language eq 'ru'}">--%>
                    <%--<c:set var="language" value="en"/>--%>
                <%--</c:when>--%>
                <%--<c:when test="${pageContext.request.locale.language eq 'en'}">--%>
                    <%--<c:set var="language" value="ru"/>--%>
                <%--</c:when>--%>
                <%--<c:otherwise>--%>
                    <%--<c:set var="language" value="unk"/>--%>
                <%--</c:otherwise>--%>
            <%--</c:choose>--%>
            <%--<a href="<fmt:setLocale value="${language}" scope="session"/>">--%>
                <%--<button class="btn btn-link">--%>
                    <%--${language}--%>
                <%--</button>--%>
            <%--</a>--%>
        <%--</div>--%>

        <div class="columns">

            <div class="column col-2 col-ml-auto">
                <ul class="menu">
                    <li class="menu-item">
                        <div class="tile tile-centered">
                            <div class="tile-icon">
                                <c:choose>
                                    <c:when test="${currentUser.role eq 'ADMIN'}">
                                        <img src="/img/avatars/admin.png" class="avatar avatar-xl" alt="admin logo">
                                    </c:when>
                                    <c:when test="${currentUser.role eq 'DOCTOR'}">
                                        <img src="/img/avatars/doctor.png" class="avatar avatar-xl" alt="doctor logo">
                                    </c:when>
                                    <c:when test="${currentUser.role eq 'NURSE'}">
                                        <img src="/img/avatars/nurse.png" class="avatar avatar-xl" alt="nurse logo">
                                    </c:when>
                                </c:choose>
                            </div>
                            <div class="tile-content">${currentUser.login}<br/>
                                <fmt:message key="${currentUser.role.name}"/><br/>
                                <%--<c:url var="urlLogout" value="/logout.html"/>--%>
                                <%--<a href="${urlLogout}">--%>
                                    <%--<img src="../../img/action/logout.png" class="avatar" alt="logout">--%>
                                <%--</a>--%>
                            </div>
                        </div>
                    </li>

                    <li class="divider"></li>

                    <li class="menu-item">
                        <c:choose>
                            <c:when test="${currentUser.role eq 'ADMIN'}">
                                <c:url var="urlUserList" value="/user/list.html"/>
                                <a href="${urlUserList}"><fmt:message key="user.list.title"/></a>
                            </c:when>
                            <c:otherwise>
                                <c:url var="urlPatientList" value="/patient/list.html"/>
                                <a href="${urlPatientList}"><fmt:message key="patient.list.title"/></a>
                            </c:otherwise>
                        </c:choose>
                    </li>

                    <li class="menu-item">
                        <c:choose>
                            <c:when test="${currentUser.role eq 'ADMIN'}">
                                <c:url var="urlUserEdit" value="/user/edit.html"/>
                                <a href="${urlUserEdit}"><fmt:message key="user.edit.title.add"/></a>
                            </c:when>
                            <c:otherwise>
                                <c:url var="urlPatientEdit" value="/patient/edit.html"/>
                                <a href="${urlPatientEdit}"><fmt:message key="patient.edit.title.add"/></a>
                            </c:otherwise>
                        </c:choose>
                    </li>

                    <li class="menu-item">
                        <c:url var="urlPasswordEdit" value="/password/edit.html"/>
                        <a href="${urlPasswordEdit}">
                            <fmt:message key="application.button.password.change"/>
                        </a>
                    </li>

                    <li class="divider"></li>

                    <li class="menu-item">
                        <c:url var="urlLogout" value="/logout.html"/>
                        <a href="${urlLogout}">
                            <fmt:message key="application.button.logout"/>
                        </a>
                    </li>
                </ul>
            </div>
            <div class="column col-6 col-mr-auto">
                <%--<div id="content" class="docs-content off-canvas-content">--%>
                <jsp:doBody/>
            </div>
        </div>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>

    </body>
</html>