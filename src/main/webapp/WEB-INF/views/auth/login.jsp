<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>
	<h1>Login</h1>

	<div id="login-error">${error}</div>
	
	<form action="../j_spring_security_check" method="post" >
	
	<p>
		<label for="j_username">Username</label>
		<input id="j_username" name="j_username" type="text" />
	</p>
	
	<p>
		<label for="j_password">Password</label>
		<input id="j_password" name="j_password" type="password" />
	</p>
	
	<input  type="submit" value="Login"/>								
		
	</form>
</body>
</html>