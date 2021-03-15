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
<script><%@include file="../jsp/student-narbar.jsp" %></script>
<script>
$( function() {    
    <c:forEach var="week" items="${Weeks}">
    	<c:forEach var="plan" items="${week.plan}">
        	var note = '${plan.notes}';
        	var n= 	note.replace(/%&/g, '\n');
        	$("#note${plan.planID}").text(n);
    	</c:forEach>
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
<%-- css Reference https://www.bootdey.com/snippets/view/Gradients-dashboard-cards --%>
.order-card {
    color: #fff;
}

.bg-c-blue {
    background: linear-gradient(45deg,#4099ff,#73b4ff);
}
.card {
    border-radius: 20px;
    -webkit-box-shadow: 0 1px 2.94px 0.06px rgba(4,26,55,0.16);
    box-shadow: 0 1px 2.94px 0.06px rgba(4,26,55,0.16);
    border: none;
    margin-bottom: 30px;
    -webkit-transition: all 0.3s ease-in-out;
    transition: all 0.3s ease-in-out;
}

.card .card-block {
    padding: 25px;
}

.order-card i {
    font-size: 26px;
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
					<li class="active"><a style="color:white" href="/student/plan">Weekly Plan</a></li>
					<li id="ab"><a style="color:white" href="/student/plan/note">Note</a></li>
				</ul>	
			</div>
			
			<div class="col-sm-1"></div>
			<div class="col-sm-8 text-left">
					<div class="panel panel-primary">
						<div class="panel-heading clearfix">
							<h3 class="panel-title pull-left">Note</h3>	
						</div>

						<c:if test="${StudentObject.weeknumber ==0}">
							<div class="panel-body">No Plan Created</div>
						</c:if>
						<c:if test="${StudentObject.weeknumber !=0}">
							<div class="panel-body"
								style="min-height: 700px; max-height: 650px; overflow-y: scroll;">
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
											</div>
											<div id="collapse${week.weekID}"
												class="panel-collapse collapse">
												<div class="panel-body">
													<c:if test="${not empty week.plan}">
														<c:forEach items="${week.plan}" var="plan" varStatus="ct">
													        <div class="col-md-6">
													            <div class="card bg-c-blue order-card">
													                <div class="card-block">
													                    <h4>${ct.count}. ${plan.title}</h4>
													                    <label>Notes:</label>
													                    <p id="note${plan.planID}"></p>
													                    	<c:if test="${empty plan.notes}">
																				No note
																			</c:if>
																		<label>Link: ${plan.link}</label>
																			<c:if test="${empty plan.link}">
																				No Link
																			</c:if>
													                </div>
													            </div>
													        </div>
												        </c:forEach>
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

</body>
</html>
