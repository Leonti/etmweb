<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New password</title>
</head>
<body>
<form:form modelAttribute="resetForm" method="POST">

	<form:label path="password">New password:</form:label>
	<form:errors path="password" cssClass="error"/><br />
	<form:password path="password"/><br />

	<form:label path="repeatPassword">Repeat password:</form:label>
	<form:errors path="repeatPassword" cssClass="error"/><br />
	<form:password path="repeatPassword"/><br />
	
	<input type="submit" value="Save new password" />	
</form:form>

</body>
</html>