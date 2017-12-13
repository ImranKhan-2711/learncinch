<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org"
	xmlns:dy="" 
	xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<body>
<div>
<form  method="post" name="loginform">
<input type="text" name="username" />
<input type="password" name="password" />
<input type="submit" value="submit">
</form>
</div>
</body>
</html>