<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="bootstrap" tagdir="/WEB-INF/tags/bootstrap" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<spring:message code="time.statsPageTitle" var="title"/>    
<jsp:include page="common/header.jsp" flush="true" >
	<jsp:param name="title" value="${title}" /> 
</jsp:include>

    <div class="container">
	
	<div class="row offset2">    
		<h1><c:out value="${employee.name}"></c:out>!</h1><br>
	</div>
	
	<c:if test="${isWorking}">
		<div class="row offset2">
			<h2>
			<spring:message code="time.currentPosition" /> "<c:out value="${currentJob.title}" />"
			</h2><br>
		</div>    		
	</c:if>	
	<c:if test="${!isWorking}">
		<div class="row offset2">
			<h2>
			<spring:message code="time.signedOff" />
			</h2><br>
		</div>   	
	</c:if>

		<div class="row offset2">
			<a href="/time/" class="btn timeBack span8"><spring:message code="time.goBack" /></a>
		</div> 
	
    </div> <!-- /container -->
    
    

<jsp:include page="common/footer.jsp" flush="true" />