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

   <div class="container-fluid text-center">    
  <div class="row content">
    <div class="col-sm-2 side">
    	<ul class="nav nav-pills nav-stacked">
	        <li  class="active"><a style="color:white" href="/student/proposal/status">Marking Status</a></li>
	     </ul>
    </div>
    <div class="col-sm-8 text-left"> 
	      <h1>Proposal</h1>
	      <h3>Project Title: ${Proposal.project_title}</h3>
	      <h3>Project Type: ${Proposal.project_type}</h3>
	      		<hr/>
	      <h2>Aim:</h2>
	        <c:set var = "string1" value = "${Proposal.project_aim}"/>
      		<c:set var = "string2" value = "${fn:replace(string1, '%&','<br/>')}" />
	      	<p>${string2}</p>
	      	      <hr/>
	      <h2>Related Work:</h2>
	        <c:set var = "string1" value = "${Proposal.project_relativeWork}"/>
      		<c:set var = "string2" value = "${fn:replace(string1, '%&','<br/>')}" />
	      	<p id="1">${string2}</p>	      
	      	      <hr/>
	      <h2>Objectives/Deliverables:</h2>
	        <c:set var = "string1" value = "${Proposal.project_objective}"/>
      		<c:set var = "string2" value = "${fn:replace(string1, '%&','<br/>')}" />
	      	<p id="1">${string2}</p>	 	      
	      	      <hr/>
	      <h2>Preliminary Architecture:</h2>
	      	<c:set var = "string1" value = "${Proposal.preliminary_architecture}"/>
      		<c:set var = "string2" value = "${fn:replace(string1, '%&','<br/>')}" />
	      	<p id="1">${string2}</p>	 
	      	      <hr/>
	      <h2>Methodology</h2>
	      	<c:set var = "string1" value = "${Proposal.project_methodology}"/>
      		<c:set var = "string2" value = "${fn:replace(string1, '%&','<br/>')}" />
	      	<p id="1">${string2}</p>	 
	      	      <hr/>
	      <h2>Risks and contingency plan:</h2>
	      	<c:set var = "string1" value = "${Proposal.project_risk}"/>
      		<c:set var = "string2" value = "${fn:replace(string1, '%&','<br/>')}" />
	      	<p id="1">${string2}</p>	 
	      	      <hr/>
	      <h2>Data:</h2>
	      	<c:set var = "string1" value = "${Proposal.project_data}"/>
      		<c:set var = "string2" value = "${fn:replace(string1, '%&','<br/>')}" />
	      	<p id="1">${string2}</p>	 	      

<hr/>
<hr/>
		<c:if test="${Proposal.spApprove ==2}">
	      <h2>Supervisor Comment:</h2>
	      	<c:set var = "string1" value = "${Proposal.spComment}"/>
      		<c:set var = "string2" value = "${fn:replace(string1, '%&','<br/>')}" />
	      	<p id="1">${string2}</p>
	    </c:if> 
	    <c:if test="${Proposal.spApprove ==1}">
	      <h2>Supervisor Comment:</h2>
			<p>Proposal Approved</p>
	    </c:if>
	   	<c:if test="${Proposal.spApprove ==0 }">
	      <h2>Supervisor Comment:</h2>
			<p>Waiting for marking</p>
	    </c:if>    	 	
<hr/>	      	
		<c:if test="${Proposal.smApprove == 2}">
	      <h2>Second Marker Comment:</h2>
	      	<c:set var = "string1" value = "${Proposal.smComment}"/>
      		<c:set var = "string2" value = "${fn:replace(string1, '%&','<br/>')}" />
	      	<p id="1">${string2}</p>
	    </c:if> 
	    <c:if test="${Proposal.smApprove ==1}">
	      <h2>Second Marker Comment:</h2>
			<p>Proposal Approved</p>
	    </c:if>  
	    <c:if test="${Proposal.smApprove ==0 }">
	      <h2>Second Marker Comment:</h2>
			<p>Waiting for marking</p>
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
