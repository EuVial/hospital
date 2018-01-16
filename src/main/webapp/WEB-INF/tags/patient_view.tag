<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:message var="title" key="patient.view.title"/>
<u:html title="${title}">

    <div id="panels" class="container">
        <div class="columns">
            <div class="column col-12 col-xs-12">
                <div class="panel">
                    <div class="panel-header text-center">
                        <h4 class="s-title text-center">${title}</h4>
                        <figure class="avatar avatar-xl">
                            <img src="/img/avatars/patient.png" alt="Patient avatar">
                        </figure>
                        <div class="panel-title h5 mt-10">
                            ${patient.firstName} ${patient.lastName}
                        </div>
                        <div class="panel-subtitle">
                            <c:choose>
                                <c:when test="${patient.ward == 0}">
                                    <fmt:message key="patient.view.table.discharged"/>
                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="patient.view.table.ward"/>${patient.ward}
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <jsp:doBody/>
                </div>
            </div>
        </div>
    </div>
</u:html>
