<!DOCTYPE HTML>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<script
	src="https://ajax.aspnetcdn.com/ajax/jquery.validate/1.10.0/jquery.validate.js"
	type="text/javascript"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script> <%@include file="../jsp/student-narbar.jsp" %></script>
<script> <%@include file="../js/proposalVaildate.js" %></script>
<script> <%@include file="../js/proposalEdit.js" %></script>
<style>
.side {
padding-top: 20px;
background-color: #334a69;
height: 800px;
}
</style>
<head>
    <title>Proposal</title>
</head>
<body>
	<div class="col-sm-2 side">
	    <ul class="nav nav-pills nav-stacked">
	        <li class="active"><a href="/student/proposal">View Proposal</a></li>
	        <li><a style="color:white" href="/student/proposal/edit">Edit Proposal</a></li>
	        <li><a style="color:white" href="/student/proposal/status">Marking Status</a></li>
	     </ul>
	 </div>

<div class="col-sm-10">
<div class="form">
<form:form method ="POST" modelAttribute="Edit_Propsal" id="edit" action="/student/proposal/update" onsubmit="return proposalValidate()">      				
	<div class="form-group">
		<div class="container">
			<div class="row align-items-center">
			<div class="col">
				<form:label path="project_title" for="title" style="font-size:20px;">Project Title</form:label>
				<form:input class="form-control"  path="project_title" name="title" id ="title" value ="${Proposal.project_title}" />
			</div>
			</div>
		</div>
	</div>
	</br>
	
		<div class="form-group">
		<div class="container">
			<div class="row align-items-center">
			<div class="col">
		       <form:label path="project_type" for="type" style="font-size:20px;">Project Type</form:label>
		       <form:select path="project_type" id="type" class="form-control">
						<c:forEach var="projectType" items="${ProjectType}">
						<tr>
							<td>                      
					              <form:option value="${projectType.type}">${projectType.type}</form:option>
					        </td>
						</tr>
					    </c:forEach> 
		       </form:select>
			</div>
			</div>
		</div>
	</div>
	</br>
  
	<div class="form-group">
		<div class="container">
			<div class="row align-items-center">
			<div class="col">
				  <form:label path="project_aim" for="aim" style="font-size:20px;">Aims:</form:label>
				  <form:textarea path="project_aim" class="form-control" rows="10" id="aim"></form:textarea>

			</div>
			</div>
		</div>
	</div>
	</br>
	
	<div class="form-group">
		<div class="container">
			<div class="row align-items-center">
			<div class="col">
				<form:label path="project_relativeWork" for="relativework" style="font-size:20px;">Related Work:</form:label>
		  		<form:textarea path="project_relativeWork" class="form-control" rows="10" id="relativework"></form:textarea>
			</div>
			</div>
		</div>
	</div>				
	</br>
	
	<div class="form-group">
		<div class="container">
			<div class="row align-items-center">
			<div class="col">
				<form:label path="project_objective" for="objective" style="font-size:20px;">Project Objectives/Deliverables:</form:label>
		  		<form:textarea path="project_objective" class="form-control" rows="10" id="objective"></form:textarea>
			</div>
			</div>
		</div>
	</div>				
	</br>
	
	<div class="form-group">
		<div class="container">
			<div class="row align-items-center">
			<div class="col">
				<form:label path="preliminary_architecture" for="pa" style="font-size:20px;">Preliminary Architecture:</form:label>
		  		<form:textarea path="preliminary_architecture" class="form-control" rows="10" id="pa"></form:textarea>
			</div>
			</div>
		</div>
	</div>				
	</br>

	<div class="form-group">
		<div class="container">
			<div class="row align-items-center">
			<div class="col">
				<form:label path="project_methodology" for="methodology" style="font-size:20px;">Methodology: </form:label>
		  		<form:textarea path="project_methodology" class="form-control" rows="10" id="methodology"></form:textarea>
			</div>
			</div>
		</div>
	</div>				
	</br>
	
  	<div class="form-group">
		<div class="container">
			<div class="row align-items-center">
			<div class="col">
			  <form:label path="project_risk" for="risk" style="font-size:20px;">Risks and contingency plan:</form:label>
			  <form:textarea path="project_risk" class="form-control" rows="10" id="risk"></form:textarea>
			</div>
			</div>
		</div>
	</div>		 
	</br>

  	<div class="form-group">
		<div class="container">
			<div class="row align-items-center">
			<div class="col">
				  <form:label path="project_data" for="data" style="font-size:20px;">Data</form:label>
				  <form:textarea path="project_data" class="form-control" rows="10" id="data"></form:textarea>
			</div>
			</div>
		</div>
	</div>
	</br>

<div class="form-group">
	<div class="container">
		<div class="row align-items-center">
			<div class="col">
				<c:if test="${SubmitProposal.student.submit ==0 or SubmitProposal.student.submit ==2}"> 
	
				<form:input id="proposalID" path ="proposalID" type="hidden" value="${Proposal.proposalID}"/>
		 		<input type="submit" value="Submit" name="submit_proposal"  id="addProposal" class="btn btn-primary" style="width:300px; height:50px; font-size:24px;"/> 
		 		<input type="submit" class="button" name="save" value="Save" id="addProposal" class="btn btn-primary" style="width:300px; height:50px; font-size:24px;"/>
				</c:if>   	

				<c:if test="${SubmitProposal.student.submit ==1}">
					<form:input id="proposalID" path ="proposalID" type="hidden" value="${Proposal.proposalID}"/>
			 		<input type="submit" class="button" name="save" value="Save" id="addProposal" class="btn btn-primary" style="width:300px; height:50px; font-size:24px;"/>
		 			<label>Waiting for marking cannot submit, save only</label>
		 		</c:if> 
 			</div>
 		</div>
 	</div>
 </div>
 </form:form> 
</div>
</div>
</body>
</html>
