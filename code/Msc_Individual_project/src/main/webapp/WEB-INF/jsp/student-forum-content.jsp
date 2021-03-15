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
<script><%@include file="../js/forum.js" %></script>
<%--CSS Reference: https://www.bootdey.com/snippets/view/Social-post --%>
<style>
.panel-shadow {
    box-shadow: rgba(0, 0, 0, 0.3) 7px 7px 7px;
}
.panel-white {
  border: 1px solid #dddddd;
}
.panel-white  .panel-heading {
  color: #333;
  background-color: #fff;
  border-color: #ddd;
}
.panel-white  .panel-footer {
  background-color: #fff;
  border-color: #ddd;
}

.post .post-heading {
  height: 150px;
  padding: 20px 15px;
}

.post .post-heading .meta .title {
  margin-bottom: 0;
}
.post .post-heading .meta .title a {
  color: black;
}
.post .post-heading .meta .title a:hover {
  color: #aaaaaa;
}
.post .post-heading .meta .time {
  margin-top: 8px;
  color: #999;
}
.post .post-description {
  padding: 15px;
}
.post .post-description p {
  font-size: 14px;
}
.post .post-description .stats {
  margin-top: 20px;
}
.post .post-description .stats .stat-item {
  display: inline-block;
  margin-right: 15px;
}
.post .post-description .stats .stat-item .icon {
  margin-right: 8px;
}
.post .post-footer {
  border-top: 1px solid #ddd;
  padding: 15px;
}
.post .post-footer .input-group-addon a {
  color: #454545;
}
.post .post-footer .comments-list {
  padding: 0;
  margin-top: 20px;
  list-style-type: none;
}
.post .post-footer .comments-list .comment {
  display: block;
  width: 100%;
  margin: 20px 0;
}

.post .post-footer .comments-list .comment .comment-heading {
  display: block;
  width: 100%;
}
.post .post-footer .comments-list .comment .comment-heading .user {
  font-size: 14px;
  font-weight: bold;
  display: inline;
  margin-top: 0;
  margin-right: 10px;
}
.post .post-footer .comments-list .comment .comment-heading .time {
  font-size: 12px;
  color: #aaa;
  margin-top: 0;
  display: inline;
}
.post .post-footer .comments-list .comment .comment-body {
  margin-left: 50px;
}
.post .post-footer .comments-list .comment > .comments-list {
  margin-left: 50px;
}
.post .post-footer .comments-list .comment .avatar {
  width: 45px;
  height: 35px;
}
</style>
<body>
<div class="container bootstrap snippets bootdey">
    <div class="col-sm-12">
        <div class="panel panel-white post panel-shadow">
            <div class="post-heading">
                <div class="pull-left meta">
                    <div class="title h3">
                        <b>${Forum.title}</b>
                    </div>
                    <h6><b>Created by ${Forum.student.firstName} ${Forum.student.lastName}</b></h6> <h6 class="text-muted time"><span id="ago"></span></h6>
                </div>
            </div> 
            <div class="post-description"> 
                <p>${Forum.detail}</p>

            </div>
            <div class="post-footer">
                <form:form method ="POST" modelAttribute="Forums"  action="/student/discussion/addContent">
                	
                	<form:textarea path="content" class="form-control" placeholder="Add a comment" rows="10"></form:textarea>
        			<form:input id="id" path ="id" type="hidden" value="${Forum.forumID}"/>
        			</br>
        			<div class="form-group">
                	<button type="submit" class="btn btn-primary pull-right"><i class="fa fa-pencil"></i> Add</button>
               		</br></br>
               		</div>
                </form:form>

			<c:forEach items="${Content}" var="content">                      				
                <ul class="comments-list">
                    <li class="comment">
                        <a class="pull-left">
                            <img class="avatar" src="https://bootdey.com/img/Content/user_1.jpg" alt="avatar">
                        </a>
                        <div class="comment-body">
                            <div class="comment-heading">
                                <h4 class="user">${content.student.firstName} ${content.student.lastName}</h4>
                                <h5 class="time${content.contentID}"></h5>
                            </div>
                            <p>${content.content}</p>
                        </div>
                    </li>
                </ul>
             </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>
</html>