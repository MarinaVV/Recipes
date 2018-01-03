function delete_ingredient(divId) {

	document.getElementById("ingredients").removeChild(divId);
}


function setUnitsSuggestions(){
	var unitsDatalist = document.getElementById("suggestionsUnits");
	
	var unitsList =["kg","g","mg","tbsp.","tsp.","ml","l","pint"];
	
	for(var index=0;index<unitsList.length;index++){
		var option = document.createElement("option");
		option.setAttribute("value", unitsList[index]);
		
		unitsDatalist.appendChild(option);
	}
}



