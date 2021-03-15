<!DOCTYPE HTML>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>My Student</title>
</head>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<script><%@include file="../jsp/supervisor-narbar.jsp" %></script>	
<script><%@include file="../js/supervisorTable.js" %></script>	
<style>
body{
background:#eee;  
}
.side{
padding-top: 20px;
background-color: #334a69;
height: 1000px;
}

.main-box {
    background: #FFFFFF;
    -webkit-box-shadow: 1px 1px 2px 0 #CCCCCC;
    -moz-box-shadow: 1px 1px 2px 0 #CCCCCC;
    -o-box-shadow: 1px 1px 2px 0 #CCCCCC;
    -ms-box-shadow: 1px 1px 2px 0 #CCCCCC;
    box-shadow: 1px 1px 2px 0 #CCCCCC;
    margin-bottom: 16px;
    -webikt-border-radius: 3px;
    -moz-border-radius: 3px;
    border-radius: 3px;
}

.user-list tbody td > img {
    position: relative;
	max-width: 50px;
	float: left;
	margin-right: 15px;
}
.user-list tbody td .user-link {
	display: block;
	font-size: 1.25em;
	padding-top: 13px;
	margin-left: 60px;
}
.user-list tbody td .user-subhead {
	font-size: 0.875em;
	font-style: italic;
}

.table {
    border-collapse: separate;
}
.table thead > tr > th {
    text-transform: uppercase;
    font-size: 0.875em;
}
.table tbody > tr > td {
	font-size: 0.875em;

	border-top: 10px solid #fff;
	vertical-align: middle;
	padding: 12px 8px;
}
.table tbody > tr > td:first-child,
.table thead > tr > th:first-child {
	padding-left: 20px;
}
.table tbody tr td {
    border-top: 1px solid #e7ebee;
    padding: 12px 8px;
}
.label{
font-size:12px;
}

<%-- CSS HTML Reference https://www.bootdey.com/snippets/view/User-list-page--%>
</style>
	<div class="col-lg-2 side">
    	<ul class="nav nav-pills nav-stacked">
			<li class="active" id="sp" ><a style="color:white" onclick="sp()">Supervised Student</a></li>
			<li id="sm"><a onclick="sm()" style="color:white">Second Marked Student</a></li>
		</ul>	
	</div>

	<div class="col-lg-10">
		<div class="supervisor">
		<div class="main-box clearfix">
			<div class="table-responsive">
				<table class="table user-list">
					<thead>
						<tr>
							<th style="width:20%"><span>Supervised Students</span></th>
							<th style="width:15%"><span>Email</span></th>
							<th style="width:20%"><span>Proposal</span></th>
							<th style="width:20%"><span>Proposal Status</span></th>
							<th style="width:10%"><span>Proposal History</span></th>
							<th style="width:10%"><span>Plan</span></th>
						</tr>
					</thead>
					<tbody style="min-height: 650px; max-height: 650px; overflow-y: scroll;">
						<c:forEach items="${StudentList_SP}"  var="studentList">
						<tr>
							<td>
								<img class="img-circle"
                     			src="https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=50" alt="User Pic">
								<label class="user-link">${studentList.firstName} ${studentList.lastName}</label>
							</td>
							<td>
								${studentList.email}
							</td>
							
							<td>
								<c:if test ="${studentList.submit==0}">	
									<span class="label label-primary">No submission</span>	 
								</c:if>
							
								<c:if test ="${studentList.submit==1}">	 
									<c:if test ="${studentList.spApprove ==0}">	
										<a href="/supervisor/myStudent/viewProposal/SP/${studentList.studentID}">
											<span class="fa-stack">
													<i class="fa fa-square fa-stack-2x"></i>
													<i class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
											</span>
										</a>
									</c:if>
									
									<c:if test ="${studentList.spApprove==1}">
										<span class="label label-primary">Second marker marking</span>	 
									</c:if>
									
									<c:if test ="${studentList.smApprove ==1}">
										<span class="label label-primary">Completed</span>		
									</c:if>
								</c:if>
								
								<c:if test ="${studentList.smApprove==2}">
									<span class="label label-primary">SecondMarker declined, Waiting new submission</span>		
								</c:if>
								
								<c:if test ="${studentList.spApprove==2}">
									<span class="label label-primary">Waiting new submission</span>	
								</c:if>
							</td>
							
							<td>
								<c:if test ="${studentList.spApprove ==0}">	
									<span class="label label-primary">Not Approved</span>
								</c:if>
								<c:if test ="${studentList.spApprove ==1}">	
									<span class="label label-primary">Approved</span>
								</c:if>
								<c:if test ="${studentList.spApprove==2}">	
									<span class="label label-primary">Declined</span>
								</c:if>
							</td>
							<td>
								<a href="/supervisor/proposal/status/${studentList.studentID}" class="table-link">
									<span class="fa-stack">
										<i class="fa fa-square fa-stack-2x"></i>
										<i class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
									</span>
								</a>
							</td>
							<td>
								<a href="/supervisor/myStudent/viewPlan/${studentList.studentID}" class="table-link">
									<span class="fa-stack">
										<i class="fa fa-square fa-stack-2x"></i>
										<i class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
									</span>
								</a>
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	
	<div class="secondMarker">
		<div class="main-box clearfix">
			<div class="table-responsive">
				<table class="table user-list">
					<thead>
						<tr>
							<th style="width:20%"><span>Second Marked Students</span></th>
							<th style="width:15%"><span>Email</span></th>
							<th style="width:20%"><span>Proposal</span></th>
							<th style="width:20%"><span>Proposal Status</span></th>
							<th style="width:10%"><span>Proposal History</span></th>
							<th style="width:10%"><span>Plan</span></th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${StudentList_SM}"  var="studentList">
						<tr>
							<td><%--image Reference https://bootsnipp.com/snippets/M4Ej--%>
								<img class="img-circle"
                     			src="https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=50" alt="User Pic">
								<label class="user-link">${studentList.firstName} ${studentList.lastName}</label>
							</td>
							<td>
								${studentList.email}
							</td>
							<td>
									<c:if test ="${studentList.submit==0}">
										<span class="label label-primary">No submission</span>	 
									</c:if>
								
									<c:if test ="${studentList.submit==1}">
										<c:if test ="${studentList.spApprove ==1 && studentList.submitVersion!=0 &&studentList.smApprove !=1}">	 
										   <a href="/supervisor/myStudent/viewProposal/SM/${studentList.studentID}">
												<span class="fa-stack">
													<i class="fa fa-square fa-stack-2x"></i>
													<i class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
												</span>
										   </a>
										</c:if>
										
										<c:if test ="${studentList.spApprove==0}">
											<span class="label label-primary">Supervisor marking</span>	 
										</c:if>
									</c:if>
			
									<c:if test ="${studentList.spApprove ==2}">	
										<span class="label label-primary">Supervisor declined. Waiting for new submission</span>
									</c:if>
									<c:if test ="${studentList.smApprove ==1}">
										<span class="label label-primary">Completed</span>	
									</c:if>
							</td>
							<td>
								<c:if test ="${studentList.smApprove ==0}">
									<span class="label label-primary">Not Approved</span>	
								</c:if>
								
								<c:if test ="${studentList.smApprove ==1}">
									<span class="label label-primary">Approved</span>	
								</c:if>
								
								<c:if test ="${studentList.smApprove ==2}">
									<span class="label label-primary">Declined</span>	
								</c:if>
							</td>
							<td>
								<a href="/supervisor/proposal/status/${studentList.studentID}" class="table-link">
									<span class="fa-stack">
										<i class="fa fa-square fa-stack-2x"></i>
										<i class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
									</span>
								</a>
							</td>
							<td>
								<a href="#" class="table-link">
									<span class="fa-stack">
										<i class="fa fa-square fa-stack-2x"></i>
										<i class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
									</span>
								</a>
							</td>
						</tr>
					</c:forEach>	
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
</body>
</html>