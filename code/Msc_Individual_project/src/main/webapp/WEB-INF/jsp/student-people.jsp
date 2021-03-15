<!DOCTYPE HTML>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<title>People</title>
</head>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<script><%@include file="../jsp/student-narbar.jsp" %></script>
<script><%@include file="../js/search.js" %></script>
<style><%@include file="../css/student-people.css" %></style>
<style>

body{
}
.container{
width: 1700px;
}
.avatar--md .avatar__content img{
    width:40px;
    height:40px;
}
.mb-4{
margin-top:40px;
}
<%-- Reference https://www.bootdey.com/snippets/view/User-list-page--%>
</style>
<div class="container">
    <div class="row">
    	<div class="col-md-3">
			<div class="grid support">
				<div class="grid-body">
					<h2>Search</h2>
					
					<hr>
					<p><strong>Project Type</strong></p>
					<c:forEach items="${TagList}"  var="tag">
						<c:if test="${tag.tagID == 1 or tag.tagID == 2 or tag.tagID == 3}">
						<ul class="support-label">
							<li><a href="/student/people/${tag.tagID}">${tag.tag}<span class="pull-right">${fn:length(tag.studentList)}</span></a></li>
						</ul>
						</c:if>
					</c:forEach>
					
					<hr>
					
					<p><strong>Tags</strong></p>
					<c:forEach items="${TagList}"  var="tag">
						<c:if test="${tag.tagID != 1 and tag.tagID != 2 and tag.tagID != 3}">
						<ul class="support-label">
							<li><a href="/student/people/${tag.tagID}"><span class="bg-blue">&nbsp;</span>&nbsp;&nbsp;&nbsp;${tag.tag}<span class="pull-right">${fn:length(tag.studentList)}</span></a></li>
						</ul>
						</c:if>
					</c:forEach>
				</div>
			</div>
		</div>
        <div class="col-sm-1">
        </div>
        <div class="col-lg-9">
            <div class="d-flex flex-column flex-md-row mb-3 mb-md-0">
                <nav class="mr-auto d-flex align-items-center" aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="active breadcrumb-item" aria-current="page"><a href="/"><i class="fa fa-home"></i></a></li>
                        <li class="active breadcrumb-item" aria-current="page"><a href="/student/people">Projects List</a></li>
                    </ol>
                </nav>
            </div>
            <div>
	            <ul class="nav nav-tabs" role="tablist" id="myTab">
				  <li class="active"><a href="#student" role="tab" data-toggle="tab" >Student</a></li>
				  <li ><a href="#supervisor" role="tab" data-toggle="tab">Supervisor</a></li>
				</ul>
            </div>
            </br>
            
            <div class="tab-content">
			  <div class="tab-pane" id="supervisor">
			  	   <div class="mb-3 Card_custom-card--border_5wJKy card">
		                <div class="table-responsive-xl">
		                    <table class="mb-0 table table-hover">
		                        <thead>
		                            <tr>
		                                <th class="align-middle bt-0">Supervisor</th>
		                                <th class="align-middle bt-0">Specialty</th>
		                                <th class="align-middle bt-0">Email</th>
		                            </tr>
		                        </thead>
		                        <tbody>
									<c:forEach items="${SupervisorList}"  var="spList">
		                            <tr>
		                           		<td class="align-middle">
											${spList.firstName} ${spList.lastName}
		                                </td>
		                                <td>${spList.projectType.type}</td>
		                                <td>${spList.email}</td>
		                            </tr>
		                            </c:forEach>
		                        </tbody>
		                    </table>
		                </div>
		            </div>
			  </div>
			  
			  
			  <div class="tab-pane active" id="student">
			  		<div class="mb-3 Card_custom-card--border_5wJKy card">
		                <div class="table-responsive-xl">
		                    <table class="mb-0 table table-hover">
		                        <thead >
		                            <tr>
		                                <th class="align-middle bt-0" style="width:25%">Project</th>
		                                <th class="align-middle bt-0">Project Type</th>
		                                <th class="align-middle bt-0">Student</th>
		                                <th class="align-middle bt-0">Supervisor</th>
		                                <th class="align-middle bt-0">Email</th>
		                                <th class="align-middle bt-0">Actions</th>
		                                
		                            </tr>
		                        </thead>
		                        <tbody>
		                        <c:forEach items="${StudentList}"  var="studentList">
		                            <tr>
		                                <td class="align-middle">
		                                    <div>
		                                    	<c:if test="${not empty studentList.proposal}">
		                                    	<a>${studentList.proposal[fn:length(studentList.proposal)-1].project_title}	</a>
		                                    	</c:if>
		                                 		<c:if test="${ empty studentList.proposal}">
		                                    		Waiting
		                                    	</c:if>
		                                    	<c:if test="${studentList.proposal[fn:length(studentList.proposal)-1].savetype=='save'}">
		                                    		Waiting
		                                    	</c:if>
		                                    </div>
		                                 </td>
		                                <td class="align-middle">
		                                	<c:if test="${studentList.proposal[fn:length(studentList.proposal)-1].savetype!='save'}">
		                                	<span>${studentList.proposal[fn:length(studentList.proposal)-1].project_type}</span>
		                                	</c:if>
		                                </td>
		                                <td class="align-middle">
											${studentList.firstName} ${studentList.lastName}
		                                </td>
		                               	<td class="align-middle">
											 <c:forEach items="${studentList.secondMarker}"  var="sm">
											 	<c:if test="${sm.role== 'Supervisor'}">
											 		${sm.supervisor.firstName} ${sm.supervisor.lastName}
											 	</c:if>
											 </c:forEach>
		                                </td>
		                                <td class="align-middle">${studentList.email}</td>
		                                <td class="align-middle	">
		                            
		                                    <c:if test="${studentList.proposal[fn:length(studentList.proposal)-1].spApprove ==1 and studentList.proposal[fn:length(studentList.proposal)-1].smApprove==1 and studentList.proposal[fn:length(studentList.proposal)-1].savetype!='save'}">
		                                    <div class="btn-group">
					        					<a class="dropdown-toggle btn btn-link" data-toggle="dropdown"><i class="fa fa-gear"></i></a>
						        				<ul class="dropdown-menu">
						        					<li><a href="/student/others/proposal/${studentList.studentID}">View Proposal</a></li>
						        					<li><a href="/student/others/plan/${studentList.studentID}">View Plan</a></li>
						        				</ul>
		                                    </div>
		                                    </c:if>
		                                    <c:if test="${studentList.proposal[fn:length(studentList.proposal)-1].spApprove !=1 or studentList.proposal[fn:length(studentList.proposal)-1].smApprove!=1 }">
												Waiting for Approval
		                                    </c:if>
		                                </td>
		                            </tr>
		                            </c:forEach>
		                        </tbody>
		                    </table>
		                </div>
		            </div>
			  </div>
        </div>
    </div>
</div>
</div>
</html>