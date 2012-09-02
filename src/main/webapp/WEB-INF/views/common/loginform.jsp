<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="bootstrap" tagdir="/WEB-INF/tags/bootstrap" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 

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
			<input type="submit" value="${submit}" class="btn btn-primary btn-large" /><br><br>
			<a href="/account/forgot">Forgot your password?</a>
		</div>	
	</form>	 