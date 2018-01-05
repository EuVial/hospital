<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="resources"/>

<fmt:message var="title" key="index.title"/>

<u:html title="${title}">
    <h2><fmt:message key="user.list.title"/></h2>
    <c:url var="patientList" value="/patient/list.html"/>
    <p><a href="${patientList}"><fmt:message key="patient.list.title"/></a></p>
    <c:url var="userList" value="/user/list.html"/>
    <p><a href="${userList}"><fmt:message key="user.list.title"/></a></p>
</u:html>
