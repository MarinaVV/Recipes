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

  				<!-- Modal content -->
 				<div class="modal-content">
   			 		<div class="modal-header">
    		 			<span class="close" onclick="closeModal()">&times;</span>
    		 			<input id="modal_hidden_recipeId" class="hidden_inputs" type="hidden">
     		 			<div class="modal_title_div">
     		 				<label id="modal_title"></label>
     		 			</div>
     		 			<div class="modal_category_div">
     		 				<label id="modal_category"></label>
     		 			</div>
    				</div>
   			 		<div class="modal-body">
   			 			<div class="modal_image_ingredients">
     						<div class = "modal_image_part">
     							<div class="modal_previwe_image" id="modal_previwe_image">
     							
     							</div>
     							<div class="modal_all_images">
     								<div id="primary_img" onclick="previewImage(primary_img)"></div>
     								<div id="secondery_img_1" onclick="previewImage(secondery_img_1)"></div>
     								<div id="secondery_img_2" onclick="previewImage(secondery_img_2)"></div>
     								<div id="secondery_img_3" onclick="previewImage(secondery_img_3)"></div>
     								<div id="secondery_img_4" onclick="previewImage(secondery_img_4)"></div>
     							</div>
     						</div>
     						<div class="modal_ingredients_part">
     							<div id="modal_recipe_ingredients">
     							
     							</div>
     						</div>
     					</div>
     					<div class="modal_description">
     						<textarea id="modal_description_ta" rows="40" cols="5" readonly></textarea>
     					</div>
     					<button id="modal_delete" onclick="deleteRecipe(modal_hidden_recipeId)">Delete Recipe</button>
     					<button id="modal_update">Update Recipe</button>
    		 		</div>
  				</div>

			</div>
	</div>
</body>
</html>