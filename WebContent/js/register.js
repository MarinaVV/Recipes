function register() {
	  var xhttp = new XMLHttpRequest();
	  var username = document.getElementById("uname").value;
	  var password = document.getElementById("pass").value;
	  var action = "register";
	  
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	window.location = this.responseText;
	    }
	  };
	 
	  xhttp.open("POST", "UserControlServlet?action=register", true);
	  xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	  xhttp.send("action=" + action + "&username=" + username +"&password=" + password);
}