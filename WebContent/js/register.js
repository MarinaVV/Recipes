function register(){
/*	var form = document.getElementById("register_form");
	
	document.getElementById("action").value="register";

	form.submit();
	
	*/
	  var xhttp = new XMLHttpRequest();
	  var username = document.getElementById("username").value;
	  var password = document.getElementById("password").value;
	  var action = "register";
	  
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	var response = this.responseText;
	    	switch (response) {
			case "DUPLICATE_NAME":
				setResponseText("Name already exists")
				break;
			case "ACC_CREATED":
				setResponseText("Account created")
				break;
			case "ERROR":
				setResponseText("Unexpected error")
				break;
			}
	    }
	  };	
	 
	  xhttp.open("POST", "UserControlServlet?action=register", true);
	  xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	  xhttp.send("action=" + action + "&username=" + username +"&password=" + password);
}

function setResponseText(text){
	document.getElementById("response_text").innerHTML=text;
}