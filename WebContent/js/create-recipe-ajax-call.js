function createRecipe() {

	if(!checkFieldValues()){
		return;
	}
	
	elementsMap = getElementsValues();
	var xhttp = new XMLHttpRequest();
	var action = "create_recipe";
	var formdata = new FormData();

	formdata.append("action", action);
	formdata.append("title", elementsMap.title);
	formdata.append("category", elementsMap.category);
	formdata.append("username", sessionStorage.getItem('uname'));

	formdata.append("primary_img", elementsMap.primary_img);
	formdata.append("secondary_img_1", elementsMap.secondary_imag_list[0]);
	formdata.append("secondary_img_2", elementsMap.secondary_imag_list[1]);
	formdata.append("secondary_img_3", elementsMap.secondary_imag_list[2]);
	formdata.append("secondary_img_4", elementsMap.secondary_imag_list[3]);

	formdata.append("recie_ingredients", JSON
			.stringify(elementsMap.recipeIngredientsList));
	formdata.append("description", elementsMap.description);

	var value = formdata.getAll("title");

	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			document.getElementById("loader").hidden = true;
			document.getElementById("outer_container").hidden = false;
			//window.alert(this.responseText);
			
		}
	};
	
	document.getElementById("loader").hidden = false;
	document.getElementById("outer_container").hidden = true;

	xhttp.open("POST", "RecipeControlServlet", true);
	xhttp.send(formdata);

	/*
	 * xhttp.onload = function(e) {
	 * 
	 * if (this.status == 200) {
	 * 
	 * alert(this.responseText);
	 *  }
	 *  };
	 */
}

function saveUnknownIngredients() {
	var optionElements = document.getElementsByName("options");
	var allIngredientsList = [];

	// get all ingredients
	for (var index = 0; index < optionElements.length; index++) {
		allIngredientsList.push(optionElements[index].value);
	}

	var ingredientElements = document.getElementsByName("ingredient_input");
	var ingredientsList = [];

	// get the ingredients which will be inserted
	for (var index = 0; index < ingredientElements.length; index++) {
		ingredientsList.push(ingredientElements[index].value);
	}

	var missingIngredients = [];

	// get the ingredients that are not in the database
	for (var index = 0; index < ingredientsList.length; index++) {

		if (allIngredientsList.indexOf(ingredientsList[index]) == -1 && missingIngredients.indexOf(ingredientsList[index]) == -1) {
			missingIngredients.push(ingredientsList[index]);
		}
	}

	if (missingIngredients.length > 0) {
		var xhttp = new XMLHttpRequest();
		var action = "save_ingredients";
		var formdata = new FormData();

		formdata.append("action", action);
		formdata.append("ingredients", JSON.stringify(missingIngredients));

		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				var suggestionsElement = document.getElementById("suggestionsIngredients");
				
				for(var index=0;index<missingIngredients.length;index++){
					var option = document.createElement("option");
					option.setAttribute("value", missingIngredients[index]);
					option.setAttribute("name", "options");
					
					suggestionsElement.appendChild(option);
				}
			}
		};

		xhttp.open("POST", "RecipeControlServlet", true);
		xhttp.send(formdata);
	}
}

