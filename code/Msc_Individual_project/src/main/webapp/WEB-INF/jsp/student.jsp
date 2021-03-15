<!DOCTYPE HTML>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script><%@include file="../jsp/student-narbar.jsp" %></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<head>
<title>Student Page</title>
</head>
<body>
	<%--Reference https://www.bootdey.com/snippets/view/Home-menu--%>
	<div class="col-md-12 col-sm-12 col-xs-12">
		<div class="container bootstrap snippets bootdey">
			<div class="col-md-12">
				<h2 class="text-primary">
					<i class="fa fa-tachometer"></i> Home menu
				</h2>
				<hr>
				<div class="row">
					<div class="col-md-4">
						<div class="panel panel-info ">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-6">
										<i class="fa fa-tasks fa-5x"></i>
									</div>
									<div class="col-xs-6 text-right">
										<p class="announcement-heading">&nbsp;</p>
										<p class="announcement-text">Proposal</p>
									</div>
								</div>
							</div>
							<a href="/student/proposal">
								<div class="panel-footer announcement-bottom">
									<div class="row">
										<div class="col-xs-6">View</div>
										<div class="col-xs-6 text-right">
											<i class="fa fa-arrow-circle-right"></i>
										</div>
									</div>
								</div>
							</a>
						</div>
					</div>

					<div class="col-md-4">
						<div class="panel panel-info ">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-6">
										<i class="fa fa-calendar-times-o fa-5x"></i>
									</div>
									<div class="col-xs-6 text-right">
										<p class="announcement-heading">&nbsp;</p>
										<p class="announcement-text">Weekly Plan</p>
									</div>
								</div>
							</div>
							<a href="/student/plan">
								<div class="panel-footer announcement-bottom">
									<div class="row">
										<div class="col-xs-6">View</div>
										<div class="col-xs-6 text-right">
											<i class="fa fa-arrow-circle-right"></i>
										</div>
									</div>
								</div>
							</a>
						</div>
					</div>

					<div class="col-md-4">
						<div class="panel panel-info ">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-6">
										<i class="fa fa-tags fa-5x"></i>
									</div>
									<div class="col-xs-6 text-right">
										<p class="announcement-heading">&nbsp;</p>
										<p class="announcement-text">Tag</p>
									</div>
								</div>
							</div>
							<a href="/student/tags">
								<div class="panel-footer announcement-bottom">
									<div class="row">
										<div class="col-xs-6">View</div>
										<div class="col-xs-6 text-right">
											<i class="fa fa-arrow-circle-right"></i>
										</div>
									</div>
								</div>
							</a>
						</div>
					</div>

				</div>
				<div class="row">
					<div class="col-md-4">
						<div class="panel panel-success ">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-6">
										<i class="fa fa-users fa-5x"></i>
									</div>
									<div class="col-xs-6 text-right">
										<p class="announcement-heading">&nbsp;</p>
										<p class="announcement-text">People</p>
									</div>
								</div>
							</div>
							<a href="/student/people">
								<div class="panel-footer announcement-bottom">
									<div class="row">
										<div class="col-xs-6">View</div>
										<div class="col-xs-6 text-right">
											<i class="fa fa-arrow-circle-right"></i>
										</div>
									</div>
								</div>
							</a>
						</div>
					</div>
					<div class="col-md-4">
						<div class="panel panel-warning ">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-6">
										<i class="fa fa-comment fa-5x"></i>
									</div>
									<div class="col-xs-6 text-right">
										<p class="announcement-heading">&nbsp;</p>
										<p class="announcement-text">Discussion</p>
									</div>
								</div>
							</div>
							<a href="/student/discussion">
								<div class="panel-footer announcement-bottom">
									<div class="row">
										<div class="col-xs-6">View</div>
										<div class="col-xs-6 text-right">
											<i class="fa fa-arrow-circle-right"></i>
										</div>
									</div>
								</div>
							</a>
						</div>
					</div>
				</div>

			</div>
		</div>

	</div>
</body>
</html>
