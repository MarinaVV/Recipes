<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/nav-bar.css"> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/shared-design.css"> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/find-recipes.css"> 

<script type="text/javascript" src="${pageContext.request.contextPath}/js/nav-bar.js"></script>
</head>
<body>

	<div class="outer_container">
		<p>find rec</p>
		<div id="nav_bar_container">
		<script>
			createNavBar();
		</script>
		</div>
		
				<div class="padding_div"> </div>
		
		<!--  BODY -->
		<div class="body_container">
			<div class="search_part">
				<p> Choose your search criteria</p>
				<div>
					Username: <input class="inner_input" type="text" name="username">
				</div>
				<div>
					Recipe name: <input class="inner_input" type="text" name="recipe_name">
				</div>
				<div>
					Categorie: <input class="inner_input" type="text" name="username">
				</div>
							
			</div>
			
			<div class="result_part">
			fff
			</div>
		</div>
	</div>
</body>
</html>