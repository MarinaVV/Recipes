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

function displaySearchResult(listRecipes) {

	var resultRowIndex = 1;
	for (var indexOfList = 0; indexOfList < listRecipes.length; indexOfList++) {
		if (indexOfList % 2 == 0) {
			createResultRow(resultRowIndex);

			var imgBase64 = listRecipes[indexOfList].images[0].image;
			var title = listRecipes[indexOfList].title;

			var imgSRC = "data:image/png;base64," + imgBase64;

			document.getElementById("left_result_" + resultRowIndex).style.backgroundImage = "url(' "
					+ imgSRC + "')";

			document.getElementById("left_label_" + resultRowIndex).innerHTML = title;

		} else {

			var imgBase64 = listRecipes[indexOfList].images[0].image;
			var title = listRecipes[indexOfList].title;

			var imgSRC = "data:image/png;base64," + imgBase64;

			document.getElementById("right_result_" + resultRowIndex).style.backgroundImage = "url(' "
					+ imgSRC + "')";

			document.getElementById("right_label_" + resultRowIndex).innerHTML = title;

			resultRowIndex++;
		}

	}

}

function createResultRow(rowIndex) {
	var result_row_div = document.createElement("div");
	result_row_div.setAttribute("class", "result_row");

	var left_result_div = document.createElement("div");
	left_result_div.setAttribute("class", "left_result");
	left_result_div.setAttribute("id", "left_result_" + rowIndex);

	var left_result_label = document.createElement("label");
	left_result_label.setAttribute("id", "left_label_" + rowIndex)

	left_result_div.appendChild(left_result_label);
	result_row_div.appendChild(left_result_div);

	var right_result_div = document.createElement("div");
	right_result_div.setAttribute("class", "right_result");
	right_result_div.setAttribute("id", "right_result_" + rowIndex);

	var right_result_label = document.createElement("label");
	right_result_label.setAttribute("id", "right_label_" + rowIndex)

	right_result_div.appendChild(right_result_label);
	result_row_div.appendChild(right_result_div);

	var results_div = document.getElementById("results");
	results_div.appendChild(result_row_div);
}
