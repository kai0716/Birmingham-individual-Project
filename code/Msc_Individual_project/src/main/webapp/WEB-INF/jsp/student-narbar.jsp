
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>
.navbar-nav li:hover > ul.dropdown-menu {
    display: block;
}
.dropdown-submenu {
    position:relative;
}
.dropdown-submenu>.dropdown-menu {
    top:0;
    left:100%;
    margin-top:-6px;
}
</style>

<nav class="navbar navbar-inverse">
	<div class="container-fluid">
	
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#alignment-example" aria-expanded="false">
			</button>
		<a class="navbar-brand" href="">Welcome</a>
		</div>
		
		<div class="collapse navbar-collapse" id="alignment-example">
			<ul class="nav navbar-nav">
				<li><a href="/student/" style="font-size:20px">Home</a></li>
			</ul>
			
			<ul class="nav navbar-nav">
				<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown"  style="font-size:20px">Project<span class="caret"></span></a>
	        		<ul class="dropdown-menu">
	        			
	        			<li  class="dropdown-submenu">
	        			<a class="dropdown-toggle" data-toggle="dropdown">Proposal<span class="caret"></span></a>
	        				<ul class="dropdown-menu">
	        					<li><a href="/student/proposal">View Proposal</a></li>
	        					<li><a href="/student/proposal/edit">Edit Proposal</a></li>
	        					<li><a href="/student/proposal/status">Marking Status</a></li>
	        				</ul>
	        			</li>	
	          			
	          			<li><a href="/student/plan">Weekly Objective(Plan)</a></li>
	          			<li><a href="/student/tags">Tags</a></li>
	        		</ul>
        		</li>
			</ul>
			
			<ul class="nav navbar-nav">
				<li><a href="/student/people" style="font-size:20px">People</a></li>
			</ul>

						
			<ul class="nav navbar-nav">
				<li><a href="/student/discussion" style="font-size:20px">Discussion</a></li>
			</ul>
			
			<ul class="nav navbar-nav navbar-right">
				<li><a><font size="5px"color="white">${Student} </font></a></li>
     			<li><a href="/logout" style="font-size:20px "><span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
 		 	</ul>
		</div>
	</div>
</nav>

