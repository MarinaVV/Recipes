<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- <link rel="stylesheet" type="text/css" href="home-page.css"> -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/shared-functions.js"></script>
</head>
<body id="body">
	<p id="p_uname">${uname}</p>
	<script>
		var name = document.getElementById("p_uname");
		localStorage.setItem("uname", name)
	</script>
</body>
</html>