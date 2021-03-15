<!DOCTYPE HTML>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<script><%@include file="../jsp/supervisor-narbar.jsp" %></script>
<script>
$( function() {   
    <c:forEach var="week" items="${Weeks}">
		var completed =0;
		var total=0;
		var percentage=0;
    	<c:if test="${not empty week.plan}">
	    	<c:forEach var="plan" items="${week.plan}">
	        	if ('${plan.status}' == "Finished"){
	        		completed++;
	        	}
	        	total++;
	    	</c:forEach>

	    	percentage = (completed/total)*100;
	    	console.log(percentage);
	    	$("#progress${week.weekID}").css("width", percentage+"%");
	    	$("#progressValue${week.weekID}").text(percentage+"% ("+completed+"/"+total+")");
	    	if(percentage==0){
	    		document.getElementById("progress${week.weekID}").className = "progress-bar progress-bar-striped progress-bar-warning"
	    		$("#progress${week.weekID}").css("width", "100%");
	    	}
	    	if(percentage<100 && percentage>0){
	    		document.getElementById("progress${week.weekID}").className = "progress-bar progress-bar-striped progress-bar-info"
	    	}
    	</c:if>
    	

    	<c:if test="${empty week.plan}">
    		$("#progress${week.weekID}").css("width", "100%");
	    	$("#progressValue${week.weekID}").text("0% ("+completed+"/"+total+")");
	    	document.getElementById("progress${week.weekID}").className = "progress-bar progress-bar-striped progress-bar-warning"
    	</c:if>
	</c:forEach>
} );
$( function() {    
    <c:forEach var="week" items="${Weeks}">
    	<c:forEach var="plan" items="${week.plan}">
        	var description = '${plan.description}'; 
        	var d = description.replace(/%&/g, '\n');
        	var note = '${plan.notes}';
        	var n= 	note.replace(/%&/g, '\n');
        	$("#description${plan.planID}").val(d);
        	$("#note${plan.planID}").val(n);
    	</c:forEach>
	</c:forEach>
    
    <c:forEach var="iplan" items="${icebox.plan}">
    	var description1 = '${iplan.description}'; 
    	var d1 = description1.replace(/%&/g, '\n');
    	var note1 = '${plan.notes}';
        var n1= note1.replace(/%&/g, '\n');
        $("#description${iplan.planID}").val(d1);
    	$("#note${iplan.planID}").val(n1);
	</c:forEach>
} );
</script>
<style>
.side{
padding-top: 20px;
background-color: #334a69;
height: 850px;
}

@media screen and (max-width: 767px) {
	.sidenav {
		height: auto;
		padding: 15px;
	}
	.row.content {
		height: auto;
	}
}

.title {
	font-weight: bold;
	font-size: 15px;
	column-width: 200px;
	column-height: 200px;
}

.tables {
	text-align: center;
}
.tableTitle{
text-align: center;
}

.modal-body {
	height: auto;
}
</style>

<head>
<title>Plan</title>
</head>
<body>
	<div class="container-fluid text-center">
		<div class="row content">
			<div class="col-sm-2 side">
				<ul class="nav nav-pills nav-stacked">
					<li class="active"><a href="/supervisor/myStudent">My Student</a></li>
					
				</ul>	
			</div>
			
			<div class="col-sm-1"></div>
			<div class="col-sm-8 text-left">
					<div class="panel panel-primary">
						<div class="panel-heading clearfix">
							<h3 class="panel-title pull-left">${Student}'s Plan</h3>
						</div>

						<c:if test="${StudentObject.weeknumber ==0}">
							<div class="panel-body">No Plan Created</div>
						</c:if>
						<c:if test="${StudentObject.weeknumber !=0}">
							<div class="panel-body"
								style="min-height: 750px; max-height: 650px; overflow-y: scroll;">
								<c:forEach var="week" items="${Weeks}" varStatus="count">

									<div class="panel-group">
										<div class="panel panel-success">
											<div class="panel-heading">
												<h4 class="panel-title">
													<a data-toggle="collapse" href="#collapse${week.weekID}">
													Week:${count.count} (${week.startDate} - ${week.endDate})
													</a>														
												</h4>
												</br>
												<div class="progress">
												  <div class="progress-bar progress-bar-striped progress-bar-success" id="progress${week.weekID}" role="progressbar">
												    <label id ="progressValue${week.weekID}"></label>
												  </div>
												</div>
											</div>
											<div id="collapse${week.weekID}"
												class="panel-collapse collapse">
												<div class="panel-body">
													<c:if test="${not empty week.plan}">
															
															<div class="form-group">
																<table class="table table-hover">
																	<tr class="tableTitle">
																		<td>Title</td>
																		<td>Status</td>
																		<td>Difficulty</td>

																	</tr>
																
																
																<c:forEach items="${week.plan}" var="plan">
																		<tr class="tableTitle">

																		<td><button class="btn btn-link" type="button"
																			data-toggle="modal" data-target="#week${plan.planID}">
																			${plan.title}</button></td>
																		
																		<td>${plan.status}</td>
																		<td >${plan.difficulty}</td>		
																		</tr>
																			
																</c:forEach>
																</table>
															</div>
													</c:if>
													<c:if test="${empty week.plan}">
														<p>No Plan Created</p>
													</c:if>
												</div>	
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
						</c:if>
					</div>
			</div>
			<div class="col-sm-1"></div>
		</div>
	</div>

	<%-----------------------------------Modal --------------------------------------%>
<c:forEach items="${Weeks}" var="week">
	<c:forEach items="${week.plan}" var="plan">
	<div class="modal fade" id="week${plan.planID}" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<div class="modal-body" style="min-height: 600px; max-height: 600px; overflow-y: scroll;">
							<div class="form-group">
								<label style="font-size:15px;" for="title">Title:</label>
								<input disabled="disabled" class="form-control" id="titles" value="${plan.title}" />
							</div>

							<div class="form-group">
								<div class="col">
									<label for="description${plan.planID}"
										style="font-size:15px;">Description:</label>
									<textarea disabled="disabled"class="form-control" rows="8" id="description${plan.planID}">{plan.description}</textarea>
								</div>

							</div>

							<div class="form-group">
								<label style="font-size:15px;" for="difficulty">Difficulty:</label>
								<select  id="difficulty"
									class="form-control" disabled="disabled">
									<option value="${plan.difficulty}">${plan.difficulty}</option>
								</select>
							</div>
							
							<div class="form-group">
								<label  style="font-size:15px;"
									 for="weekstatus">Status:</label>
								<select id="weekstatus" class="form-control" disabled="disabled">
									<option value="${plan.status}">${plan.status}</option>
								</select>
							</div>

							<div class="form-group">
								<label  style="font-size:15px;" for="weeklink">Link:</label>
								<input disabled="disabled" type="url" class="form-control" id="weeklink" value="${plan.link}"/>
							</div>
							
							<div class="form-group">
								<div class="col">
									<label for="note${plan.planID}" style="font-size:15px;">Note:</label>
									<textarea  class="form-control" rows="8" id="note${plan.planID}" disabled="disabled">${plan.notes}</textarea>
								</div>
							</div>
					</div>

				<div class="modal-footer panel-success">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>
	</c:forEach>
	</c:forEach>	
</body>
</html>
