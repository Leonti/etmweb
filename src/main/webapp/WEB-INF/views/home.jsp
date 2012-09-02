<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="bootstrap" tagdir="/WEB-INF/tags/bootstrap" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
     
<jsp:include page="common/header.jsp" flush="true" >
	<jsp:param name="title" value="ETM home" /> 
</jsp:include>

    <div class="container">
   
	    <div class="row">

			<div class="span8">
				<h1>Welcome to ETM</h1>
				<p class="lead">
					ETM is a web service for employers that track time employees spend on their tasks by providing easy to use time tracking interface.
				</p>
				<h2>Who is it for?</h2>
				<p>Organizations where job pays are hourly based such as hotels, restaurants, stores.</p>
				<h2>Key features</h2>
				<ul>
					<li>Employees log in and log off through simple web interface with personal codes
					<li>Each employee can have multiple jobs assigned to him
					<li><strong>Overtimes</strong> are calculated with ability to adjust to organization overtime policy
				</ul>	
				<h2>Installation and usage</h2>
				<p>
					All you need is a computer with internet connection for employees to log in/out of the jobs.
					Employee interface is optimized for touch screens, but can be used with mouse.
				</p>
			</div>

		    <div class="span4">
				<jsp:include page="common/loginform.jsp" flush="true" />
				<p class="lead">
					<a href="/account/register">Create account</a>       
				</p>
		    </div>
	    </div>

    </div> <!-- /container -->
    
<jsp:include page="common/footer.jsp" flush="true" />