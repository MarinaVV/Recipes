function add_ingredient(){
	var countIngredients = document.getElementById("count_ingredients_input").value;
	countIngredients++;
	var countIngredients = document.getElementById("count_ingredients_input").value=countIngredients;
	
	var ingredientDiv = document.createElement("div");
	ingredientDiv.setAttribute("class", "ingredient")
	ingredientDiv.setAttribute("id", "div_ingredient_"+countIngredients);
	
	
	var ingredientInput=document.createElement("input");
	ingredientInput.setAttribute("type", "text");
	ingredientInput.setAttribute("id", "input_ingredient_" + countIngredients);
	ingredientInput.setAttribute("name", "ingredient_input");
	
	
	var deleteButton=document.createElement("button");
	deleteButton.setAttribute("id", "delete_button_"+countIngredients);
	deleteButton.setAttribute("onclick", "delete_ingredient(div_ingredient_"+countIngredients+")");
	deleteButton.innerHTML="-";
	
	ingredientDiv.appendChild(ingredientInput);
	ingredientDiv.appendChild(deleteButton);
	
	document.getElementById("ingredients").appendChild(ingredientDiv);
}

function delete_ingredient(divId){
	
	document.getElementById("ingredients").removeChild(divId);
}

