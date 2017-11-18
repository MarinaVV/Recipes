function search_recipes() {

	var username = document.getElementById("username").value;
	var recipeName = document.getElementById("recipe_name").value;

	var ingredients = document.getElementsByName("ingredient_input");

}

function search_recipe_U() {

	// ToDo set as parameter
	var username = document.getElementById("username").value;

	var xhttp = new XMLHttpRequest();
	var action = "search_recipe_username";
	var formdata = new FormData();

	formdata.append("action", action);
	formdata.append("username", username);

	xhttp.onreadystatechange = function() {
		var response = JSON.parse(this.responseText);

		displaySearchResult(response);
	};

	xhttp.open("POST", "RecipeControlServlet", true);
	xhttp.send(formdata);
}

function search_recipe_I_L() {
	// ToDo set as parameter
	var ingredientInputElements = document
			.getElementsByName("ingredient_input");
	var ingredientList = new Array();

	for (var index = 0; index < ingredientInputElements.length; index++) {
		ingredientList.push(ingredientInputElements[index].value);
	}

	var xhttp = new XMLHttpRequest();
	var action = "search_recipe_ingredients_list";
	var formdata = new FormData();

	formdata.append("action", action);
	formdata.append("ingredients_list", ingredientList);

	xhttp.onreadystatechange = function() {
		var response = JSON.parse(this.responseText);

		displaySearchResult(response);
	};

	xhttp.open("POST", "RecipeControlServlet", true);
	xhttp.send(formdata);
}

function displaySearchResult(listRecipes) {

	var resultRowIndex = 1;
	for (var indexOfList = 0; indexOfList < listRecipes.length; indexOfList++) {
		var imgBase64 = listRecipes[indexOfList].images[0].image;
		var title = listRecipes[indexOfList].title;
		var category = listRecipes[indexOfList].category;

		var description = listRecipes[indexOfList].description;
		var recipeId = listRecipes[indexOfList].id;
		var creatingUser = listRecipes[indexOfList].creatingUser.username;
		var date = listRecipes[indexOfList].date;

		var imgSRC = "data:image/png;base64," + imgBase64;

		if (indexOfList % 2 == 0) {
			createResultRow(resultRowIndex);

			document.getElementById("left_result_" + resultRowIndex).style.backgroundImage = "url(' "
					+ imgSRC + "')";

			document.getElementById("left_label_" + resultRowIndex).innerHTML = title;

			document.getElementById("left_category_" + resultRowIndex).innerHTML = category;
			// //
			document.getElementById("left_hidden_recipeId_" + resultRowIndex).value = recipeId;

			document
					.getElementById("left_hidden_description_" + resultRowIndex).value = description;

			document.getElementById("left_hidden_date_" + resultRowIndex).value = date;

			document.getElementById("left_hidden_userName_" + resultRowIndex).value = creatingUser;

		} else {

			document.getElementById("right_result_" + resultRowIndex).style.backgroundImage = "url(' "
					+ imgSRC + "')";

			document.getElementById("right_label_" + resultRowIndex).innerHTML = title;

			document.getElementById("right_category_" + resultRowIndex).innerHTML = category;

			// //
			document.getElementById("right_hidden_recipeId_" + resultRowIndex).value = recipeId;

			document.getElementById("right_hidden_description_"
					+ resultRowIndex).value = description;

			document.getElementById("right_hidden_date_" + resultRowIndex).value = date;

			document.getElementById("right_hidden_userName_" + resultRowIndex).value = creatingUser;

			resultRowIndex++;
		}

	}

}

function createResultRow(rowIndex) {
	var result_row_div = document.createElement("div");
	result_row_div.setAttribute("class", "result_row");

	var left_result_div = document.createElement("div");
	left_result_div.setAttribute("class", "left_result");
	var left_result_div_id = "left_result_" + rowIndex;
	left_result_div.setAttribute("id", left_result_div_id);

	var left_result_label = document.createElement("label");
	var left_title_id = "left_label_" + rowIndex;
	left_result_label.setAttribute("id", left_title_id)
	left_result_label.setAttribute("class", "result_title_label")

	var left_category_label = document.createElement("label");
	var left_category_id = "left_category_" + rowIndex;
	left_category_label.setAttribute("id", left_category_id);
	left_category_label.setAttribute("class", "result_category_label");

	var left_hidden_recipeId = document.createElement("input");
	var left_hidden_recipeId_id = "left_hidden_recipeId_" + rowIndex;
	left_hidden_recipeId.setAttribute("id", left_hidden_recipeId_id);
	left_hidden_recipeId.setAttribute("class", "hidden_inputs");

	var left_hidden_description = document.createElement("input");
	var left_hidden_descrpt_id = "left_hidden_description_" + rowIndex;
	left_hidden_description.setAttribute("id", left_hidden_descrpt_id);
	left_hidden_description.setAttribute("class", "hidden_inputs");

	var left_hidden_date = document.createElement("input");
	var left_hidden_date_id = "left_hidden_date_" + rowIndex;
	left_hidden_date.setAttribute("id", left_hidden_date_id);
	left_hidden_date.setAttribute("class", "hidden_inputs");

	var left_hidden_userName = document.createElement("input");
	var left_hidden_userName_id = "left_hidden_userName_" + rowIndex;
	left_hidden_userName.setAttribute("id", left_hidden_userName_id);
	left_hidden_userName.setAttribute("class", "hidden_inputs");

	left_result_div.appendChild(left_hidden_recipeId);
	left_result_div.appendChild(left_hidden_description);
	left_result_div.appendChild(left_hidden_date);
	left_result_div.appendChild(left_hidden_userName);
	left_result_div.appendChild(left_result_label);
	left_result_div.appendChild(left_category_label);
	left_result_div.setAttribute("onclick", "openModal("
			+ left_hidden_recipeId_id + "," + left_hidden_descrpt_id + ","
			+ left_hidden_date_id + "," + left_hidden_userName_id + ","
			+ left_title_id + "," + left_category_id + "," + left_result_div_id
			+ ")");
	result_row_div.appendChild(left_result_div);

	var right_result_div = document.createElement("div");
	right_result_div.setAttribute("class", "right_result");
	var right_result_div_id = "right_result_" + rowIndex;
	right_result_div.setAttribute("id", right_result_div_id);

	var right_result_label = document.createElement("label");
	var right_title_id = "right_label_" + rowIndex;
	right_result_label.setAttribute("id", right_title_id)
	right_result_label.setAttribute("class", "result_title_label")

	var right_category_label = document.createElement("label");
	var right_category_id = "right_category_" + rowIndex;
	right_category_label.setAttribute("id", right_category_id);
	right_category_label.setAttribute("class", "result_category_label");

	var right_hidden_recipeId = document.createElement("input");
	var right_hidden_recipeId_id = "right_hidden_recipeId_" + rowIndex;
	right_hidden_recipeId.setAttribute("id", right_hidden_recipeId_id);
	right_hidden_recipeId.setAttribute("class", "hidden_inputs");

	var right_hidden_description = document.createElement("input");
	var right_hidden_descript_id = "right_hidden_description_" + rowIndex;
	right_hidden_description.setAttribute("id", right_hidden_descript_id);
	right_hidden_description.setAttribute("class", "hidden_inputs");

	var right_hidden_date = document.createElement("input");
	var right_hidden_date_id = "right_hidden_date_" + rowIndex;
	right_hidden_date.setAttribute("id", right_hidden_date_id);
	right_hidden_date.setAttribute("class", "hidden_inputs");

	var right_hidden_userName = document.createElement("input");
	var right_hidden_userName_id = "right_hidden_userName_" + rowIndex;
	right_hidden_userName.setAttribute("id", right_hidden_userName_id);
	right_hidden_userName.setAttribute("class", "hidden_inputs");

	right_result_div.appendChild(right_hidden_recipeId);
	right_result_div.appendChild(right_hidden_description);
	right_result_div.appendChild(right_hidden_date);
	right_result_div.appendChild(right_hidden_userName);
	right_result_div.appendChild(right_result_label);
	right_result_div.appendChild(right_category_label);
	right_result_div.setAttribute("onclick", "openModal("
			+ right_hidden_recipeId_id + "," + right_hidden_descript_id + ","
			+ right_hidden_date_id + "," + right_hidden_userName_id + ","
			+ right_title_id + "," + right_category_id + ","
			+ right_result_div_id + ")");
	result_row_div.appendChild(right_result_div);

	var results_div = document.getElementById("results");
	results_div.appendChild(result_row_div);
}
