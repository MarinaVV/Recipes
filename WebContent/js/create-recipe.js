function delete_ingredient(divId) {

	document.getElementById("ingredients").removeChild(divId);
}

function setUnitsSuggestions() {
	var unitsDatalist = document.getElementById("suggestionsUnits");

	var unitsList = [ "kg", "g", "mg", "tbsp.", "tsp.", "ml", "l", "pint" ];

	for (var index = 0; index < unitsList.length; index++) {
		var option = document.createElement("option");
		option.setAttribute("value", unitsList[index]);

		unitsDatalist.appendChild(option);
	}
}

function checkFieldValues() {
	elementsMap = getElementsValues();

	var title = elementsMap.title;
	var ingredientsList = elementsMap.recipeIngredientsList;
	var description = elementsMap.description;

	if (title == "Add Title" || title.length <= 0) {
		alert("No title");
		return false;
	} else if (description == "Add Description" || description.length <= 0) {
		alert("No description");
		return false;
	} else {
		recipe_ingredients = elementsMap.recipeIngredientsList;
		for (var index = 0; index < recipe_ingredients.length; index++) {
			if (recipe_ingredients[index].ingredient.length <= 0) {
				alert("Add ingredient name");
				return false
			} else if (recipe_ingredients[index].quantity.length <= 0) {
				alert("Add quantity");
				return false
			} else if (isNaN(recipe_ingredients[index].quantity)) {
				alert("Quantity must be a number");
				return false
			} else if (recipe_ingredients[index].units.length <= 0) {
				alert("Add unit");
				return false
			}
		}

		return true;
	}

}

function getElementsValues() {

	var elementsMap = {};

	elementsMap.title = document.getElementById("title_input").value;
	elementsMap.category = document.getElementById("category_select").value;
	elementsMap.description = document.getElementById("description_input").value;

	elementsMap.primary_img = document.getElementById("primary_img_input").files[0];
	var secondary_img_list = [];
	secondary_img_list
			.push(document.getElementById("secondary_img_1_input").files[0]);
	secondary_img_list
			.push(document.getElementById("secondary_img_2_input").files[0]);
	secondary_img_list
			.push(document.getElementById("secondary_img_3_input").files[0]);
	secondary_img_list
			.push(document.getElementById("secondary_img_4_input").files[0]);
	elementsMap.secondary_imag_list = secondary_img_list;

	var listRecipeIngredients = [];
	var listIngredientInputs = document.getElementsByName("ingredient_input");
	var listQunatitiesInputs = document.getElementsByName("quantity_input");
	var listUnitsInputs = document.getElementsByName("units_input");
	for (var index = 0; index < listIngredientInputs.length; index++) {
		if (listIngredientInputs[index].value != "Add ingredient") {
			var recipe_ingredient = {};
			recipe_ingredient.ingredient = listIngredientInputs[index].value;
			recipe_ingredient.quantity = listQunatitiesInputs[index].value;
			recipe_ingredient.units = listUnitsInputs[index].value;
			listRecipeIngredients.push(recipe_ingredient);
		}
	}
	elementsMap.recipeIngredientsList = listRecipeIngredients;

	return elementsMap;
}

function reviewModal() {

	if (!checkFieldValues()) {
		return false;
	}

	elementsMap = getElementsValues();

	document.getElementById('modal_title').innerHTML = elementsMap.title;
	document.getElementById('modal_category').innerHTML = elementsMap.category;
	document.getElementById('modal_description_ta').value = elementsMap.description;

	var input = document.getElementById('primary_img_input');
	var img = document.getElementById('modal_previwe_image').childNodes[0];
	if (img.childNodes.length > 0) {
		previewImg(input, img);
	} else {
		img.src = "img/No-image-available.jpg";
	}
	input = document.getElementById('primary_img_input');
	img = document.getElementById('primary_img').childNodes[0];
	if (img.childNodes.length > 0) {
		previewImg(input, img);
	} else {
		img.src = "img/No-image-available.jpg";
	}
	input = document.getElementById('secondary_img_1_input');
	img = document.getElementById('secondery_img_1').childNodes[0];
	if (img.childNodes.length > 0) {
		previewImg(input, img);
	}
	input = document.getElementById('secondary_img_2_input');
	img = document.getElementById('secondery_img_2').childNodes[0];
	if (img.childNodes.length > 0) {
		previewImg(input, img);
	}
	input = document.getElementById('secondary_img_3_input');
	img = document.getElementById('secondery_img_3').childNodes[0];
	if (img.childNodes.length > 0) {
		previewImg(input, img);
	}
	input = document.getElementById('secondary_img_4_input');
	img = document.getElementById('secondery_img_4').childNodes[0];
	if (img.childNodes.length > 0) {
		previewImg(input, img);
	}

	recipe_ingredients = elementsMap.recipeIngredientsList;

	for (var i = 0; i < recipe_ingredients.length; i++) {
		var ingredientName = recipe_ingredients[i].ingredient;
		recipe_ingredients[i].ingredient = {};
		recipe_ingredients[i].ingredient.name = ingredientName;
		createRecipeIngredientRow(recipe_ingredients[i]);
	}

	return true;

}

function unclockImageInput(checkInputId, unlockInputId, buttonId) {
	var file = checkInputId.files[0];
	if (file != undefined && file != null) {
		var fileType = file["type"];
	}
	var validImageTypes = [ "image/gif", "image/jpeg", "image/png" ];
	if (validImageTypes.indexOf(fileType) == -1 || file == undefined
			|| file == null) {
		if (unlockInputId != undefined || unlockInputId != null) {
			unlockInputId.disabled = true;
		}
		buttonId.disabled = true;
	} else {
		if (unlockInputId != undefined || unlockInputId != null) {
			unlockInputId.disabled = false;
		}
		buttonId.disabled = false;
	}

}