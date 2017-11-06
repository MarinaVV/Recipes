function add_ingredient() {
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
	ingredientInput.setAttribute("value", "Add ingredient");
	ingredientInput.setAttribute("class", "ingredient_input");
	ingredientInput.setAttribute("onclick", "deleteHintValueIngredient("
			+ "input_ingredient_" + countIngredients + ")");
	ingredientInput.setAttribute("onblur", "setHintValueIngredient("
			+ "input_ingredient_" + countIngredients + ")");

	var quantityInput = document.createElement("input");
	quantityInput.setAttribute("type", "text");
	quantityInput.setAttribute("id", "quantity_input_" + countIngredients);
	quantityInput.setAttribute("name", "quantity_input");
	quantityInput.setAttribute("value", "Quantity");
	quantityInput.setAttribute("class", "quantity_input");
	quantityInput.setAttribute("onclick", "deleteHintValueQuantity("
			+ "quantity_input_" + countIngredients + ")");
	quantityInput.setAttribute("onblur", "setHintValueQuantity("
			+ "quantity_input_" + countIngredients + ")");

	var unitsInput = document.createElement("input");
	unitsInput.setAttribute("type", "text");
	unitsInput.setAttribute("id", "unit_input_" + countIngredients);
	unitsInput.setAttribute("name", "units_input");
	unitsInput.setAttribute("value", "Units");
	unitsInput.setAttribute("class", "unit_input");
	unitsInput.setAttribute("onclick", "deleteHintValueUnits(" + "unit_input_"
			+ countIngredients + ")");
	unitsInput.setAttribute("onblur", "setHintValueUnits(" + "unit_input_"
			+ countIngredients + ")");
	unitsInput.setAttribute("list", "suggestions");

	var deleteButton = document.createElement("button");
	deleteButton.setAttribute("id", "delete_button_" + countIngredients);
	deleteButton.setAttribute("onclick", "delete_ingredient(div_ingredient_"
			+ countIngredients + ")");
	deleteButton.innerHTML = "-";
	deleteButton.setAttribute("class", "delete_button");

	ingredientDiv.appendChild(ingredientInput);
	ingredientDiv.appendChild(quantityInput);
	ingredientDiv.appendChild(unitsInput);
	ingredientDiv.appendChild(deleteButton);

	document.getElementById("ingredients").appendChild(ingredientDiv);
}

function delete_ingredient(divId) {

	document.getElementById("ingredients").removeChild(divId);
}

function previewImg(input) {

	if (input.files && input.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			// get loaded data and render thumbnail.
			document.getElementById("previewHolder").src = e.target.result;
		};

		// read the image file as a data URL.
		reader.readAsDataURL(input.files[0]);
	} else {
		document.getElementById("previewHolder").src = "";
	}
}

function saveUnknownIngredients(){
	var optionElements = document.getElementsByName("options");
	var allIngredientsList=[];
	
	for(var index=0;index<optionElements.length;index++){
		allIngredientsList.push(optionElements[index]);
	}
	
	var 
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
