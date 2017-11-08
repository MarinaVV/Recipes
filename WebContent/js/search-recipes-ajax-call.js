function search_recipes() {

	var username = document.getElementById("username").value;
	var recipeName = document.getElementById("recipe_name").value;

	var ingredients = document.getElementsByName("ingredient_input");

}

function search_recipe_U() {

	var username = document.getElementById("username").value;

	var xhttp = new XMLHttpRequest();
	var action = "search_recipe_username";
	var formdata = new FormData();

	formdata.append("action", action);
	formdata.append("username", username);

	xhttp.onreadystatechange = function() {
		var response = JSON.parse(this.responseText);
		
		var img=response[0].images[0].image;
		
		
		var src = "data:image/png;base64," + img;
		
		document.getElementById("left_result").style.backgroundImage = "url(' " + src + "')";
		
	};

	xhttp.open("POST", "RecipeControlServlet", true);
	xhttp.send(formdata);
}