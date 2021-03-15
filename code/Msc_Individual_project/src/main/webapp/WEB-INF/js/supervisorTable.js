function sp(){
	$(".supervisor").show();
	$(".secondMarker").hide();
	document.getElementById("sp").className = "active";
	document.getElementById("sm").className = "";
}

function sm(){
	$(".supervisor").hide();
	$(".secondMarker").show();
	document.getElementById("sm").className = "active";
	document.getElementById("sp").className = "";
}

$(document).ready(function() {		
	$(".supervisor").show();
	$(".secondMarker").hide();
	})
	
	