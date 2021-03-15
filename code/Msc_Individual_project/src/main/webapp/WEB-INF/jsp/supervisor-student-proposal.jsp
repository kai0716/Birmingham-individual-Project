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
height: 100%;
}
.row.content {height: 1500px}

@media screen and (max-width: 767px) {
   .sidenav {
   height: auto;
   padding: 15px;
   }
   .row.content {height: auto;} 
}
</style>

<head>
    <title>Proposal</title>
</head>

  <div class="container-fluid text-center">    
  <div class="row content">
    <div class="col-sm-2 side">
    		<ul class="nav nav-pills nav-stacked">
				<li class="active"><a href="/supervisor/myStudent">My Student</a></li>
				<c:if test="${Student.submit==1}"> 
				    <li><a style="color:white" href="/supervisor/myStudent/viewProposal/SP/${Student.studentID}">Current Proposal</a></li>
				    <li><a style="color:white"  href="/supervisor/proposal/status/${Student.studentID}">Proposal History</a></li>
				</c:if>
				 
			</ul>
    </div>
    <div class="col-sm-8 text-left"> 

	      <h2>Proposal</h2>
	      <h4>Project Title: ${Proposal.project_title}</h4>
	      <h4>Project Type: ${Proposal.project_type}</h4>
	      		<hr/>
	      <h4>Aim:</h4>
	        <c:set var = "string1" value = "${Proposal.project_aim}"/>
      		<c:set var = "string2" value = "${fn:replace(string1, '%&','<br/>')}" />
	      	<p>${string2}</p>
	      	      <hr/>
	      <h4>Related Work:</h4>
	        <c:set var = "string1" value = "${Proposal.project_relativeWork}"/>
      		<c:set var = "string2" value = "${fn:replace(string1, '%&','<br/>')}" />
	      	<p id="1">${string2}</p>	      
	      	      <hr/>
	      <h4>Objectives/Deliverables:</h4>
	        <c:set var = "string1" value = "${Proposal.project_objective}"/>
      		<c:set var = "string2" value = "${fn:replace(string1, '%&','<br/>')}" />
	      	<p id="1">${string2}</p>	 	      
	      	      <hr/>
	      <h4>Preliminary Architecture:</h4>
	      	<c:set var = "string1" value = "${Proposal.preliminary_architecture}"/>
      		<c:set var = "string2" value = "${fn:replace(string1, '%&','<br/>')}" />
	      	<p id="1">${string2}</p>	 
	      	      <hr/>
	      <h4>Methodology</h4>
	      	<c:set var = "string1" value = "${Proposal.project_methodology}"/>
      		<c:set var = "string2" value = "${fn:replace(string1, '%&','<br/>')}" />
	      	<p id="1">${string2}</p>	 
	      	      <hr/>
	      <h4>Risks and contingency plan:</h4>
	      	<c:set var = "string1" value = "${Proposal.project_risk}"/>
      		<c:set var = "string2" value = "${fn:replace(string1, '%&','<br/>')}" />
	      	<p id="1">${string2}</p>	 
	      	      <hr/>
	      <h4>Data:</h4>
	      	<c:set var = "string1" value = "${Proposal.project_data}"/>
      		<c:set var = "string2" value = "${fn:replace(string1, '%&','<br/>')}" />
	      	<p id="1">${string2}</p>	 	      

<hr/>
	 <c:if test="${marker=='SP'}">
			<form:form method ="POST" modelAttribute="viewProposal" id="edit" action="/supervisor/myStudent/viewProposal/SP/${Proposal.proposalID}">
				<div class="form-group">
					<form:label path="spComment" for="sp_comment" style="font-size:20px;">Comment of rejection:</form:label>
					<form:textarea path="spComment" class="form-control" rows="10" id="sp_comment"></form:textarea>
				</div></br>
				
				<div class="col-sm-8 ">
			 		<input type="submit" value="Decline" name="SP_comment"  id="addProposal" class="btn btn-danger" style="width:300px; height:50px; font-size:24px;"/>
			 	</div>
			 	<div class="col-sm-4 ">
			 		<a href="/supervisor/myStudent/sp_accept/${Proposal.proposalID}" >
			 			<button type="button" class="btn btn-primary" style="width:300px; height:50px; font-size:24px;" >Accept</button>
					</a>
			 	</div>
			 </form:form>

	</c:if>
		 

	<c:if test="${marker=='SM'}">
			<form:form method ="POST" modelAttribute="viewProposal" id="edit" action="/supervisor/myStudent/viewProposal/SM/${Proposal.proposalID}">
				<div class="form-group">
					<form:label path="smComment" for="sm_comment" style="font-size:20px;">Comment of rejection:</form:label>
					<form:textarea path="smComment" class="form-control" rows="10" id="sm_comment"></form:textarea>
				</div>
				</br>
				<div class="col-sm-8 ">
			 		<input type="submit" value="Decline" name="SM_accept"  id="addProposal" class="btn btn-danger" style="width:300px; height:50px; font-size:24px;"/> 
			 	</div>
			 	<div class="col-sm-4 ">
			 		<a href="/supervisor/myStudent/sm_accept/${Proposal.proposalID}">
			 			<button type="button" class="btn btn-primary" name ="SM_accept"style="width:300px; height:50px; font-size:24px;" >Accept</button>
			 		</a>
			 	</div>
			 </form:form>
			 


	</c:if>
    </div>
    
    <div class="col-sm-2 side">
    </div>
    </div>
  </div>
</div>
<body>

</body>
</html>
