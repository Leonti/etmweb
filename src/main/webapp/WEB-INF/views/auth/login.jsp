<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="bootstrap" tagdir="/WEB-INF/tags/bootstrap" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
    
<spring:message code="login.pageTitle" var="title"/>    
<jsp:include page="../common/header.jsp" flush="true" >
	<jsp:param name="title" value="${title}" /> 
</jsp:include>

    <div class="container">

	<c:if test="${not empty param.error}">
	    <div class="row">
	    	<div class="offset3 span6 alert alert-error">
	  		   <spring:message code="login.error" />	    	
	    	</div>
	    </div>
	</c:if>	    
	    <div class="row">

		    <div class="span4 offset4">
				<form method="POST" class="well" action="../j_spring_security_check">

				    <div class="control-group">
				        <label class="control-label" for="j_username">${label}</label>
				        <div class="controls">
				            <input id="j_username" name="j_username" type="text" />
				        </div>
				    </div>
				    <div class="control-group">
				        <label class="control-label" for="j_password">${label}</label>
				        <div class="controls">
				            <input id="j_password" name="j_password" type="password" />
				        </div>
				    </div>			

					<div class="control-group">
						<div class="controls">
						<label class="checkbox">
							<input type="checkbox" name='_spring_security_remember_me' checked="checked"> 
							<spring:message code="login.rememberMe" />
						</label>
						</div>
					</div>
					
					<div class="form-actions">
						<spring:message code="login.submit" var="submit" /> 
						<input type="submit" value="${submit}" class="btn btn-primary btn-large" />
					</div>	
				</form>	        
		    </div>
	    </div>

    </div> <!-- /container -->
    
<jsp:include page="../common/footer.jsp" flush="true" />