function showMyRecipes() {
	createResultArea();

	var username = sessionStorage.uname;
	search_recipe_U(username);
}

function showFavoriteRecipes() {
	var xhttp = new XMLHttpRequest();
	var action = "get_favorite_recipes";
	var formdata = new FormData();

	formdata.append("action", action);
	var username = sessionStorage.uname;
	formdata.append("username", username);

	createResultArea();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {

			document.getElementById("hidden_result_input").value = this.responseText;

			var response = JSON.parse(this.responseText);

			clearSearchResult();
			displaySearchResult(response);

		}
	};

	xhttp.open("POST", "RecipeControlServlet", true);
	xhttp.send(formdata);
}

function createResultArea() {
	var informationDiv = document.getElementById("information_div");

	var categoryBarDiv = document.createElement("div");
	categoryBarDiv.setAttribute("id", "category_bar");
	categoryBarDiv.setAttribute("class", "category_bar");

	if (document.getElementById("scriptCategoryBar") == undefined) {
		var scriptTag = document.createElement("script");
		scriptTag.setAttribute("type", "text/javascript");
		scriptTag.setAttribute("id", "scriptCategoryBar");
		scriptTag.innerHTML = "createCategoryBar()";

		categoryBarDiv.appendChild(scriptTag)

		informationDiv.appendChild(categoryBarDiv);

		var horizontalLine = document.createElement("hr");
		informationDiv.appendChild(horizontalLine);
	}
	var resultsDiv = document.createElement("div");
	resultsDiv.setAttribute("id", "results")

	informationDiv.appendChild(resultsDiv);
}

function deleteRecipe(recipeId) {

	var xhttp = new XMLHttpRequest();
	var action = "delete_recipe_title";
	var formdata = new FormData();

	formdata.append("action", action);
	formdata.append("recipe_id", recipeId.value);

	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var response = this.responseText;
			alert(response);

			var recipes = JSON.parse(document
					.getElementById("hidden_result_input").value);
			for (var i = 0; i < recipes.length; i++) {
				if (recipes[i].id == recipeId.value) {
					recipes.splice(i, 1);
				}
			}

			clearSearchResult();
			displaySearchResult(recipes);
			closeModal();
		}
	};

	xhttp.open("POST", "RecipeControlServlet", true);
	xhttp.send(formdata);
}
