<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Provide your email to sent your password reset link</title>
</head>
<body>
<form:form modelAttribute="forgotForm" method="POST">
	<form:label path="email">Email:</form:label>
	<form:errors path="email" cssClass="error"/><br />
	<form:input path="email"/><br />
	
	<input type="submit" value="Send reset link" />	
</form:form>

</body>
</html>