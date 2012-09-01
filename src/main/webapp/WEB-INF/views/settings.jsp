<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="bootstrap" tagdir="/WEB-INF/tags/bootstrap" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<spring:message code="settings.pageTitle" var="title"/>    
<jsp:include page="common/header.jsp" flush="true" >
	<jsp:param name="title" value="${title}" /> 
</jsp:include>

    <div class="container">

		<div class="row">
			<form:form modelAttribute="settingsForm" method="POST">


<form:select path="timeZone">
    <form:options items="${timeZones}" />
</form:select>

				<bootstrap:input path="dayRegularOvertimeLimit" labelMessage="settings.dayRegularOvertimeLimit" />
				<bootstrap:input path="dayExtraOvertimeLimit" labelMessage="settings.dayExtraOvertimeLimit" />
				<bootstrap:input path="consecutiveDaysLimit" labelMessage="settings.consecutiveDaysLimit" />
				<bootstrap:input path="weekOvertimeLimit" labelMessage="settings.weekOvertimeLimit" />
				<bootstrap:input path="regularOvertimeMultiplier" labelMessage="settings.regularOvertimeMultiplier" />
				<bootstrap:input path="extraOvertimeMultiplier" labelMessage="settings.extraOvertimeMultiplier" />
						
				<spring:message code="settings.submit" var="submit" /> 
				<input type="submit" value="${submit}" class="btn" />								
			</form:form>
		</div>

    </div> <!-- /container -->
    
    

<jsp:include page="common/footer.jsp" flush="true" />