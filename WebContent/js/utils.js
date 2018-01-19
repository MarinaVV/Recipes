function getListIngredients() {
	var xhttp = new XMLHttpRequest();
	var action = "get_ingredients_list";
	/*var formdata = new FormData();

	formdata.append("action", action);*/

	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var response = JSON.parse(this.responseText);
			var suggestionsElement = document.getElementById("suggestionsIngredients");
			
			for(var index=0;index<response.length;index++){
				var option = document.createElement("option");
				option.setAttribute("value", response[index]);
				option.setAttribute("name", "options");
				
				suggestionsElement.appendChild(option);
			}
			
		}
	};

	xhttp.open("GET", "RecipeControlServlet?action="+action, true);
	xhttp.send();
}

function createCategoryBar(){
	var categoryBarDiv = document.getElementById("category_bar");
	
	var hiddenResultInput = document.createElement("input");
	hiddenResultInput.setAttribute("id", "hidden_result_input");
	hiddenResultInput.setAttribute("type", "hidden");
	
	categoryBarDiv.appendChild(hiddenResultInput);
	
	var allLabel = document.createElement("label");
	allLabel.setAttribute("class", "category");
	allLabel.innerHTML="All";
	
	categoryBarDiv.appendChild(allLabel);
	
	var breakfastLabel = document.createElement("label");
	breakfastLabel.setAttribute("class", "category");
	breakfastLabel.innerHTML="Breakfast";
	
	categoryBarDiv.appendChild(breakfastLabel);
	
	var lunchLabel = document.createElement("label");
	lunchLabel.setAttribute("class", "category");
	lunchLabel.innerHTML="Lunch";
	
	categoryBarDiv.appendChild(lunchLabel);
	
	var dinnerLabel = document.createElement("label");
	dinnerLabel.setAttribute("class", "category");
	dinnerLabel.innerHTML="Dinner";
	
	categoryBarDiv.appendChild(dinnerLabel);
	
	var snackLabel = document.createElement("label");
	snackLabel.setAttribute("class", "category");
	snackLabel.innerHTML="Snack";
	
	categoryBarDiv.appendChild(snackLabel);
}


//Add ingredient in create-recipe and account modalUpdate
function add_ingredient_create_update(ingredient,quantity,unit) {
	var countIngredients = document.getElementById("count_ingredients_input").value;
	countIngredients++;
	var countIngredients = document.getElementById("count_ingredients_input").value = countIngredients;

	var ingredientDiv = document.createElement("div");
	ingredientDiv.setAttribute("class", "ingredient")
	ingredientDiv.setAttribute("id", "div_ingredient_" + countIngredients);

	var ingredientInput = document.createElement("input");
	ingredientInput.setAttribute("type", "text");
	ingredientInput.setAttribute("id", "input_ingredient_" + countIngredients);
	ingredientInput.setAttribute("name", "ingredient_input");
	ingredientInput.setAttribute("value", ingredient);
	ingredientInput.setAttribute("class", "ingredient_input");
	ingredientInput.setAttribute("onclick", "deleteHintValueIngredient("
			+ "input_ingredient_" + countIngredients + ")");
	ingredientInput.setAttribute("onblur", "setHintValueIngredient("
			+ "input_ingredient_" + countIngredients + ")");
	ingredientInput.setAttribute("list", "suggestionsIngredients");

	var quantityInput = document.createElement("input");
	quantityInput.setAttribute("type", "text");
	quantityInput.setAttribute("id", "quantity_input_" + countIngredients);
	quantityInput.setAttribute("name", "quantity_input");
	quantityInput.setAttribute("value", quantity);
	quantityInput.setAttribute("class", "quantity_input");
	quantityInput.setAttribute("onclick", "deleteHintValueQuantity("
			+ "quantity_input_" + countIngredients + ")");
	quantityInput.setAttribute("onblur", "setHintValueQuantity("
			+ "quantity_input_" + countIngredients + ")");

	var unitsInput = document.createElement("input");
	unitsInput.setAttribute("type", "text");
	unitsInput.setAttribute("id", "unit_input_" + countIngredients);
	unitsInput.setAttribute("name", "units_input");
	unitsInput.setAttribute("value", unit);
	unitsInput.setAttribute("class", "unit_input");
	unitsInput.setAttribute("onclick", "deleteHintValueUnits(" + "unit_input_"
			+ countIngredients + ")");
	unitsInput.setAttribute("onblur", "setHintValueUnits(" + "unit_input_"
			+ countIngredients + ")");
	unitsInput.setAttribute("list", "suggestionsUnits");

	var deleteButton = document.createElement("button");
	deleteButton.setAttribute("id", "delete_button_" + countIngredients);
	deleteButton.setAttribute("onclick", "delete_ingredient(div_ingredient_"
			+ countIngredients + ")");
	deleteButton.innerHTML = "-";
	deleteButton.setAttribute("class", "delete_button");
	
	var hiddenIngredientInput = document.createElement("input");
	hiddenIngredientInput.setAttribute("type", "hidden");
	hiddenIngredientInput.setAttribute("id", "hidden_input_ingredient_" + countIngredients);
	hiddenIngredientInput.setAttribute("name", "hidden_ingredient_input");
	hiddenIngredientInput.setAttribute("value", ingredient);
	hiddenIngredientInput.setAttribute("class", "hidden");

	ingredientDiv.appendChild(ingredientInput);
	ingredientDiv.appendChild(quantityInput);
	ingredientDiv.appendChild(unitsInput);
	ingredientDiv.appendChild(deleteButton);
	ingredientDiv.appendChild(hiddenIngredientInput);

	document.getElementById("ingredients").appendChild(ingredientDiv);
}


function previewImg(input, imgId) {

	if (input.files && input.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			// get loaded data and render thumbnail.
			imgId.src = e.target.result;
		};

		// read the image file as a data URL.
		reader.readAsDataURL(input.files[0]);
	} else {
		imgId.src = "";
	}
}


function deleteHintValueIngredient(element) {
	if (element.value == "Add ingredient") {
		element.setAttribute("value", "")
	}
}

function setHintValueIngredient(element) {
	if (element.value.trim() == "") {
		element.setAttribute("value", "Add ingredient");
	}
}

function deleteHintValueQuantity(element) {
	if (element.value == "Quantity") {
		element.setAttribute("value", "")
	}
}

function setHintValueQuantity(element) {
	if (element.value.trim() == "") {
		element.setAttribute("value", "Quantity");
	}
}

function deleteHintValueUnits(element) {
	if (element.value == "Units") {
		element.setAttribute("value", "")
	}
}

function setHintValueUnits(element) {
	if (element.value.trim() == "") {
		element.setAttribute("value", "Units");
	}
}

function deleteHintValueTitle(element) {
	if (element.value == "Add Title") {
		element.setAttribute("value", "")
	}
}

function setHintValueTitle(element) {
	if (element.value.trim() == "") {
		element.setAttribute("value", "Add Title");
	}
}

function deleteHintValueDescription(element) {
	if (element.innerHTML == "Add Description") {
		element.innerHTML = "";
	}
}

function setHintValueDescription(element) {
	if (element.innerHTML == "") {
		element.innerHTML == "Add Description";
	}
}
