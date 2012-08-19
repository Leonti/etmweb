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
	    	<a href="/employee/add"><spring:message code="employee.addLink" /></a>
		    <div class="span12">
		    	<table class="table table-striped">
		    		<thead>
		    			<tr>
		    				<th>Name</th>
		    				<th>Code</th>
		    				<th>Jobs</th>
		    				<th></th>
		    			</tr>
		    		</thead>
		    		<tbody>
		    		<c:forEach var="employee" items="${list}">
		    			<tr>
		    				<td>${employee.name}</td>
		    				<td>${employee.code}</td>
		    				<td></td>
		    				<td><a href="/employee/edit?id=${employee.id}"><spring:message code="employee.editLink" /></a></td>
		    			</tr>	
		    		</c:forEach>
		    		</tbody>
		    	</table>
		    </div>
	    </div>

    </div> <!-- /container -->
    
<jsp:include page="../common/footer.jsp" flush="true" />