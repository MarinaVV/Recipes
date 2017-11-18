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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/modal.css">  

<script type="text/javascript" src="${pageContext.request.contextPath}/js/nav-bar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/find-recipes.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/search-recipes-ajax-call.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/modal.js"></script>
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
				
				<button id="search_button" onclick="search_recipe_I_L()">Search</button>			
			</div>
			
			<div class="result_part">
				<div class="category_bar">
					<label class="category">All</label>
					<label class="category">Breakfast</label>
					<label class="category">Lunch</label>
					<label class="category">Dinner</label>
					<label class="category">Snack</label>
				</div>
				<hr>
				<div id="results">
				</div>
				
			</div>
			
					<!-- The Modal -->
			<div id="myModal" class="modal">

  				<!-- Modal content -->
 				<div class="modal-content">
   			 		<div class="modal-header">
    		 			<span class="close" onclick="closeModal()">&times;</span>
     		 			<label id="modal_title"></label>
     		 			<label id="modal_category"></label>
    				</div>
   			 		<div class="modal-body">
   			 			<div class="modal_image_ingredients">
     						<div class = "modal_image_part">
     							<div class="modal_previwe_image" id="modal_previwe_image">
     							
     							</div>
     							<div class="modal_all_images">
     								<div id="primary_img"></div>
     								<div id="secondery_img_1"></div>
     								<div id="secondery_img_2"></div>
     								<div id="secondery_img_3"></div>
     								<div id="secondery_img_4"></div>
     							</div>
     						</div>
     						<div class="modal_ingredients_part">
     						</div>
     					</div>
     					<div class="modal_description">
     						<textarea id="modal_description_ta" rows="40" cols="5" readonly></textarea>
     					</div>
     					<button id="modal_add_favorite">Add to Favorites</button>
    		 		</div>
  				</div>

			</div>
		</div>
	</div>
</body>
</html>