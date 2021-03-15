<!DOCTYPE HTML>
<html>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://ajax.aspnetcdn.com/ajax/jquery.validate/1.10.0/jquery.validate.js"
	type="text/javascript"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
<style>
.side{
padding-top: 20px;
background-color: #334a69;
height: 850px;
}

</style>
<script>
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
	
	
</script>

	<div class="col-lg-2 side">
    	<ul class="nav nav-pills nav-stacked">
			<li class="active" id="sp" ><a style="color:white" onclick="sp()">Student List</a></li>
			<li id="sm"><a onclick="sm()" style="color:white">Supervisor List</a></li>
		</ul>	
	</div>
</html>	