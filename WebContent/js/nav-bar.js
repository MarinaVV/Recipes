function createNavBar(){
	
	var mapOptions = {
			home : "Home",
			find_recipes : "Find Recipes",
			fav_recipes : "Favorite Recipes",
			create_recipe: "Create Recipe",
			ingredient: "Our ingredients list",
			account: "Account"
	}
	
	var ul = document.createElement("ul");
	ul.setAttribute("class", "nav_bar")
	
	
	for(key in mapOptions){
		var p = document.createElement('p');
		p.innerHTML=mapOptions[key];
		if(key == "home"){
			p.setAttribute("class", "active")
		}
		var li = document.createElement('li');
		li.appendChild(p);
		li.setAttribute("class", "nav_bar_option");
		li.setAttribute("onlick", key+"()");
		ul.appendChild(li);
	}
	
	document.getElementById("nav_bar_container").appendChild(ul);
}


