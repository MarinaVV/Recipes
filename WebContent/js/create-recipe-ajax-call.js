function createRecipe(){
	var title = document.getElementById("title_input").value;
	var category=document.getElementById("category_select").value;
	var description=document.getElementById("description_input").innerHTML;
	
	var primary_img = document.getElementById("primary_img").files[0];
	var secondary_img_1 = document.getElementById("secondary_img_1").files[0];
	var secondary_img_2 = document.getElementById("secondary_img_2").files[0];
	var secondary_img_3 = document.getElementById("secondary_img_3").files[0];
	var secondary_img_4 = document.getElementById("secondary_img_4").files[0];
	
	var listIngredients=[{}];
	var listIngredientInputs=document.getElementsByName("ingredient_input");
	var listQunatitiesInputs=document.getElementsByName("quantity_input");
	var listUnitsInputs=document.getElementsByName("units_input");
	for(var index=0;index<listIngredientInputs.length - 1;index++){
		if(listIngredientInputs[index].value != "Add ingredient"){
			var recipe_ingredient = {};
			recipe_ingredient.ingredient=listIngredientInputs[index].value;
			recipe_ingredient.quantity = listQunatitiesInputs[index].value;
			recipe_ingredient.units = listUnitsInputs[index].value;
			listIngredients.push(recipe_ingredient);
		}
	}	
	
}