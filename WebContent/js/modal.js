window.onclick = function(event) {
    if (event.target == document.getElementById('myModal')) {
    	closeModal();
    }
}

function closeModal(){
	document.getElementById('myModal').style.display = "none";
}

function openModal(recipeIdInput, descriptionInput, 
					dateInput, userNameInput, 
					titleLabel, categoryLabel, resultDiv){
	
	document.getElementById('modal_title').innerHTML=titleLabel.innerHTML;
	document.getElementById('modal_category').innerHTML=categoryLabel.innerHTML;
	document.getElementById('modal_description_ta').value=descriptionInput.value;
	
	document.getElementById("modal_previwe_image").style.backgroundImage = resultDiv.style.backgroundImage;
	
	var secondaryImages = getSecondaryImages_Ingredients(recipeIdInput.value);
	
	document.getElementById('myModal').style.display = "block";
}

function getSecondaryImages_Ingredients(recipeId){
	var xhttp = new XMLHttpRequest();
	var action = "get_secondary_images_ingredients";
	var formdata = new FormData();

	formdata.append("action", action);
	formdata.append("recipe_id", recipeId);
	
	xhttp.onreadystatechange = function() {
		var response = JSON.parse(this.responseText);
		return response;
	};

	xhttp.open("POST", "RecipeControlServlet", true);
	xhttp.send(formdata);
}
