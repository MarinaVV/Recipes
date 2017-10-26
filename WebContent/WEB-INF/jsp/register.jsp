<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/register.css">
</head>
<body>
	<form action="/web.eng.recipes/RegisterServlet" method="post">
		<div class="outer">
			<div class="inner">
				Username: <input class="inner_input" type="text" name="uname">
			</div>
			<div class="inner">
				Password: <input class="inner_input" type="text" name="pass">
			</div>
			<div class="inner">
				Password conformation: <input class="inner_input" type="text" name="passconf">
			</div>
			<div class="inner">
				<input   class="button" type="submit" value="Register">
			</div>
		</div>
	</form>
</body>
</html>