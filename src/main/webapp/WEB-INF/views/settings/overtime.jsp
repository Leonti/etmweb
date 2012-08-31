<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="bootstrap" tagdir="/WEB-INF/tags/bootstrap" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<spring:message code="overtime.settings.pageTitle" var="title"/>    
<jsp:include page="../common/header.jsp" flush="true" >
	<jsp:param name="title" value="${title}" /> 
</jsp:include>

    <div class="container">

		<div class="row">
			<form:form modelAttribute="overtimeSettingsForm" method="POST">

				<bootstrap:input path="dayRegularOvertimeLimit" labelMessage="overtime.settings.dayRegularOvertimeLimit" />
				<bootstrap:input path="dayExtraOvertimeLimit" labelMessage="overtime.settings.dayExtraOvertimeLimit" />
				<bootstrap:input path="consecutiveDaysLimit" labelMessage="overtime.settings.consecutiveDaysLimit" />
				<bootstrap:input path="weekOvertimeLimit" labelMessage="overtime.settings.weekOvertimeLimit" />
				<bootstrap:input path="regularOvertimeMultiplier" labelMessage="overtime.settings.regularOvertimeMultiplier" />
				<bootstrap:input path="extraOvertimeMultiplier" labelMessage="overtime.settings.extraOvertimeMultiplier" />
						
				<spring:message code="overtime.settings.submit" var="submit" /> 
				<input type="submit" value="${submit}" class="btn" />								
			</form:form>
		</div>

    </div> <!-- /container -->
    
    

<jsp:include page="../common/footer.jsp" flush="true" />