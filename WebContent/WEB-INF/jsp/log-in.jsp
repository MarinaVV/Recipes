<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/log-in.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/log-in.js"></script>
</head>
<body>
	<p id="is_invalid"></p>
	<script>
		var is_invalid=${is_invalid};
		if(is_invalid == true){
			document.getElementById("is_invalid").innerHTML="Username or password is incorrect!"
		}
	</script>
	<form id="log_in_form" action="${pageContext.request.contextPath}/UserControlServlet"
		method="post">
		<div class="outer">
			<div class="inner">
				Username: <input id="uname" class="inner_input" type="text" name="username">
			</div>
			<div class="inner">
				Password: <input id="pass" class="inner_input" type="password" name="password">
			</div>
			<input id="action" class="inner_input" type="hidden" name="action">
		</div>
	</form>
	<div class="inner">
		<input class="button" type="button" value="Log in" onclick="login()">
		<input class="button" type="button" value="Register" onclick="document.location.href='UserControlServlet?action=register'">
	</div>
	
</body>
</html>