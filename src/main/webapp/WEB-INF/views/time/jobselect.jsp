<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	    
	<c:out value="${employee.name}"></c:out>
    </div> <!-- /container -->
    
    

<jsp:include page="common/footer.jsp" flush="true" />