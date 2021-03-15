<!DOCTYPE HTML>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script><%@include file="../jsp/supervisor-narbar.jsp" %></script>	
<head>
    <title>Supervisor Page</title>
</head>
<body>
<h1 align="center">Home Page</h1>

<div class="container">
	<div class="row">
		<div class="c1"></div>
		<div class="col-lg-4" >
			<a href="/teacher/"></a>
			<button type="button" class="btn btn-default btn-lg" data-dismiss="modal">My Student</button>
		</div>	
		<div class="col-lg-4">
				<a href="/teacher/Exam"></a>
			<button type="button" class="btn btn-default btn-lg" data-dismiss="modal">Other Student</button>
		</div>
		
		<div class="col-lg-4">
			<a href="/teacher/"></a>
			<button type="button" class="btn btn-default btn-lg	" data-dismiss="modal">Chat</button>
	 	</div>	
	</div>	
</div>
</body>
</html>
