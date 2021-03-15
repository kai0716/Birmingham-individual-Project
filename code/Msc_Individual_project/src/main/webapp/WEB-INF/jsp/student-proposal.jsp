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
<script><%@include file="../jsp/student-narbar.jsp" %></script>	

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
<body>

   <div class="container-fluid text-center">    
  <div class="row content">
    <div class="col-sm-2 side">
    	<ul class="nav nav-pills nav-stacked">
	        <li class="active"><a href="/student/proposal">View Proposal</a></li>
	        <li><a style="color:white" href="/student/proposal/edit">Edit Proposal</a></li>
	        <li><a style="color:white" href="/student/proposal/status">Marking Status</a></li>
	    </ul>
    </div>
    <div class="col-sm-8 text-left"> 
    	<c:if test="${Empty==0}">
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
      	</c:if>
      	
      	<c:if test="${Empty==1}">
	      <h1>No Record</h1>
	      <a href="/student/proposal/edit">
		  <button type="button" class="btn btn-primary btn-lg">Write Proposal</button>
		  </a>
      	</c:if>
    </div>
    <div class="col-sm-2 side">
    </div>
    </div>
  </div>
</div>

</body>
</html>
