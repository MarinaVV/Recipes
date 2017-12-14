window.onclick = function(event) {
	if (event.target == document.getElementById('myModal')) {
		closeModal();
	}
}

function closeModal() {
	clearModalData();
	document.getElementById('myModal').style.display = "none";
}

function openModal(recipeIdInput, descriptionInput, dateInput, userNameInput,
		titleLabel, categoryLabel, resultDiv) {

	getSecondaryImages_Ingredients(recipeIdInput.value, descriptionInput,
			dateInput, userNameInput, titleLabel, categoryLabel, resultDiv);

	document.getElementById('myModal').style.display = "block";
}

function getSecondaryImages_Ingredients(recipeId, descriptionInput, dateInput,
		userNameInput, titleLabel, categoryLabel, resultDiv) {
	var xhttp = new XMLHttpRequest();
	var action = "get_secondary_images_ingredients";
	var formdata = new FormData();

	formdata.append("action", action);
	formdata.append("recipe_id", recipeId);

	xhttp.onreadystatechange = function() {
		if (this.readyState === 4 && this.status === 200) {
			var response = JSON.parse(this.responseText);

			displayModalData(descriptionInput, dateInput, userNameInput,
					titleLabel, categoryLabel, resultDiv, response, recipeId);
		}
	};

	xhttp.open("POST", "RecipeControlServlet", true);
	xhttp.send(formdata);
}

function displayModalData(descriptionInput, dateInput, userNameInput,
		titleLabel, categoryLabel, resultDiv, secondaryImagesIngredients, recipeId) {

	document.getElementById('modal_title').innerHTML = titleLabel.innerHTML;
	document.getElementById('modal_category').innerHTML = categoryLabel.innerHTML;
	document.getElementById('modal_description_ta').value = descriptionInput.value;
	document.getElementById('modal_hidden_recipeId').value = recipeId;

	document.getElementById("modal_previwe_image").style.backgroundImage = resultDiv.style.backgroundImage;
	document.getElementById("primary_img").style.backgroundImage = resultDiv.style.backgroundImage;

	var secondaryImages = secondaryImagesIngredients.images;
	var recipe_ingredients = secondaryImagesIngredients.recipe_ingredients;

	for (var i = 0; i < secondaryImages.length; i++) {
		var imgSRC = "data:image/png;base64," + secondaryImages[i].image;
		document.getElementById("secondery_img_" + (i + 1)).style.backgroundImage = "url(' "
				+ imgSRC + "')";
	}

	for (var i = 0; i < recipe_ingredients.length; i++) {
		createRecipeIngredientRow(recipe_ingredients[i]);
	}

}

function createRecipeIngredientRow(recipe_ingredient) {

	var recipeIngredientDiv = document.createElement("div");
	recipeIngredientDiv.setAttribute("name", "modal_recipe_ingredient_row");

	var recipeIngredientLabel = document.createElement("label");
	recipeIngredientLabel.innerHTML = recipe_ingredient.ingredient.name + " "
			+ recipe_ingredient.quantity + " " + recipe_ingredient.units;

	recipeIngredientDiv.appendChild(recipeIngredientLabel);

	document.getElementById("modal_recipe_ingredients").appendChild(
			recipeIngredientDiv);

}

function clearModalData() {
	document.getElementById('modal_title').innerHTML = "";
	document.getElementById('modal_category').innerHTML = "";
	document.getElementById('modal_description_ta').value = "";

	document.getElementById("modal_previwe_image").style.backgroundImage = "";
	document.getElementById("primary_img").style.backgroundImage = "";

	for (var i = 0; i < 3; i++) {
		document.getElementById("secondery_img_" + (i + 1)).style.backgroundImage = "";
	}

	var recipe_ingredients = document
			.getElementsByName("modal_recipe_ingredient_row");
	var modal_recipe_ingredients = document
			.getElementById("modal_recipe_ingredients");
	var recipe_ingredients_length = recipe_ingredients.length;

	for (var i = 0; i < recipe_ingredients_length; i++) {
		modal_recipe_ingredients.removeChild(recipe_ingredients[0]);
	}
}

function previewImage(element) {
	if (element.style.backgroundImage != ""
			&& element.style.backgroundImage != null
			&& element.style.backgroundImage != undefined) {
		document.getElementById("modal_previwe_image").style.backgroundImage = element.style.backgroundImage;
	}

}

function addRecipeToFavorites(recipeIdInput){
	var xhttp = new XMLHttpRequest();
	var action = "add_recipe_favorites";
	var formdata = new FormData();

	
	formdata.append("action", action);
	formdata.append("recipe_id", recipeIdInput.value);
	formdata.append("username", sessionStorage.getItem("uname"));

	xhttp.onreadystatechange = function() {
		if (this.readyState === 4 && this.status === 200) {
			var response = this.responseText;
			if(response == "OK"){
				alert("Added to favorites");
			}else{
				alert("ERROR");
			}
			
		}
	};

	xhttp.open("POST", "RecipeControlServlet", true);
	xhttp.send(formdata);
}

function createBaseModal(){
	
	//get the modalDiv from html
	var myModalDiv = document.getElementById("myModal");
	
	//create div containing modal content
	var modalContentDiv = document.createElement("div");
	modalContentDiv.setAttribute("class", "modal-content");
	
	//create div containing header content
	var modalHeaderDiv = document.createElement("div");
	modalHeaderDiv.setAttribute("class", "modal-header");
	
	//create close X
	var closeSpan = document.createElement("span");
	closeSpan.setAttribute("class", "close");
	closeSpan.setAttribute("onclick", "closeModal()");
	closeSpan.innerHTML = "&times";
	
	//attach close button to header
	modalHeaderDiv.appendChild(closeSpan);
	
	//create hidden input for recipeId
	var recipeIdHiddenInput = document.createElement("input");
	recipeIdHiddenInput.setAttribute("id", "modal_hidden_recipeId");
	recipeIdHiddenInput.setAttribute("class", "hidden_inputs");
	recipeIdHiddenInput.setAttribute("type", "hidden");
	
	//attach hidden input recipe Id to header
	modalHeaderDiv.appendChild(recipeIdHiddenInput);
	
	//create div containing recipe Title label
	var titleDiv=document.createElement("div");
	titleDiv.setAttribute("class", "modal_title_div");
	
	//create label containing recipe Title
	var titleLabel = document.createElement("label");
	titleLabel.setAttribute("id", "modal_title");
	
	//attach title label to title div
	titleDiv.appendChild(titleLabel);
	//attach title div to header div
	modalHeaderDiv.appendChild(titleDiv);
	
	//create category div containing recipe category label
	var categoryDiv = document.createElement("div");
	categoryDiv.setAttribute("class", "modal_category_div")
	
	//create category label containing recipe category
	var categoryLabel = document.createElement("label");
	categoryLabel.setAttribute("id", "modal_category");
	
	//attach category label to category div
	categoryDiv.appendChild(categoryLabel);
	//attach category fiv to header div
	modalHeaderDiv.appendChild(categoryDiv);
	
	//attach modal header to modal content div
	modalContentDiv.appendChild(modalHeaderDiv);
	
	//create BODY div containing body content
	var modalBodyDiv = document.createElement("div");
	
	//create image ingredients div containing iamges and ingredients
	var imageIngredientsDiv = document.createElement("div");
	
	//create image part div containing images
	var imagePartDiv = document.createElement("div");
	imagePartDiv.setAttribute("class", "modal_image_part");
	
	//create div for image preview
	var previewImageDiv = document.createElement("div");
	previewImageDiv.setAttribute("id", "modal_previwe_image");
	previewImageDiv.setAttribute("class", "modal_previwe_image");
	
	//attach previewImage div to image part div
	imagePartDiv.appendChild(previewImageDiv);
	
	//create div containing all the small images
	var allImagesDiv = document.createElement("div");
	allImagesDiv.setAttribute("class", "modal_all_images");
	
	var primaryImgDiv = document.createElement("div");
	primaryImgDiv.setAttribute("id", "primary_img");
	primaryImgDiv.setAttribute("onclick", "previewImage(primary_img)");
	
	allImagesDiv.appendChild(primaryImgDiv);
	
	var secondaryImgDiv1 = document.createElement("div");
	secondaryImgDiv1.setAttribute("id", "secondery_img_1");
	secondaryImgDiv1.setAttribute("onclick", "previewImage(secondery_img_1)");

	allImagesDiv.appendChild(secondaryImgDiv1);
	
	var secondaryImgDiv2 = document.createElement("div");
	secondaryImgDiv2.setAttribute("id", "secondery_img_2");
	secondaryImgDiv2.setAttribute("onclick", "previewImage(secondery_img_2)");
	
	allImagesDiv.appendChild(secondaryImgDiv2);
	
	var secondaryImgDiv3 = document.createElement("div");
	secondaryImgDiv3.setAttribute("id", "secondery_img_3");
	secondaryImgDiv3.setAttribute("onclick", "previewImage(secondery_img_3)");
	
	allImagesDiv.appendChild(secondaryImgDiv3);
	
	var secondaryImgDiv4 = document.createElement("div");
	secondaryImgDiv4.setAttribute("id", "secondery_img_4");
	secondaryImgDiv4.setAttribute("onclick", "previewImage(secondery_img_4)");
	
	allImagesDiv.appendChild(secondaryImgDiv4);
	
	//attach all images div to images part div
	imagePartDiv.appendChild(allImagesDiv);
	
	//attach images part div to image ingredients div
	imageIngredientsDiv.appendChild(imagePartDiv);
	
	var ingredientsPartDiv = document.createElement("div");
	ingredientsPartDiv.setAttribute("class", "modal_ingredients_part");
	
	//create div wich will be used to add recipe ingredients rows
	var recipeIngredientsDiv = document.createElement("div");
	recipeIngredientsDiv.setAttribute("id", "modal_recipe_ingredients");
	
	ingredientsPartDiv.appendChild(recipeIngredientsDiv);
	imageIngredientsDiv.appendChild(ingredientsPartDiv);
	modalBodyDiv.appendChild(imageIngredientsDiv);
	
	var descriptionDiv = document.createElement("div");
	descriptionDiv.setAttribute("class", "modal_description");
	
	var descriptionTextArea = document.createElement("textarea");
	descriptionTextArea.setAttribute("id", "modal_description_ta");
	descriptionTextArea.setAttribute("rows", "40");
	descriptionTextArea.setAttribute("cols", "5");
	descriptionTextArea.setAttribute("readonly", "");
	
	descriptionDiv.appendChild(descriptionTextArea);
	modalBodyDiv.appendChild(descriptionDiv);
	
	//attach modal body to modal content div
	modalContentDiv.appendChild(modalBodyDiv);
	
	myModalDiv.appendChild(modalContentDiv);
}
