<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="bootstrap" tagdir="/WEB-INF/tags/bootstrap" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<spring:message code="time.jobselectPageTitle" var="title"/>    
<jsp:include page="common/header.jsp" flush="true" >
	<jsp:param name="title" value="${title}" /> 
</jsp:include>

    <div class="container">
	
	<div class="row offset2">    
		<h1>Hello, <c:out value="${employee.name}"></c:out>!</h1><br>
	</div>
	
	<c:if test="${isWorking}">
		<div class="row offset2">
			<h2>
			<spring:message code="time.currentPosition" /> "<c:out value="${currentJob.title}" />"
			</h2><br>
		</div>    

		<div class="row offset2">
			<form:form modelAttribute="jobSelectForm" action="/time/selectjob" method="POST">
			
				<form:input type="hidden" path="code" value="${employee.code}" />
				<form:input type="hidden" path="jobId" value="0" />
			
				<spring:message code="time.signOut" var="submit" />
				<input type="submit" value="${submit}" class="btn btn-primary span8 signOutButton" />
			</form:form>		
		</div>
		<div class="row offset2">
		<c:if test="fn:length(employeeJobs) gt 1">
			<h1>
				<spring:message code="time.switchJobs" />
			</h1><br>
		</c:if>	
		</div>
	</c:if>	
	
	<c:forEach  var="job" items="${employeeJobs}">
	
		<c:if test="${ !(isWorking && job.id == currentJob.id) }" >
		<div class="row offset2">
			<form:form modelAttribute="jobSelectForm" action="/time/selectjob" method="POST">
			
				<form:input type="hidden" path="code" value="${employee.code}" />
				<form:input type="hidden" path="jobId" value="${job.id}" />
			
				<input type="submit" value="${job.title}" class="btn span8 jobButton" />
			</form:form>
		</div>	
		</c:if>
	</c:forEach>
	
    </div> <!-- /container -->
    
    

<jsp:include page="common/footer.jsp" flush="true" />