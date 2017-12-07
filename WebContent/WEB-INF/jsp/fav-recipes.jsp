<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/nav-bar.css"> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/shared-design.css"> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/nav-bar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/utils.js"></script>
</head>
<body>

	<div class="outer_container">
		<p>Fav rec</p>
		<div id="nav_bar_container">
		<script>
			createNavBar();
		</script>
		
		<div class="padding_div"> </div>
		
			<!--  BODY -->
		<div class="body_container">
			<div id="category_bar" class="category_bar">
					<script type="text/javascript">
						createCategoryBar();
					</script>
				</div>
				<hr>
				<div id="results">
				
				</div>
		</div>
		
		</div>
	</div>
</body>
</html>