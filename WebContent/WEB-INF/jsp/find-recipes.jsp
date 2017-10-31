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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/find-recipes.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/search-recipes.js"></script>
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
				<div class="search_row">
					<label>Username:</label> 
					<input id="username" type="text" name="username">
				</div>
				
				<div class="search_row">
					<label>Recipe name: </label> 
					<input id="recipe_name" type="text" name="recipe_name">
				</div>
				
				<div class="search_row">
					<label>Categorie: </label> 
					<input id="categorie" type="text" name="categorie">
				</div>
				
				
				<div class="ingredient_lsit">
				<label>Ingredients</label>
				<input type="hidden" value="1" id="count_ingredients_input">
					<div id="ingredients">
						<div id="div_ingredient_1" class="ingredient">
							<input type="text" id="input_ingredient_1" name="ingredient_input"><button id="delete_button_1"  onclick="">-</button>
						</div>
					</div>
					<button id=add_button onclick="add_ingredient()">+</button>
				</div>
				
				<button id="search_button" onclick="search_recipes()">Search</button>			
			</div>
			
			<div class="result_part">
			fff
			</div>
		</div>
	</div>
</body>
</html>