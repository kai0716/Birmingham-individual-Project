<!DOCTYPE HTML>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script><%@include file="../jsp/supervisor-narbar.jsp" %></script>	

<style>
.side {
padding-top: 20px;
background-color: #334a69;
height: 1000px;
}
.row.content {height: 1500px}

@media screen and (max-width: 767px) {
   .sidenav {
   height: auto;
   padding: 15px;
   }
   .row.content {height: auto;} 
}
.title{
font-weight:bold;
font-size: 20px;

column-width: 200px;
column-height: 200px;
text-align: center;
}
.tables{
column-width: 200px;
column-height: 200px;
text-align: center;
}
</style>

<head>
    <title>Proposal History</title>
</head>

   <div class="container-fluid text-center">    
	  <div class="row content">
		    <div class="col-sm-2 side">
    		<ul class="nav nav-pills nav-stacked">
				<li class="active"><a href="/supervisor/myStudent">My Student</a></li>
				<c:if test="${Student.submit==1}"> 
				    <li><a style="color:white" href="/supervisor/myStudent/viewProposal/SP/${Student.studentID}">Current Proposal</a></li>
				    <li><a style="color:white" href="/supervisor/proposal/status/${Student.studentID}">Proposal History</a></li>
				</c:if>
				
			</ul>
		    </div>
		    <div class="col-sm-8 text-left">
		    	<c:if test="${not empty Proposal}"> 
				<table class="table table-hover">
					<tr class="title">
						<td>Submitted Version</td>
						<td>Submitted time</td>
						<td>Status</td>
						<td>View Proposal</td>
		
					</tr>
		
					<c:forEach items="${Proposal}"  var="proposal">
						<tr class="tables">
							<td class="tables">${proposal.version}</td>
							<td class="tables">${proposal.time}</td>
							
							<td class="tables">
							<c:if test="${proposal.submit==0}">
								<label>No Submit Record</label>
							</c:if>
							
							<c:if test="${proposal.submit==1}">
								<c:if test="${proposal.spApprove==0}">
									<label>Supervisor Marking</label>
								</c:if>
								<c:if test="${proposal.spApprove==2}">
									<label>Supervisor Declined</label>
								</c:if>
								<c:if test="${proposal.spApprove==1 && proposal.smApprove==0}">
									<label>Supervisor Approved, Second Marker Marking</label>
								</c:if>
								<c:if test="${proposal.smApprove==2}">
									<label>Second Marker Declined</label>
								</c:if>
								<c:if test="${proposal.spApprove==1 && proposal.smApprove==1}">
									<label>Proposal Approved:Completed</label>
								</c:if>
							</c:if>
							</td>
							
							<td class="tables">
							<a href="/supervisor/proposal/status/viewProposal/${proposal.proposalID}">
							   <button type="button" class="btn btn-default">View</button>
							</a>   
							</td>
						</tr>
					</c:forEach>
				</table>
				</c:if>
				
				<c:if test="${empty Proposal}"> 
				<p>No submitted proposal</p>
				</c:if>
		    </div>
		    <div class="col-sm-2 side">
		    </div>
	    </div>
  </div>

<body>

</body>
</html>

