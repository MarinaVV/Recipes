/*window.onclick = function(event) {
	if (event.target == document.getElementById('myModalUpdate')) {
		closeModalUpdate();
	}
}*/

function closeModalUpdate() {
	clearModalData();
	document.getElementById('myModalUpdate').style.display = "none";
}

function openModalUpdate(recipeIdInput, descriptionInput, dateInput, userNameInput,
		titleLabel, categoryLabel, resultDiv) {

	
	
	document.getElementById("modalUpdate_title").value = titleLabel.innerHTML;
	
	category = categoryLabel.innerHTML;
	
	switch(category){
		case "Breakfast": 
			document.getElementById("modalUpdate_category").options[0].selected = true;
			break;
		case "Lunch": 
			document.getElementById("modalUpdate_category").options[1].selected = true;
			break;
		case "Dinner": 
			document.getElementById("modalUpdate_category").options[2].selected = true;
			break;
		case "Snack": 
			document.getElementById("modalUpdate_category").options[3].selected = true;
			break;
			
	}
		
	
	document.getElementById("modalUpdate_description").value = descriptionInput.value;
	document.getElementById("modalUpdate_hidden_recipe_id").value = recipeIdInput.value;
	
	closeModal();
	document.getElementById('myModalUpdate').style.display = "block";
}

function delete_ingredient(divId) {

	document.getElementById("ingredients").removeChild(divId);
}

function updateRecipe() {
	var xhttp = new XMLHttpRequest();
	var action = "update_recipe";
	var formdata = new FormData();

	formdata.append("action", action);
	
	var title = document.getElementById("modalUpdate_title").value;
	formdata.append("recipe_title", title);
	
	var description = document.getElementById("modalUpdate_description").value;
	formdata.append("recipe_description", description);
	
	var recipe_id = document.getElementById("modalUpdate_hidden_recipe_id").value;
	formdata.append("recipe_id", recipe_id);
	
	var category = document.getElementById("modalUpdate_category").value;
	formdata.append("recipe_category", category);

	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var response = this.responseText;
		
			alert(response);
			
			showMyRecipes();
			closeModalUpdate();
			
		}
	};

	xhttp.open("POST", "RecipeControlServlet", true);
	xhttp.send(formdata);
}