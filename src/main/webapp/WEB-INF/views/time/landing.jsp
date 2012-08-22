<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="bootstrap" tagdir="/WEB-INF/tags/bootstrap" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<spring:message code="time.langingPageTitle" var="title"/>    
<jsp:include page="common/header.jsp" flush="true" >
	<jsp:param name="title" value="${title}" /> 
</jsp:include>

    <div class="container">

		<div class="keyboard">
			<form:form modelAttribute="employeeLoginForm" method="POST">
				<div class="row">
					<div class="span4 offset4 codeInput">
						<bootstrap:input path="code" cssClass="span4" />
					</div>
				</div>
			    <div class="row">
				    <div class="span2 offset2 button">1</div>
				    <div class="span2 button">2</div>
				    <div class="span2 button">3</div>
				    <div class="span2 button">
				    	<spring:message code="time.codeSubmit" var="submit" />
				    	<input type="submit" value="${submit}"/>
				    </div>
			    </div>
			    <div class="row">
				    <div class="span2 offset2 button">4</div>
				    <div class="span2 button">5</div>
				    <div class="span2 button">6</div>
				    <div class="span2 button"> 
						<i class="general foundicon-left-arrow"></i>
					</div>
			    </div>
			    <div class="row">
				    <div class="span2 offset2 button">7</div>
				    <div class="span2 button">8</div>
				    <div class="span2 button">9</div>
				    <div class="span2 button">0</div>
			    </div>
		    </form:form>
	    </div>  	    

    </div> <!-- /container -->
    
    

<jsp:include page="common/footer.jsp" flush="true" />