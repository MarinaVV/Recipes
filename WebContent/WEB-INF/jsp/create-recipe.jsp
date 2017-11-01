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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/create-recipe.js"></script>
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
					<input type="hidden" value="1" id="count_ingredients_input">
					<hr>
					<div id="ingredients">
						<div class="ingredient" id="div_ingredient_1">
							<input class="ingredient_input" id="ingredient_input_1" type="text" name="ingredients_input" 
							value="Add ingredient" onclick="deleteHintValueIngredient(ingredient_input_1)" onblur="setHintValueIngredient(ingredient_input_1)"><input 
							class="quantity_input" id="quantity_input_1" type="text" name="quantity_input" value="Quantity"><input 
							class="unit_input" id="unit_input_1" value="Units" name="units_input" list="suggestions"><button 
							class="delete_button" id="delete_button_1"  onclick="">-</button>
						</div>		
					</div>
					<button id=add_button onclick="add_ingredient()">+</button>	
					<datalist id="suggestions">
						 <option value="Chrome">
  						 <option value="Firefox">
					</datalist>
				</div>
				<div class="images_div">
					<div class="preview_div">
						<img id="previewHolder" alt="Uploaded Image Preview Holder" width="250px" height="250px"/>
					</div>
					<div class="choose_div">
						<label>Primary image</label>
						<input id="primary_img" type="file" accept="image/*" data-errormsg="PhotoUploadErrorMsg" onchange="readURL()">
						<label>Image</label>
						<input id="secondary_img_1" type="file" accept="image/*" data-errormsg="PhotoUploadErrorMsg" onchange="readURL()">
						<label>Image</label>
						<input id="secondary_img_2" type="file" accept="image/*" data-errormsg="PhotoUploadErrorMsg" onchange="readURL()">
						<label>Image</label>
						<input id="secondary_img_3" type="file" accept="image/*" data-errormsg="PhotoUploadErrorMsg" onchange="readURL()">
						<label>Image</label>
						<input id="secondary_img_4" type="file" accept="image/*" data-errormsg="PhotoUploadErrorMsg" onchange="readURL()">
					</div>
				</div>
			</div>
			
			<div class="description_div">
				<textarea id="description_input" cols="40" rows="5"> Add Description</textarea>
			</div>
			
			<div class="last_buttons_div">
				<button>Review</button>
				<button>Create recipe</button>
			</div>
		</div>
	</div>
</body>
</html>