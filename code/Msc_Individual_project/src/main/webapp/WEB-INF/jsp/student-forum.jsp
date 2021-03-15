<!DOCTYPE HTML>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<title>Forum</title>
</head>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<script><%@include file="../jsp/student-narbar.jsp" %></script>
<script><%@include file="../js/search.js" %></script>
<script><%@include file="../js/forum.js" %></script>
<style><%@include file="../css/forum.css" %></style>

<body>
<div class="container">
<section class="content">
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
							<li><a href="/student/discussion/tag/${tag.tagID}">${tag.tag}<span class="pull-right">${fn:length(tag.studentList)}</span></a></li>
						</ul>
						</c:if>
					</c:forEach>
					
					<hr>
					
					<p><strong>Tags</strong></p>
					<c:forEach items="${TagList}"  var="tag">
						<c:if test="${tag.tagID != 1 and tag.tagID != 2 and tag.tagID != 3}">
						<ul class="support-label">
							<li><a href="/student/discussion/tag/${tag.tagID}"><span class="bg-blue">&nbsp;</span>&nbsp;&nbsp;&nbsp;${tag.tag}<span class="pull-right">${fn:length(tag.studentList)}</span></a></li>
						</ul>
						</c:if>
					</c:forEach>
				</div>
			</div>
		</div>
		<div class="col-md-9">
			<div class="grid support-content">
				 <div class="grid-body">
					 <h2>Discussion Forum</h2>
					 
					 <hr>
				
					<button type="button" class="btn btn-success pull-right" data-toggle="modal" data-target="#newIssue">New Forum</button>
					<div class="modal fade" id="newIssue" tabindex="-1" role="dialog" aria-labelledby="newIssue" aria-hidden="true">
						<div class="modal-wrapper">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header bg-blue">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
										<h4 class="modal-title"><i class="fa fa-pencil"></i> Create New Issue</h4>
									</div>
									<form:form method ="POST" modelAttribute="Forums"  action="/student/discussion/add">      				
										<div class="modal-body">
											<div class="form-group">
												<form:input path="forum_title" name="subject" type="text" class="form-control" placeholder="Subject"/>
											</div>

											<div class="form-group">
												<form:textarea path="forum_detail" name="message" class="form-control" placeholder="Please detail your issue or question" style="height: 120px;"></form:textarea>
											</div>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-times"></i> Close</button>
											<button type="submit" class="btn btn-primary pull-right"><i class="fa fa-pencil"></i> Create</button>
										</div>
									</form:form>
								</div>
							</div>
						</div>
					</div>
					</br>
					</br>
					<div class="padding"></div>
					 
					<div class="row">

						<div class="col-md-12">
						<c:if test="${not empty AllForum}">
							<c:forEach items="${AllForum}" var="forum">
							<ul class="list-group fa-padding">
								<li class="list-group-item">
									<div class="media">
										<div class="media-body">
											<strong><a href="/student/discussion/forum/${forum.forumID}">${forum.title}</a></strong>
											<p class="info">Opened by <a>${forum.student.firstName} ${forum.student.lastName} </a> <span id="ago${forum.forumID}"></span> <i class="fa fa-comments"></i> <a href="/student/discussion/forum/${forum.forumID}"> ${fn:length(forum.contentList)} comments</a></p>
										</div>
									</div>
								</li>
								
							</ul>
							</c:forEach>					
						</c:if>
						<c:if test="${empty AllForum}">
						No Forum
						</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
</div>
</body>
</html>