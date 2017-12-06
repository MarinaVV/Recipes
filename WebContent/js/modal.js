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
