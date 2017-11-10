window.onclick = function(event) {
    if (event.target == document.getElementById('myModal')) {
    	document.getElementById('myModal').style.display = "none";
    }
}

function closeModal(){
	document.getElementById('myModal').style.display = "none";
}

function openModal(){
	document.getElementById('myModal').style.display = "block";
}
