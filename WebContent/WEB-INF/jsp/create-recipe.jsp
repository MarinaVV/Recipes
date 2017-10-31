<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/nav-bar.css"> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/shared-design.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/create-recipe.css">
 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/nav-bar.js"></script>
</head>
<body>
	<div class="outer_container">
		<p>Create rec</p>
		
		<!-- Navigation bar -->
		<div id="nav_bar_container">
		<script>
			createNavBar();
		</script>
		</div>
		
		<div class="padding_div"> </div>
		
		<!--  BODY -->
		<div class="body_container">
			<div class="title_category_div">
				<input id="title_input" type="text" value="Add Title">
				<select id="category_select"></select> 
			</div>
			
			<div class="ingredients_images_div">
				<div class="ingredients_div">
					<label>Ingredients</label>
					<div id="ingredients">
						<div class="ingredient">
							<input id="ingredient_input" type="text" name="ingredients_input" value=""> <input 
							id="quantity_input" type="text" name="quantity_input" value=""><select 
							id="unit_select"></select><button class="delete_button" id="delete_button_1"  onclick="">-</button>
						</div>				
					</div>
				</div>
				<div class="images_div">
				
				</div>
			</div>
			
			<div class="description_div">
				<input id="description_input" type="text" value="Add Description">
			</div>
			
			<div class="last_buttons_div">
				<button>Review</button>
				<button>Create recipe</button>
			</div>
		</div>
	</div>
</body>
</html>