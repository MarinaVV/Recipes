<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/register.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/register.js"></script>
</head>
<body>
	<form action="/web.eng.recipes/UserControlServlet" method="post">
		<div class="outer">
			<div class="inner">
				Username: <input id="uname" class="inner_input" type="text">
			</div>
			<div class="inner">
				Password: <input id="pass" class="inner_input" type="text">
			</div>
			<div class="inner">
				Password conformation: <input class="inner_input" type="text">
			</div>
			<div class="inner">
				<input   class="button" type="button" value="Register" onclick="register()">
			</div>
		</div>
	</form>
</body>
</html>