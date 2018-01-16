<%@page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:message var="title" key="error.title"/>
<u:without_sidebar title="Error page">
    Hi there!<br/>
    Error code is ${pageContext.errorData.statusCode}.<br/>
    Please go to <a href="/index.html">home page</a>.
</u:without_sidebar>