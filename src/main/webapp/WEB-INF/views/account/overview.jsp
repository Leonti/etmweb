<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="bootstrap" tagdir="/WEB-INF/tags/bootstrap" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<spring:message code="overview.pageTitle" var="title"/>    
<jsp:include page="../common/header.jsp" flush="true" >
	<jsp:param name="title" value="${title}" /> 
</jsp:include>

    <div class="container">
	    
	    <div class="row">

		    <div class="span6">
				<div class="well">
					<h1><spring:message code="overview.header"/></h1>
					<div class="row">
						<div class="span2"><spring:message code="overview.companyName"/></div><div class="span2"><c:out value="${tenant.companyName}" /></div>
					</div>
					<div class="row">
						<div class="span2"><spring:message code="overview.companyEmail"/></div><div class="span2"><c:out value="${tenant.email}" /></div>
					</div>		
				</div>
				<a href="/account/edit"><spring:message code="overview.editAccount" /></a>		    	        
		    </div>
	    </div>

    </div> <!-- /container -->
    
<jsp:include page="../common/footer.jsp" flush="true" />