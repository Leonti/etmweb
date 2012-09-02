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
				<jsp:include page="../common/loginform.jsp" flush="true" />       
		    </div>
	    </div>

    </div> <!-- /container -->
    
<jsp:include page="../common/footer.jsp" flush="true" />