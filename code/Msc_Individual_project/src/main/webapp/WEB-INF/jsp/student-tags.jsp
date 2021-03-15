<!DOCTYPE HTML>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<title>Tag</title>
</head>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet">
<script><%@include file="../jsp/student-narbar.jsp" %></script>
<script><%@include file="../js/tag.js" %></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-tagsinput/1.3.6/jquery.tagsinput.min.js"></script>
<style>
body {
	font-family: 'Poppins', sans-serif;

}

div.tagsinput {
	border: 1px solid #CCC;
	background: #FFF;
	padding: 5px;
	width: 300px;
	overflow-y: auto;
}

div.tagsinput span.tag {
	-moz-border-radius: 2px;
	-webkit-border-radius: 2px;
	border-radius: 2px;
	display: block;
	float: left;
	padding: 4px;
	text-decoration: none;
	background: #55ACEE; /*#1abc9c; SoloLearn color*/
	color: #fff;
	margin-right: 5px;
	margin-bottom: 5px;
	font-family: 'Poppins', sans-serif;
	font-size: 13px;
	border-bottom: 2px solid #66757F; /*#17a68a; SoloLearn color*/
}

div.tagsinput span.tag a {
	font-weight: bold;
	color: #fff;
	text-decoration: none;
	font-size: 13px;
}

div.tagsinput input {
	width: 80px;
	margin: 0px;
	font-family: 'Hind', sans-serif;
	font-size: 13px;
	border: 1px solid transparent;
	padding: 5px;
	background: transparent;
	color: #000;
	outline: 0px;
	margin-right: 5px;
	margin-bottom: 5px;
}

span.tag {
	-moz-border-radius: 2px;
	-webkit-border-radius: 2px;
	border-radius: 2px;
	display: block;
	float: left;
	padding: 3px;
	text-decoration: none;
	background: #55ACEE;
	color: #fff;
	margin-right: 5px;
	margin-bottom: 5px;
	font-family: 'Poppins', sans-serif;
	font-size: 11px;
	border-bottom: 2px solid #66757F;
}

.tags {
	width: 100%;
	overflow-y: auto;
}
</style>
<div class="col-sm-2"></div>
<div class="col-sm-8">
	<form:form method="POST" modelAttribute="GetTags" action="/student/tags">
		<c:if test="${not empty Tags}">
			<table class="table user-list">
				<tr>
				<th style="width: 20%"><input type="checkbox" name="select-all"
					class="select-all checkbox" /></th>
				<th style="width: 40%; font-size:20px">Tags</th>
				</tr>
				<c:forEach items="${Tags}" var="tag">
				<tr>
					<c:if test="${tag.tag == ProjectType.type}">
					<td></td>
					</c:if>
					<c:if test="${tag.tag != ProjectType.type}">
						<td><form:checkbox path="tagID" value="${tag.tagID}"
								class="select-item checkbox"
								name="select-item" /></td>
					</c:if>
						<td>${tag.tag}</td>								
				</tr>
				</c:forEach>
			</table>
			<input type="submit" class="btn btn-danger" value="Delete" name="deleteTag" class="col-sm-6 text-left" />
	</br></br>
			<div class="form-group">
				<form:label for="tags" path="tag">Tags : (Hint: Keep the tags as simple as possible, complex tags may affect search quality)</form:label>
				<form:input class="tags" id="tags" path="tag" />
			</div>
		</c:if>
		
		
		<c:if test="${empty Tags}">
			<div class="form-group">
				<form:label for="tags" path="tag">Tags : (Hint: Keep the tags as simple as possible, complex tags may affect search quality)</form:label>
				<form:input class="tags" id="tags" path="tag" />
			</div>
		</c:if>
		<input type="submit" class="btn btn-primary" value="Add" name="addTag"
			class="col-sm-6 text-left" />
	</form:form>
</div>

<script>
$('#tags').tagsInput({
    width: 'auto',
    onAddTag: addTag,
    onRemoveTag: removeTag,
    delimiter: [' ']
    
});
</script>
</html>