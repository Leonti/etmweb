<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="bootstrap" tagdir="/WEB-INF/tags/bootstrap" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<spring:message code="employee.listPageTitle" var="title"/>    
<jsp:include page="../common/header.jsp" flush="true" >
	<jsp:param name="title" value="${title}" /> 
</jsp:include>

    <div class="container">

	    <div class="row">
	    	<a href="/employee/add"><spring:message code="employee.addLink" /></a>
		    <div class="span12">
		    	<table class="table table-striped">
		    		<thead>
		    			<tr>
		    				<th><spring:message code="employee.name" /></th>
		    				<th><spring:message code="employee.code" /></th>
		    				<th><spring:message code="employee.jobs" /></th>
		    				<th></th>
		    			</tr>
		    		</thead>
		    		<tbody>
		    		<c:forEach var="employee" items="${list}">
		    			<tr>
		    				<td>${employee.name}</td>
		    				<td>${employee.code}</td>
		    				<td>
		    					<table class="table table-condensed">
		    						<thead>
		    							<tr>
		    								<th><spring:message code="job.title" /></th>
		    								<th><spring:message code="job.wage" /></th>
		    								<th></th>
		    							</tr>
		    						</thead>
		    						<tbody>
			    					<c:forEach var="job" items="${employee.jobs}">
			    						<tr>
			    							<td>${job.title}</td>
			    							<td>${job.wage}</td>
			    							<td>
												<form:form modelAttribute="employeeJobForm" action="/employee/removejob?id=${employee.id}" method="POST" class="form-search">												
													<form:hidden path="jobId" value="${job.id}" />
													<spring:message code="employee.removeJob" var="submit" /> 
													<input type="submit" value="${submit}" class="btn" />		
												</form:form>			    							
			    							</td>
			    						</tr>	    						  
			    					</c:forEach>
			    					</tbody>
		    					</table>
		    				
								<form:form modelAttribute="employeeJobForm" action="/employee/addjob?id=${employee.id}" method="POST" class="form-search">
								
									<form:select path="jobId" items="${jobList}" itemValue="id" itemLabel="title" />
																	
									<spring:message code="employee.addJob" var="submit" /> 
									<input type="submit" value="${submit}" class="btn" />		
								</form:form>		    				
		    				</td>
		    				<td><a href="/employee/edit?id=${employee.id}"><spring:message code="employee.editLink" /></a></td>
		    			</tr>	
		    		</c:forEach>
		    		</tbody>
		    	</table>
		    </div>
	    </div>

    </div> <!-- /container -->
    
<jsp:include page="../common/footer.jsp" flush="true" />