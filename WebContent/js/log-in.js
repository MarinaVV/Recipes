function login() {
	var form = document.getElementById("my_form");
	var username = document.getElementById("uname").value;
	var password = document.getElementById("pass").value;
	
	form.setAttribute("action", "log_in")
	form.submit();
}	