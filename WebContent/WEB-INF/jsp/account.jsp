<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/nav-bar.css"> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/shared-design.css"> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/account.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/modal.css">  

<script type="text/javascript" src="${pageContext.request.contextPath}/js/nav-bar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/account.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/utils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/search-recipes-ajax-call.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/modal.js"></script>
</head>
<body>
	<div class="outer_container">
		<p>ACc</p>
		
		<!-- Navigation bar -->
		<div id="nav_bar_container">
		<script>
			createNavBar();
		</script>
		</div>
		
		<div class="padding_div"> </div>
		
		<!--  BODY -->
		<div class="body_container">
		
			<div class="menu_div">
				<div class="menu_item">
					<label>Profile</label>
				</div>
				<div class="menu_item" onclick="showMyRecipes()">
					<label>My recipes</label>
				</div>
				<div class="menu_item" onclick="showFavoriteRecipes()">
					<label>Favorite recipes</label>
				</div>
			</div>
			
			<div id="information_div" class="information_div">
			
			</div>
			
			
		</div>
		
		<!-- The Modal -->
		<div id="myModal" class="modal">



		</div>
	</div>
</body>
</html>