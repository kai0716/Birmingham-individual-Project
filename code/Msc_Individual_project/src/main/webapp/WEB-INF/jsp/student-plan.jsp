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
<script><%@include file="../js/planValidate.js" %></script>
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
	text-align: center;
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
					<li class="active"><a style="color:white" href="/student/plan">Weekly Plan</a></li>
					<li><a style="color:white" href="/student/plan/note">Note</a></li>
				</ul>
			</div>

			<div class="col-sm-10 text-left">

				<div class="col-sm-6 text-left">
					<div class="panel panel-primary">
						<div class="panel-heading clearfix">
							<h3 class="panel-title pull-left">Plan</h3>
							<div class="btn-group pull-right media-object">
								<a class="btn btn-success" data-toggle="modal"
									data-target="#myModalWeek">Set Weeks</a>
							</div>
						</div>

						<c:if test="${StudentObject.weeknumber ==0}">
							<div class="panel-body">No Plan</div>
						</c:if>
						<c:if test="${StudentObject.weeknumber !=0}">
							<div class="panel-body"
								style="min-height: 650px; max-height: 650px; overflow-y: scroll;">
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
														<form:form method="POST" modelAttribute="Student_Plan_Page" action="/student/plan" id="${week.weekID}a">
															<div class="form-group">
																<form:select path="transferWeek" class="form-control" style="background-color: #ECECEC;">
																	<form:option value="${icebox.weekID}">Waiting Box</form:option>
																	<c:forEach var="option" items="${Weeks}" varStatus="ct">
																		<form:option value="${option.weekID}">Transfer to Week:${ct.count}</form:option>
																	</c:forEach>
																</form:select>
															</div>
															
															<div class="form-group">
																<table class="table table-hover">
																	<tr class="tableTitle">
																		<td style="width:5%"><input type="checkbox"  name="select-all-${week.weekID}" class="select-all-${week.weekID} checkbox" /></td>
																		<td style="width:50%">Title</td>
																		<td style="width:10%">Status</td>
																		<td style="width:10%">Difficulty</td>

																	</tr>
																
																
																<c:forEach items="${week.plan}" var="plan">
																		<tr class="tableTitle">
																		<td><form:checkbox path="checkBox" value="${plan.planID}" class="select-item-${week.weekID} checkbox" name="select-item-${week.weekID}"/></td>
																		<td><button class="btn btn-link" type="button"
																			data-toggle="modal" data-target="#week${plan.planID}">
																			${plan.title}</button></td>
																		
																		<td>${plan.status}</td>
																		<td >${plan.difficulty}</td>		
																		</tr>
																			
																</c:forEach>
																</table>
															</div>
														</form:form>
													</c:if>			
													<c:if test="${empty week.plan}">
														<p>No plan</p>
													</c:if>
												</div>
												<div class="panel-footer">
													<input type="submit" class="btn btn-primary" value="Transfer" form="${week.weekID}a" name="transfer" class="col-sm-6 text-left" /> 
													<input type="submit" class="btn btn-danger" value="Delete" form="${week.weekID}a" name="delete" class="col-sm-6 text-left" />
												</div>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
						</c:if>
					</div>
				</div>

				<%-----------------------------------ICE BOX --------------------------------------%>
				<div class="col-sm-6 text-left">
					<div class="panel panel-primary">
						<div class="panel-heading clearfix">
							<h3 class="panel-title pull-left">Waiting Box</h3>
							<div class="btn-group pull-right media-object">
								<a class="btn btn-success" data-toggle="modal"
									data-target="#myModalPlan">Add Plan</a>
							</div>
						</div>

						<c:if test="${empty icebox.plan}">
							<div class="panel-body">No Plan</div>
						</c:if>

						<c:if test="${not empty icebox.plan}">
							<div class="panel-body"
								style="min-height: 600px; max-height: 600px; overflow-y: scroll;">
								<form:form method="POST" id="ckbox"
									modelAttribute="Student_Plan_Page" action="/student/plan">
									<div class="form-group">
										<c:if test="${not empty Weeks}">
											<form:select path="transferWeek" class="form-control" style="background-color: #ECECEC;">
												<c:forEach var="week" items="${Weeks}" varStatus="count">
													<form:option value="${week.weekID}">Transfer to Week:${count.count}</form:option>
												</c:forEach>
											</form:select>
										</c:if>
									</div>
									<div class="form-group">
										<table class="table table-hover">
											<tr class="tableTitle">
												<td style="width: 5%"><input type="checkbox"
													name="select-all-ice" class="select-all-ice checkbox" /></td>
												<td style="width: 50%">Title</td>
												<td style="width: 10%">Status</td>
												<td style="width: 10%">Difficulty</td>

											</tr>

											<c:forEach items="${icebox.plan}" var="plan">
												<tr class="tableTitle">
													<td><form:checkbox path="checkBox"
															value="${plan.planID}" class="select-item-ice checkbox"
															name="select-item-ice" /></td>
													<td><button class="btn btn-link" type="button"
															data-toggle="modal" data-target="#icebox${plan.planID}">
															${plan.title}</button></td>

													<td>${plan.status}</td>
													<td>${plan.difficulty}</td>
												</tr>
											</c:forEach>
										</table>
									</div>
								</form:form>
							</div>
							<div class="panel-footer">
								<input type="submit" class="btn btn-primary" value="Transfer" form="ckbox" name="transfer" class="col-sm-6 text-left" /> 
								<input type="submit" class="btn btn-danger" value="Delete" form="ckbox" name="delete" class="col-sm-6 text-left" />

							</div>
						</c:if>

					</div>
				</div>

			</div>
		</div>
	</div>

	<%-----------------------------------Modal --------------------------------------%>
	<div class="modal fade" id="myModalWeek" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<div class="modal-body">
					<form:form method="POST" modelAttribute="Student_Plan_Page"
						action="/student/plan" id="WeekForm"
						onsubmit="return weekValidate()">

						<div class="form-group">
							<form:label path="weeknumber" style="font-size:20px;"
								for="weeknumber">Number of Weeks</form:label>
							<form:input type="number" min="0" max="30" class="form-control"
								id="numWeek" path="weeknumber"
								value="${StudentObject.weeknumber}" />
						</div>

						<div class="form-group">
							<form:label path="selectedDate" style="font-size:20px;"
								cssClass="error" for="selectedDate">Start Date:</form:label>
							<form:input path="selectedDate" type="date" id="date"
								cssClass="error" />
						</div>

						<input type="submit" class="btn btn-primary" value="Submit"
							name="week" />
					</form:form>

				</div>
				<div id="error" class="error">
					<font color="red"></font>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>

	<div class="modal fade" id="myModalPlan" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<div class="modal-body">
					<form:form method="POST" modelAttribute="Student_Plan_Page"
						action="/student/plan" onsubmit="return planValidate()">

						<div class="form-group">
							<form:label path="title" style="font-size:15px;" for="title">Title:</form:label>
							<form:input class="form-control" id="title" path="title" />
						</div>

						<div class="form-group">
							<div class="col">
								<form:label path="description" for="description${plan.planID}"
									style="font-size:15px;">Description:</form:label>
								<form:textarea path="description" class="form-control" rows="10"
									id="description${plan.planID}"></form:textarea>
							</div>

						</div>

						<div class="form-group">
							<form:label path="difficulty" style="font-size:15px;"
								cssClass="error" for="difficulty">Difficulty:</form:label>
							<form:select path="difficulty" id="difficulty"
								class="form-control">
								<form:option value="High">High</form:option>
								<form:option value="Medium">Medium</form:option>
								<form:option value="Low">Low</form:option>
							</form:select>
						</div>

						<div class="form-group">
							<form:label path="link" style="font-size:15px;" for="link">Link:</form:label>
							<form:input class="form-control" id="link" path="link" />
						</div>

						<input type="submit" class="btn btn-primary" value="Submit"
							name="addplan" />
					</form:form>

				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>

<c:forEach items="${icebox.plan}" var="plan">
	<div class="modal fade" id="icebox${plan.planID}" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<div class="modal-body" style="min-height: 600px; max-height: 600px; overflow-y: scroll;">
						<form:form method="POST" modelAttribute="Student_Plan_Page"
							action="/student/plan">
							<div class="form-group">
								<form:label path="title" style="font-size:15px;" for="title">Title:</form:label>
								<form:input class="form-control" id="titles" path="title"
									value="${plan.title}" />
							</div>

							<div class="form-group">
								<div class="col">
									<form:label path="description" for="description${plan.planID}"
										style="font-size:15px;">Description:</form:label>
										<form:textarea path="description" class="form-control" rows="8"
										id="description${plan.planID}" value="${plan.description}"></form:textarea>
								</div>

							</div>

							<div class="form-group">
								<form:label path="difficulty" style="font-size:15px;" cssClass="error" for="difficulty">Difficulty:</form:label>
								<form:select path="difficulty" id="difficulty"
									class="form-control">
									<form:option value="${plan.difficulty}">${plan.difficulty}</form:option>
									<form:option value="High">High</form:option>
									<form:option value="Medium">Medium</form:option>
									<form:option value="Low">Low</form:option>
								</form:select>
							</div>

							<div class="form-group">
								<form:label path="link" style="font-size:15px;" for="link">Link:</form:label>
								<form:input type="url" class="form-control" id="link" path="link" value="${plan.link}"/>
							</div>

							<div class="form-group">
								<div class="col">
									<form:label path="note" for="note${plan.planID}"
										style="font-size:15px;">Note:</form:label>
									<form:textarea path="note" class="form-control" rows="8"
										id="note${plan.planID}" value="${plan.notes}"></form:textarea>
								</div>

							</div>
							
							<form:input type="hidden" path="status" value ="${plan.status}" style="font-size:15px;" cssClass="error"></form:input>


							<form:input path="planID" type="hidden" value="${plan.planID}" />
							<input type="submit" class="btn btn-primary" value="Save"
								name="edit" />

						</form:form>

					</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>
	</c:forEach>

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
						<form:form method="POST" modelAttribute="Student_Plan_Page"
							action="/student/plan">
							<div class="form-group">
								<form:label path="title" style="font-size:15px;" for="title">Title:</form:label>
								<form:input class="form-control" id="titles" path="title"
									value="${plan.title}" />
							</div>

							<div class="form-group">
								<div class="col">
									<form:label path="description" for="description${plan.planID}"
										style="font-size:15px;">Description:</form:label>
									<form:textarea path="description" class="form-control" rows="8"
										id="description${plan.planID}" value="${plan.description}"></form:textarea>
								</div>

							</div>

							<div class="form-group">
								<form:label path="difficulty" style="font-size:15px;"
									cssClass="error" for="difficulty">Difficulty:</form:label>
								<form:select path="difficulty" id="difficulty"
									class="form-control">
									<form:option value="${plan.difficulty}">${plan.difficulty}</form:option>
									<form:option value="High">High</form:option>
									<form:option value="Medium">Medium</form:option>
									<form:option value="Low">Low</form:option>
								</form:select>
							</div>
							
							<div class="form-group">
								<form:label path="status" style="font-size:15px;"
									cssClass="error" for="weekstatus">Status:</form:label>
								<form:select path="status" id="weekstatus" class="form-control" onchange="getStatus(this)">
									<form:option value="${plan.status}">${plan.status}</form:option>
									<form:option value="Unstarted">UnStarted</form:option>
									<form:option value="Start">Start</form:option>
									<form:option value="Finished">Finished</form:option>
								</form:select>
							</div>

							<div class="form-group">
								<form:label path="link" style="font-size:15px;" for="weeklink">Link:</form:label>
								<form:input type="url" class="form-control" id="weeklink" path="link" value="${plan.link}"/>
							</div>
							
							<div class="form-group">
								<div class="col">
									<form:label path="note" for="note${plan.planID}"
										style="font-size:15px;">Note:</form:label>
									<form:textarea path="note" class="form-control" rows="8"
										id="note${plan.planID}" value="${plan.notes}"></form:textarea>
								</div>

							</div>

							<form:input path="planID" type="hidden" value="${plan.planID}" />
							<input type="submit" class="btn btn-primary" value="Save"
								name="edit"/>
						</form:form>

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
