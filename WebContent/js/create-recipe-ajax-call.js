function createRecipe(){
	var title = document.getElementById("title_input").value;
	var category=document.getElementById("category_select").value;
	var description=document.getElementById("description_input").innerHTML;
	
	var primary_img = document.getElementById("primary_img").files[0];
	var secondary_img_1 = document.getElementById("secondary_img_1").files[0];
	var secondary_img_2 = document.getElementById("secondary_img_2").files[0];
	var secondary_img_3 = document.getElementById("secondary_img_3").files[0];
	var secondary_img_4 = document.getElementById("secondary_img_4").files[0];
	
	var listIngredients=[];
	var listIngredientInputs=document.getElementsByName("ingredient_input");
	for(input in listIngredientInputs){
		listIngredients.push(input.value);
	}
	
	var listIngredients=[];
	var listIngredientInputs=document.getElementsByName("ingredient_input");
	for(input in listIngredientInputs){
		listIngredients.push(input.value);
	}
}