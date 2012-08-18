<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="bootstrap" tagdir="/WEB-INF/tags/bootstrap" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../common/header.jsp" flush="true" >
	<jsp:param name="title" value="Register new company account" /> 
</jsp:include>

    <div class="container">

	    <div class="row">
	    	<div class="span8">
	  		   	<h1>Bootstrap starter template</h1>
	  	    	<p>Use this document as a way to quick start any new project.<br> All you get is this message and a barebones HTML document.</p>	    	
	    	</div>

		    <div class="span4">
				<form:form modelAttribute="tenantForm" method="POST">
				
					<bootstrap:input path="companyName" label="Company name:" />
					<bootstrap:input path="subdomain" label="Subdomain:" />
					<bootstrap:input path="email" label="Email(login):" />
					<bootstrap:input path="password" label="Password:" type="password" />
					<bootstrap:input path="repeatPassword" label="Repeat password:" type="password" />
					
					<br>
					<input type="submit" value="Register" class="btn btn-primary" />	
				</form:form>	        
		    </div>
	    </div>

    </div> <!-- /container -->
    
    

<jsp:include page="../common/footer.jsp" flush="true" />
