<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="bootstrap" tagdir="/WEB-INF/tags/bootstrap" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<spring:message code="log.pageTitle" var="title"/>    
<jsp:include page="../common/header.jsp" flush="true" >
	<jsp:param name="title" value="${title}" /> 
</jsp:include>

    <div class="container">

		<div class="row">
			<form:form modelAttribute="logParametersForm" method="POST" class="form-inline">
				<form:select path="employeeId" items="${employeeList}" itemValue="id" itemLabel="name" />
				<form:input path="startDate" cssClass="input-medium" labelMessage="log.start" id="startDate" value="${defaultStartDate}" />
				<form:input path="endDate" cssClass="input-medium" labelMessage="log.end" id="endDate" value="${defaultEndDate}" />
			
				<spring:message code="log.submit" var="submit" /> 
				<input type="submit" value="${submit}" class="btn" />								
			</form:form>
		</div>

	<c:if test="${not empty employee}">
		<jsp:include page="timelist.jsp" flush="true" />
	</c:if>

    </div> <!-- /container -->
    
    

<jsp:include page="../common/footer.jsp" flush="true" />