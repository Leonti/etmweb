<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit company info</title>
</head>
<body>
<form:form modelAttribute="editTenantForm" method="POST">
	<form:label path="companyName">Company name:</form:label>
	<form:errors path="companyName" cssClass="error"/><br />
	<form:input path="companyName"/><br />

	<form:label path="subdomain">Subdomain:</form:label>
	<form:errors path="subdomain" cssClass="error"/><br />
	<form:input path="subdomain"/><br />	
	
	<input type="submit" value="Save" />	
</form:form>

<form:form modelAttribute="changePasswordForm" action="savepassword" method="POST">

    <form:label path="password">Password:</form:label>
    <form:errors path="password" cssClass="error"/><br />
    <form:password path="password"/><br />
    
    <form:label path="repeatPassword">Repeat password:</form:label>
    <form:errors path="repeatPassword" cssClass="error"/><br />
    <form:password path="repeatPassword"/><br />    
    
    <input type="submit" value="Change password" />         
</form:form>

</body>
</html>