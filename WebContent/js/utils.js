function getListIngredients() {
	var xhttp = new XMLHttpRequest();
	var action = "get_ingredients_list";
	/*var formdata = new FormData();

	formdata.append("action", action);*/

	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var response = JSON.parse(this.responseText);
			var suggestionsElement = document.getElementById("suggestions");
			
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