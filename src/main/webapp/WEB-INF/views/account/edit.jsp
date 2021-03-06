<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="bootstrap" tagdir="/WEB-INF/tags/bootstrap" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<spring:message code="edit.pageTitle" var="title"/>    
<jsp:include page="../common/header.jsp" flush="true" >
	<jsp:param name="title" value="${title}" /> 
</jsp:include>

    <div class="container">

	    <div class="row">
		    <div class="span4 offset2">
				<form:form modelAttribute="editTenantForm" method="POST" class="well">
				
					<bootstrap:input path="companyName" labelMessage="register.companyName" />
					
					<div class="form-actions">
						<spring:message code="edit.submit" var="submit" /> 
						<input type="submit" value="${submit}" class="btn btn-primary btn-large" />
					</div>		
				</form:form>	        
		    </div>
		    
		    <div class="span4">
				<form:form modelAttribute="changePasswordForm" method="POST" action="savepassword" class="well">
				
					<bootstrap:input path="password" labelMessage="register.password" type="password" />
					<bootstrap:input path="repeatPassword" labelMessage="register.repeatPassword" type="password" />
					
					<div class="form-actions">
						<spring:message code="edit.changePassword" var="submit" /> 
						<input type="submit" value="${submit}" class="btn btn-primary btn-large" />
					</div>		
				</form:form>	        
		    </div>		    
	    </div>

    </div> <!-- /container -->
    
    

<jsp:include page="../common/footer.jsp" flush="true" />