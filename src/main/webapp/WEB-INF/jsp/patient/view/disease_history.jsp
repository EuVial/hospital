<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<u:patient_view>
    <div class="timeline-item">
        <div class="tile">
            <div class="tile-content">
                <div class="tile-subtitle">
                    <c:forEach var="patientDiagnosis" items="${patientDiagnoses}">
                        ${patientDiagnosis.id}
                    </c:forEach>
                    <%--Diagnosis name--%>
                </div>
                <div class="tile-title">
                    <%--Treatments name--%>
                </div>
            </div>
        </div>
    </div>
</u:patient_view>