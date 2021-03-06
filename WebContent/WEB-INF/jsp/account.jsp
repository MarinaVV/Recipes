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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/modalUpdate.css"> 

<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/create-recipe.js"></script> --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/nav-bar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/account.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/utils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/search-recipes-ajax-call.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/modal.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/modalUpdate.js"></script>
</head>
<body>
	<script type="text/javascript">
		window.onload = function(){
			getListIngredients();
			setUnitsSuggestions();
		}
	</script>
	<div class="outer_container">
		<p id="p_uname">${uname}</p>
		<script>
			var uname = window.sessionStorage.getItem("uname");
			if(window.sessionStorage.getItem("uname") == null){
				var name = document.getElementById("p_uname").innerHTML;
				window.sessionStorage.setItem("uname", name)
			}else{
				document.getElementById("p_uname").innerHTML=window.sessionStorage.getItem("uname");
			}
		</script>
		
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
		
		<!-- The Update Modal -->
		<!-- main modal classes from modal.css oder from modalUpdate.css -->
		<div id="myModalUpdate" class="modal">
					  <!-- Modal content -->
  			<div class="modal-content">
    			<div class="modal-header">
    				<input id="modalUpdate_hidden_recipe_id" type="hidden" class="hidden">
      				<span class="close" onclick="closeModalUpdate()">&times;</span>
      				
      				<div class="modal_title_div">
      					<input id="modalUpdate_title">
      				</div>
      				<div class="modal_category_div">
      					<select id="modalUpdate_category">
							<option>Breakfast</option>
							<option>Lunch</option>
							<option>Dinner</option>
							<option>Snack</option>
						</select>
      					
      				</div>

    			</div>
    			<div class="modal-body">
    				<div class="modal_image_ingredients">
    					<div class="modal_image_part" disabled>
    						<div class="previewHolder">
    						</div>
    						<div class="images">
    							<div>
									<div>
										<label>Primary image</label>
									</div>
									<input id="modalUpdate_primary_img" type="file" accept="image/*" data-errormsg="PhotoUploadErrorMsg">
									<button onclick="previewImg(primary_img)">Peview	image</button>
									<div>
										<button>Add</button>
										<button>Delete</button>
									</div>
								</div>
								<div>
									<div>
										<label>Image</label>
									</div>
									<input id="modalUpdate_secondary_img_1" type="file" accept="image/*" data-errormsg="PhotoUploadErrorMsg">
									<button onclick="previewImg(secondary_img_1)">Peview	image</button>
									<div>
										<button>Add</button>
										<button>Delete</button>
									</div>
								</div>
								<div>
									<div>
										<label>Image</label>
									</div>
									<input id="modalUpdate_secondary_img_2" type="file" accept="image/*" data-errormsg="PhotoUploadErrorMsg">
									<button onclick="previewImg(secondary_img_2)">Peview	image</button>
									<div>
										<button>Add</button>
										<button>Delete</button>
									</div>
								</div>
								<div>
									<div>
										<label>Image</label>
									</div>
									<input id="modalUpdate_secondary_img_3" type="file" accept="image/*" data-errormsg="PhotoUploadErrorMsg">
									<button onclick="previewImg(secondary_img_3)">Peview	image</button>
									<div>
										<button>Add</button>
										<button>Delete</button>
									</div>
								</div>
								<div>
									<div>
										<label>Image</label>
									</div>
									<input id="modalUpdate_secondary_img_4" type="file" accept="image/*" data-errormsg="PhotoUploadErrorMsg">
									<button onclick="previewImg(secondary_img_4)">Peview image</button>
									<div>
										<button>Add</button>
										<button>Delete</button>
									</div>
								</div>
							</div>
							
    					</div>
    					<div class="modal_ingredients_part">
    						<div class="ingredients_div">
			
								<label>Ingredients</label>
								<input type="hidden" value="0" id="count_ingredients_input">
								<hr>
								<div id="ingredients">
							<!-- 		<div class="ingredient" id="div_ingredient_1">
										<input class="ingredient_input" id="ingredient_input_1" type="text" name="ingredient_input" list="suggestionsIngredients"
										value="Add ingredient" onclick="deleteHintValueIngredient(ingredient_input_1)" onblur="setHintValueIngredient(ingredient_input_1)"><input 
										class="quantity_input" id="quantity_input_1" type="text" name="quantity_input" value="Quantity"
										onclick="deleteHintValueQuantity(quantity_input_1)" onblur="setHintValueQuantity(quantity_input_1)"><input 
										class="unit_input" id="unit_input_1" value="Units" name="units_input" list="suggestionsUnits"
										onclick="deleteHintValueUnits(unit_input_1)" onblur="setHintValueUnits(unit_input_1)"><button 
										class="delete_button" id="delete_button_1"  onclick="">-</button>
										<input class="hidden" id="hidden_ingredient_input_1" type="hidden" name="hidden_ingredient_input">
									</div>		 -->
								</div>
								<button id=add_button onclick="add_ingredient_create_update('Add ingredient','Quantity','Units')">+</button>	
								
							</div>
							<datalist id="suggestionsIngredients"> </datalist>
							<datalist id="suggestionsUnits"> </datalist>
						</div>
    				</div>
      				<div class="modal_description">
      					<textarea id="modalUpdate_description" rows="40" cols="5"></textarea>
      				</div>
      				<button onclick="updateRecipe()">Update</button>
    			</div>

  			</div>
		</div>
		
	</div>
</body>
</html>