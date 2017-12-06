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