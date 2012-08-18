<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="bootstrap" tagdir="/WEB-INF/tags/bootstrap" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<spring:message code="register.pageTitle" var="title"/>    
<jsp:include page="../common/header.jsp" flush="true" >
	<jsp:param name="title" value="${title}" /> 
</jsp:include>

    <div class="container">

	    <div class="row">
	    	<div class="span6">
	  		   	<h1>Bootstrap starter template</h1>
	  	    	<p>Use this document as a way to quick start any new project.<br> All you get is this message and a barebones HTML document.</p>	    	
	    	</div>

		    <div class="span4">
				<form:form modelAttribute="tenantForm" method="POST" class="well">
				
					<bootstrap:input path="companyName" labelMessage="register.companyName" />
					<bootstrap:input path="email" labelMessage="register.login" />
					<bootstrap:input path="password" labelMessage="register.password" type="password" />
					<bootstrap:input path="repeatPassword" labelMessage="register.repeatPassword" type="password" />
					
					<div class="form-actions">
						<spring:message code="register.submit" var="submit" /> 
						<input type="submit" value="${submit}" class="btn btn-primary btn-large" />
					</div>		
				</form:form>	        
		    </div>
	    </div>

    </div> <!-- /container -->
    
    

<jsp:include page="../common/footer.jsp" flush="true" />
